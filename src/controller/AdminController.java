package controller;


import dto.Movie;
import dto.PageRequest;
import util.AdminServiceUtil;
import view.Admin_Movie;
import view.Errorable;

import java.sql.SQLException;
import java.util.List;

public class AdminController implements Errorable,Controller {

    public Integer admin() {
        Integer result=-1;
        Movie movie;
        while(result!=0){
            result = Admin_Movie.AdminMenu();
            //1.영화등록 // 2.영화목록(검색,수정) 3.전체예매자목록(검색,수정)

            if(result==0){
                return result;
            }
            if(result==-1){
                printError("메뉴를 다시 입력해주세요");
                continue;
            }

            switch (result){
                case 1:{
                    //영화등록
                    movie = Admin_Movie.inputMovie();
                    if(movie==null){
                        printError();
                        movie = Admin_Movie.inputMovie();
                    }
                    if(!movie.getOpenDate().before(movie.getSchedule())){
                        //상영날짜가 개봉날짜 보다 이전일때
                        printError("상영날짜는 개봉일보다 이전일수 없습니다");
                        movie = Admin_Movie.inputMovie();
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
                }
                case 2:{
                    System.out.println("현재 등록된 영화 목록");
                    try {
                        Integer totalCnt = AdminServiceUtil.INSTANCE.adminService.getTotalCnt();
                        PageRequest pageRequest = new PageRequest(totalCnt);
                        List<Movie> movieList;
                        int curPage;
                        int selected;
                        while(result!=0){
                            movieList = AdminServiceUtil.INSTANCE.adminService.getMovieList(pageRequest);
                            String tmp = Admin_Movie.movieList(movieList,pageRequest);
                            curPage=pageRequest.getPage();
                            //m p n q
                            if(tmp.equalsIgnoreCase("m")){
                                //수정 삭제
                                selected = Admin_Movie.sel_modify_delete1();
                                movie=movieList.get(selected-1);
                                if(movie==null){
                                    printError();
                                    continue;
                                }
                                selected = Admin_Movie.sel_modify_delete2();

                                //수정
                                if(selected==1){
                                    //1.영화제목 2.감독 3.런타임 4.개봉일&상영스케줄 5.스토리요약
                                    movie = Admin_Movie.modifyMovie(movie);
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

                                }//if 1.수정

                                //삭제
                                else if(selected==2){
                                    //삭제추가
                                    selected = Admin_Movie.deleteMovie(movie);
                                    if(selected==1){
                                        selected = AdminServiceUtil.INSTANCE.adminService.deleteMovie(movie);
                                        if(selected!=1){
                                            printError("삭제에 실패했습니다");
                                            printError();
                                            continue;
                                        }
                                        else if (selected==1){
                                            System.out.println("삭제에 성공했습니다");
                                        }
                                    }//if 외부
                                    System.out.println("리스트로 돌아갑니다");
                                }
                            }//if(tmp.equalsIgnoreCase("m")) //수정 삭제 모드
                            else if(tmp.equalsIgnoreCase("p")){
                                //이전
                                if(curPage!=1){
                                    pageRequest.setPage(pageRequest.getPage()-1);
                                }
                                else{
                                    printError("더이상 앞으로 갈 수 없습니다");
                                    System.out.println();
                                }
                            }
                            else if(tmp.equalsIgnoreCase("n")){
                                //다음
                                if(curPage!=pageRequest.getTotalPage()){
                                    pageRequest.setPage(pageRequest.getPage()+1);
                                }
                                else{
                                    printError("더이상 뒤로 갈 수 없습니다");
                                    System.out.println();
                                }
                            }
                            else if(tmp.equalsIgnoreCase("q")){
                                //종료
                                result=0;
                                break;
                            }
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
                        printError("해당목록을 조회할수 없습니다(e)");
                        return -1;
                    }

                    break;
                }
                case 3:{

                    break;
                }
            }
        }//while
        return result;
    }
}
