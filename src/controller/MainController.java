package controller;

import view.Errorable;
import view.Login;
import view.Home;

import java.util.Map;

public class MainController implements Errorable {
    public MainController(){

        Integer result=-1;
        AdminController adminController = new AdminController();
        LoginController loginController = new LoginController();
        Map<String,String> id_pwd;

        outer:
        while(result!=0){
            if(result==-9){//관리자모드
                result = adminController.admin();
                if(result!=-1){
                    printError();
                    break;
                }
            }//관리자모드

            //메인메뉴
            result = Home.mainMenu();

            //메뉴선택실패
            if(result==-1){
                printError("메뉴선택에 실패했습니다.");
                break;
            }

            switch (result){
                case 1 : {//로그인
                    result = Login.loginMenu();
                    if(result==1){//로그인
                        while(result!=0){
                            result = loginController.login();
                            if(result==0){//종료
                                break outer;
                            }
                            else if(result==-1){//에러
                                printError();
                                continue;
                            }
                            else if(result==-9){//관리자모드 로그인 성공
                                break;
                            }
                            else if(result==-10){//유저모드 로그인 성공
                                break;
                            }
                        }//while
                    }//if(result==1)로그인

                    else if(result==2){//회원가입
                        System.out.println("회원가입");
                        //서비스 호출해서 회원가입하기

                    }
                    break;
                }
                case 2: {//상영중인 영화목록 // 조회하고 예약 하려고 하면 로그인 화면으로
                    System.out.println("상영중인 영화목록");
                    break;
                }

                case 0 : {
                    System.out.println("종료합니다");
                    break;
                }

            }//switch
        }//while
    }
}
