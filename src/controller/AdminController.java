package controller;


import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import util.AdminServiceUtil;
import util.ClassUtil;
import util.InputUtil;
import util.MainServiceUtil;
import view.Admin;
import view.MainView;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdminController extends Controller{
    Admin admin = new Admin();
    public Integer admin() {
        Integer result=-1;
        Movie movie;
        while(result!=0){
            result = admin.AdminMenu();
            //1.영화등록 2.영화목록(조회,수정,삭제,검색) 3.전체예매자목록(조회,수정,삭제,검색) +유저목록 0.종료

            if(result==0){
//                return result;
                ClassUtil.INSTANCE.invoke("view.Home","exit");
                InputUtil.INSTANCE.close();
                System.exit(0);
            }
            if(result==-1){
                printError("메뉴를 다시 입력해주세요");
                continue;
            }

            //1.영화등록 2.영화목록 3.전체예매자목록
            switch (result){
                //1.영화등록
                case 1:{
                    movie = admin.inputMovie();
                    if(movie==null){
                        printError();
                        movie = admin.inputMovie();
                    }
                    if(!movie.getOpenDate().before(movie.getSchedule())){
                        //상영날짜가 개봉날짜 보다 이전일때
                        printError("상영날짜는 개봉일보다 이전일수 없습니다");
                        movie = admin.inputMovie();
                    }
                    try {
                        result = AdminServiceUtil.INSTANCE.adminService.insertMovie(movie);
                    } catch (ClassNotFoundException e){
                        printError("영화 등록에 실패했습니다(cne)");
                        continue;
                    } catch (SQLException e){
                        printError("영화 등록에 실패했습니다(sqe)");
                        continue;
                    } catch (Exception e) {
                        printError("영화 등록에 실패했습니다(e)(입력값을 확인해 주세요)");
                        System.out.println(movie);
                        continue;
                    }

                    if(result!=1){
                        printError("영화 등록에 실패했습니다(입력값을 확인해 주세요)");
                        System.out.println(movie);
                        continue;
                    }
                    System.out.println("영화등록에 성공 했습니다");
                    System.out.println(movie);

                    break;
                }//case 1:영화등록

                //2.영화목록
                case 2:{
                    System.out.println("등록된 모든 영화 목록");
                    try {
                        //초기값
                        Integer totalCnt = AdminServiceUtil.INSTANCE.adminService.getMovieCnt();
                        PageRequest pageRequest = new PageRequest(totalCnt);
                        List<Movie> movieList = new ArrayList<>();
                        String keyword = "";
                        int curPage;
                        int selected;
                        while(result!=0){

                            //검색기능이 활성화되지 않은 경우
                            if(result!=7) {
                                movieList = AdminServiceUtil.INSTANCE.adminService.getMovieList(pageRequest);
                            }
                            //movielist를 화면에 출력후 메뉴입력받음
                            String tmp = admin.showMovieList(movieList,pageRequest);

                            //현재 페이지 저장을 위한 curPage
                            curPage=pageRequest.getPage();

                            //s(검색) m(수정/삭제) p(이전) n(다음) w(메뉴로) q(모드종료)
                            if(tmp.equalsIgnoreCase("s")){
                                keyword= admin.input_Search_Keyword();
                                totalCnt = AdminServiceUtil.INSTANCE.adminService.getSearchedMovieCnt(keyword);
                                pageRequest = new PageRequest(totalCnt);
                                movieList = AdminServiceUtil.INSTANCE.adminService.getSearchedMovieList(pageRequest,keyword);
                                if(movieList.size()==0){
                                    printError("찾으시는 영화는 존재하지 않습니다");
                                    break;
                                }
                                result = 7;
                            }//if(tmp.equalsIgnoreCase("s")) //검색 모드

                            else if(tmp.equalsIgnoreCase("m")){
                                //수정 삭제
                                selected = admin.sel_modify_delete1();
                                if(selected==-1){
                                    printError("메뉴로 돌아갑니다");
                                    continue;
                                }
                                movie=movieList.get(selected-1);
                                if(movie==null){
                                    printError();
                                    continue;
                                }
                                selected = admin.sel_modify_delete2();
                                if(selected==-1){
                                    printError("메뉴로 돌아갑니다");
                                    continue;
                                }
                                //수정
                                if(selected==1){
                                    //1.영화제목 2.감독 3.런타임 4.개봉일&상영스케줄 5.스토리요약
                                    movie = admin.modifyMovie(movie);
                                    if(movie==null){
                                        printError();
                                    }

                                    result = AdminServiceUtil.INSTANCE.adminService.updateMovie(movie);
                                    if(result!=1){
                                        printError("영화정보 업데이트에 실패했습니다");
                                        printError();
                                        return -1;
                                    }
                                    System.out.println("영화정보 업데이트에 성공했습니다");
                                    break;
                                }//if 1.수정

                                //삭제
                                else if(selected==2){
                                    //삭제추가
                                    selected = admin.deleteMovie(movie);
                                    if(selected==1){
                                        selected = AdminServiceUtil.INSTANCE.adminService.deleteMovie(movie);
                                        if(selected!=1){
                                            printError("삭제에 실패했습니다");
                                            printError();
                                            continue;
                                        }
                                        else if (selected==1){
                                            System.out.println("삭제에 성공했습니다");
                                            break;
                                        }
                                    }//if 외부
                                    System.out.println("리스트로 돌아갑니다");
                                }
                            }//if(tmp.equalsIgnoreCase("m")) //수정 삭제 모드
                            else if(tmp.equalsIgnoreCase("p")){
                                //이전
                                if(curPage!=1){
                                    pageRequest.setPage(pageRequest.getPage()-1);
                                    if(result==7){
                                        movieList=AdminServiceUtil.INSTANCE.adminService.getSearchedMovieList(pageRequest,keyword);
                                    }
                                }
                                else{
                                    printError("더이상 앞으로 갈 수 없습니다");
                                }
                            }//if(tmp.equalsIgnoreCase("p")) //이전
                            else if(tmp.equalsIgnoreCase("n")){
                                //다음
                                int totalPage = pageRequest.getTotalPage();

                                if(curPage!=totalPage&&totalPage!=1){
                                    pageRequest.setPage(pageRequest.getPage()+1);
                                    if(result==7){
                                        movieList=AdminServiceUtil.INSTANCE.adminService.getSearchedMovieList(pageRequest,keyword);
                                    }
                                }
                                else{
                                    printError("더이상 뒤로 갈 수 없습니다");
                                }
                            }//if(tmp.equalsIgnoreCase("n")) //다음
                            else if(tmp.equalsIgnoreCase("w")){
                                //관리자모드 처음으로
                                break;
                            }//if(tmp.equalsIgnoreCase("w")) //메뉴로
                            else if(tmp.equalsIgnoreCase("q")){
                                //관리자모드 종료
                                ClassUtil.INSTANCE.invoke("view.Home","exit");
                                InputUtil.INSTANCE.close();
                                System.exit(0);
                            }//if(tmp.equalsIgnoreCase("q")) //모드종료
                            else{
                                printError("올바른 메뉴를 선택해주세요");
                            }
                        }//while
                    }//try (관리자모드 2번의 try)
                    catch (ClassNotFoundException e){
                        printError("해당목록을 조회할수 없습니다(cne)(프로그램 오류)");
                        return -1;
                    } catch (SQLException e) {
                        printError("해당목록을 조회할수 없습니다(sqe)(해당목록이 존재하지 않습니다)");
                        return -1;
                    }
                    catch (Exception e) {
                        if(result==-1){
                            printError();
                            continue;
                        }
                        printError("해당목록을 조회할수 없습니다(e)");
                        return -1;
                    }

                    break;
                }//case 2: 등록된 영화 목록 리스트(조회/수정/삭제/검색)

                //3.전체예매자목록
                case 3:{
                    try {
                        System.out.println("현재 등록된 영화 목록(조회/수정/삭제)");
                        Integer totalResCnt = AdminServiceUtil.INSTANCE.adminService.getResCnt();
                        PageRequest pageRequest = new PageRequest(totalResCnt);
                        List<Reservation> reservationList = AdminServiceUtil
                                .INSTANCE.adminService.getResList(pageRequest);
                        String keyword = "";
                        int curPage;
                        int selected;
                        while(result!=0){
                            //검색중이 아닐때
                            if(result!=7){
                                //검색중이 아닐때
                                reservationList = AdminServiceUtil.INSTANCE.adminService.getResList(pageRequest);
                            }

                            //목록을 불러온다
                            tmp=admin.showResList(reservationList,pageRequest);
                            //현재 게시판 페이지 기억하는 변수
                            curPage=pageRequest.getPage();

                            //s(검색) m(수정) p(이전) n(다음) w(메뉴로) q(종료)
                            if(tmp.equalsIgnoreCase("s")){
                                keyword = admin.input_Search_Keyword();
                                totalResCnt = AdminServiceUtil.INSTANCE.adminService.getResCnt();
                                pageRequest = new PageRequest(totalResCnt);
                                //검색한 리스트
                                reservationList = AdminServiceUtil.INSTANCE.adminService.getSearchedResList(pageRequest,keyword);
                                //키워드로 찾아온 리스트의 사이즈가 0이면 존재x
                                if(reservationList.size()==0){
                                    printError("찾으시는 예약목록은 존재하지 않습니다");
                                    printError();
                                }
                                else{
                                    //검색중이라는것을 알리는 변수
                                    result = 7;
                                }
                            }//tmp==s
                            else if(tmp.equalsIgnoreCase("m")){
                                //예약정보 수정/삭제
                                result = admin.resConMenu();
                                if(result == -1){
                                    continue;
                                }
                                switch(result){
                                    case 1 :{
                                        //수정
                                        //update seatnum , moddate 수정가능
                                        //수정할 예약을 선택
                                        selected = admin.resConMenu1("수정");
                                        if(selected==-1){
                                            printError("메뉴로 돌아갑니다");
                                            continue;
                                        }
                                        Reservation selectedRes = reservationList.get(selected-1);
                                        //선택한 예약이 이미 상영된 영화라면 좌석을 수정할 필요가 없음
                                        if(!AdminServiceUtil.INSTANCE.adminService.checkSchedule(selectedRes)){
                                            printError("선택하신 예약은 이미 상영된 영화입니다(수정불가)");
                                            continue;
                                        }
                                        if(selectedRes==null){
                                            throw new Exception();
                                        }
                                        reservationList=AdminServiceUtil.INSTANCE.adminService
                                                .getResList_byTno(selectedRes.getTno());

                                        //tno로 검색된 예약리스트에 존재하는 모든 seatlist를 가져온다
                                        List<Integer> seatList = reservationList.stream()
                                                .map(r->r.getSeatNum()).collect(Collectors.toList());

                                        //변경하고싶은 좌석번호를 가져와 검증
//                                        Object obj = ClassUtil.INSTANCE.getObj("view.MainView");
//                                        result = (Integer) ClassUtil.INSTANCE.invoke("view.MainView",obj,"showSeatList",seatList);
                                        result = new MainView().showSeatList(seatList);
                                        if(result==-1){
                                            continue;
                                        }
                                        if(!AdminServiceUtil.INSTANCE.adminService.checkSeatNum(reservationList,result)){
                                            printError("해당 좌석에는 이미 예매가 존재합니다");
                                            printError();
                                            continue;
                                        }
                                        //검증에 통과한 경우에만 업데이트
                                        AdminServiceUtil.INSTANCE.adminService.updateRes(selectedRes,result);
                                        System.out.println("좌석번호 수정에 성공 했습니다");
                                        break;
                                    }
                                    case 2 :{
                                        selected = admin.resConMenu1("삭제");
                                        if(selected==-1){
                                            printError("메뉴로 돌아갑니다");
                                            continue;
                                        }
                                        Reservation selectedRes = reservationList.get(selected-1);
                                        if(selectedRes==null){
                                            throw new Exception();
                                        }
                                        System.out.println("정말로 삭제 하시겠습니까?");
                                        if(admin.confirm()){
                                            AdminServiceUtil.INSTANCE.adminService.deleteRes(selectedRes);
                                            System.out.println("삭제에 성공했습니다");
                                        }
                                        else{
                                            printError("메뉴로 돌아갑니다");
                                            continue;
                                        }
                                        break;
                                    }
                                }
                            }//tmp==m
                            else if(tmp.equalsIgnoreCase("p")){
                                //이전
                                if(curPage!=1){
                                    pageRequest.setPage(pageRequest.getPage()-1);
                                    if(result==7){
                                        reservationList = AdminServiceUtil.INSTANCE.adminService
                                                .getSearchedResList(pageRequest,keyword);
                                    }
                                }//if외부
                                else{
                                    printError("더이상 앞으로 갈 수 없습니다");
                                }
                            }//tmp==p
                            else if(tmp.equalsIgnoreCase("n")){
                                //다음
                                int totalPage = pageRequest.getTotalPage();
                                if(totalPage==0){
                                    //게시판에 아무것도 없는 상태일때
                                    printError("목록이 없습니다");
                                }
                                else if(curPage!=totalPage&&totalPage!=1){
                                    pageRequest.setPage(pageRequest.getPage()+1);
                                    if(result==7){
                                        reservationList=AdminServiceUtil.INSTANCE.adminService.
                                                getSearchedResList(pageRequest,keyword);
                                    }
                                }
                                else{
                                    printError("더이상 뒤로 갈 수 없습니다");
                                }
                            }//tmp==n
                            else if(tmp.equalsIgnoreCase("w")){
                                //관리자모드 처음으로
                                break;
                            }//if(tmp.equalsIgnoreCase("w")) //메뉴로
                            else if(tmp.equalsIgnoreCase("q")){
                                //관리자모드 종료
                                ClassUtil.INSTANCE.invoke("view.Home","exit");
                                InputUtil.INSTANCE.close();
                                System.exit(0);
                            }//if(tmp.equalsIgnoreCase("q")) //모드종료
                        }//while
                    } //try
                    catch(ClassNotFoundException e){
                        printError("해당목록을 조회할수 없습니다(cne)(프로그램 오류)");
                        continue;
                    } catch(SQLException e){
                        printError("해당목록을 조회할수 없습니다(sqe)(해당목록이 존재하지 않습니다)");
                        continue;
                    }
                    catch(Exception e){
                        if(result==-1){
                            printError();
                            continue;
                        }
                        e.printStackTrace();
                        printError("해당목록을 조회할수 없습니다(e)");
                        continue;
                    }
                    break;
                }//case 3: 전체예약목록(조회,수정,삭제/검색)
            }
        }//while
        return result;
    }
}
