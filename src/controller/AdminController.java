package controller;

import dto.Movie;
import dto.PageRequest;
import util.AdminServiceUtil;
import util.InputUtil;
import view.Admin;
import view.Errorable;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class AdminController implements Errorable {

    public Integer admin() {
        Integer result=-1;
        Movie movie;
        while(result!=0){
            result = Admin.AdminMenu();
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
                    movie = Admin.inputMovie();
                    if(movie==null){
                        printError();
                        movie = Admin.inputMovie();
                    }
                    if(!movie.getOpenDate().before(movie.getSchedule())){
                        //상영날짜가 개봉날짜 보다 이전일때
                        printError("상영날짜는 개봉일보다 이전일수 없습니다");
                        movie = Admin.inputMovie();
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
                        List<Movie> movieList = AdminServiceUtil.INSTANCE.adminService.getMovieList(pageRequest);
                        while(result!=0){
                            movieList = AdminServiceUtil.INSTANCE.adminService.getMovieList(pageRequest);
                            String tmp = Admin.movieList(movieList,pageRequest);
                            //m p n q
                            if(tmp.equalsIgnoreCase("m")){
                                //수정 삭제
//                                Admin.modifyMovie(movieList);
                            }
                            else if(tmp.equalsIgnoreCase("p")){
                                //이전
                                if(pageRequest.isNext()){
                                    pageRequest.setPage(pageRequest.getPage()-1);
                                }
                            }
                            else if(tmp.equalsIgnoreCase("n")){
                                //다음
                                if(pageRequest.isNext()){
                                    pageRequest.setPage(pageRequest.getPage()+1);
                                }
                            }
                            else if(tmp.equalsIgnoreCase("q")){
                                //종료
                                break;
                            }
                            else{
                                printError("올바른 메뉴를 선택해주세요");
                            }
                        }//while
                    } catch (Exception e) {
                        printError("목록 조회에 실패했습니다");
                        printError();
                        continue;
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
