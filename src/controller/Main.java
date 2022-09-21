package controller;

import dto.User;
import view.Login;
import view.Main_Home;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        Integer result=-1;
        while(result!=0)
        result = Main_Home.mainMenu();
        switch (result){
            case 1 : {//로그인
                result = Login.loginMenu();
                if(result==1){//로그인
                    List<String> id_pwd = Login.login();
                    if(id_pwd.size()!=2){
                        System.out.println("다시 입력해 주세요");
                        Login.login();
                    }
                    String id = id_pwd.get(0);
                    String pwd = id_pwd.get(id_pwd.size()-1);
                    User user = new User(id,pwd);

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
    }//main
}
