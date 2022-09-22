package controller;

import view.Errorable;
import view.Login;
import view.Main_Home;

import java.util.Map;

public class MainController implements Errorable {
    public MainController(){

        Integer result=-1;
        LoginController loginController;
        Map<String,String> id_pwd;

        outer:
        while(result!=0){
            if(result==-9){//관리자모드 로그인 성공 뷰 만들기
                System.out.println("result -9 성공");
                break;
            }

            if(result==-10){//유저모드 로그인 성공 뷰 만들기
                System.out.println("result -10 성공");
                break;
            }

            result = Main_Home.mainMenu();
            if(result==-1){
                printError("메뉴선택에 실패했습니다.");
                break;
            }
            switch (result){
                case 1 : {//로그인
                    result = Login.loginMenu();
                    if(result==1){//로그인
                        while(result!=0){
                            loginController=new LoginController(); //connectionutil의 에러가 모이는곳
                            result = loginController.inLoginMenu();
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
