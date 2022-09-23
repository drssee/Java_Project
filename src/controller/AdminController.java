package controller;

import dto.Movie;
import util.AdminServiceUtil;
import util.InputUtil;
import view.Admin;
import view.Errorable;

public class AdminController implements Errorable {

    public Integer admin() {
        Integer result=-1;
        Movie movie;
        while(result!=0){
            result = Admin.AdminMenu();
            //1.영화등록 2.영화목록 3.전체예매자 목록 4.예매자 검색

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
                    try {
                        result = AdminServiceUtil.INSTANCE.adminService.insertMovie(movie);
                    } catch (Exception e) {
                        printError("영화 등록에 실패했습니다(입력값을 확인해 주세요)");
                        System.out.println(movie);
                        continue;
                    }
                    if(result!=1){
                        printError("영화 등록에 실패했습니다(입력값을 확인해 주세요)");
                        System.out.println(movie);
                        continue;
                    }
                    System.out.println("영화등록에 성공 했습니다");
                    System.out.println(movie);//입력폼 출력형식 변경

                    break;
                }
                case 2:{

                    break;
                }
                case 3:{

                    break;
                }
                case 4:{

                    break;
                }
            }
        }//while
        return result;
    }
}
