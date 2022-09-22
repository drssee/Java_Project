package controller;

import view.Login;
import view.Main_Home;

import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Integer result=-1;
        while(result!=0){
            result = Main_Home.mainMenu();
            if(result==-1){
                System.out.println("다시 시도해 주세요");
                break;
            }
            switch (result){
                case 1 : {//로그인
                    result = Login.loginMenu();
                    if(result==1){//로그인
                        Map<String,String> id_pwd = Login.login();
                        //내부(login())에서 체크하고 리턴 재귀호출
                        if(id_pwd.get("id")==null||id_pwd.get("pwd")==null){
                            System.out.println("다시 입력해 주세요");
                            id_pwd = Login.login();
                        }
                        String id = id_pwd.get("id");
                        String pwd = id_pwd.get("pwd");
                        System.out.print(id+" , ");
                        System.out.println(pwd);
                        //서비스 호출해서 유저정보 가져오기
                    }
                    else if(result==2){//회원가입
                        //서비스 호출해서 회원가입하기
                    }
                    break;
                }
                case 2: {//상영중인 영화목록
                    break;
                }
                case 0 : {
                    System.out.println("종료합니다");
                    break;
                }
            }//switch
        }//while
    }//main
}
