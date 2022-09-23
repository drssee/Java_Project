package controller;

import dto.Movie;
import util.AdminServiceUtil;
import view.Admin;
import view.Errorable;

public class AdminController implements Errorable {

    public Integer admin() {
        Integer result;
        Movie movie;
        result = Admin.AdminMenu();
        //1.영화등록 2.영화목록 3.전체예매자 목록 4.예매자 검색

        if(result==-1){//찾는 메뉴가 없으면 -1을 리턴
            printError("메뉴를 다시 입력해주세요");
            return result;
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
                    return -1;
                }
                if(result!=1){
                    printError("영화 등록에 실패했습니다(입력값을 확인해 주세요)");
                    return -1;
                }
                System.out.println("영화등록에 성공 했습니다");
                System.out.println(movie);
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

        return result;
    }
}
