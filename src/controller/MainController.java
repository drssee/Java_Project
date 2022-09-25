package controller;

import util.InputUtil;
import view.Errorable;
import view.Login;
import view.Home;

import java.util.Map;

public class MainController implements Errorable,Controller {
    public MainController(){

        Integer result=-1;
        AdminController adminController = new AdminController();
        LoginController loginController = new LoginController();

        outer:
        while(result!=0){
            //관리자모드
            if(result==-9){
                while(result!=0){
                    result = adminController.admin();
                }
            }

            //메인메뉴
            result = Home.mainMenu();

            //메뉴선택실패
            if(result==-1){
                printError("메뉴선택에 실패했습니다.");
                break;
            }

            //메뉴선택성공 1.로그인(로그인,회원가입) 2.현재상영중인영화목록 0.프로그램종료
            switch (result){
                //로그인(로그인,회원가입)
                case 1 : {
                    result = Login.loginMenu();
                    //1.로그인 2.회원가입

                    //1.//로그인
                    if(result==1){
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


                    //2.회원가입
                    else if(result==2){
                        System.out.println("회원가입");
                        //서비스 호출해서 회원가입하기

                    }
                    break;
                }//case 1 로그인(로그인,회원가입) 끝
                case 2: {//상영중인 영화목록 // 조회하고 예약 하려고 하면 로그인 화면으로
                    System.out.println("상영중인 영화목록");
                    break;
                }

                case 0 : {
                    System.out.println("종료합니다");
                    InputUtil.INSTANCE.close();
                    break;
                }
            }//switch
        }//while
    }
}
