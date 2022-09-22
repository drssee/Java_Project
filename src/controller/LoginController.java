<<<<<<< HEAD
package controller;

import dto.User;
import util.UserServiceUtil;
import view.Login;

import java.sql.SQLException;
import java.util.Map;

public class LoginController extends MainController{
    public Integer inLoginMenu(){
        Integer result=0;
        Map<String,String> id_pwd;
        User user;

        id_pwd = Login.login();
        if(id_pwd.get("id")==null||id_pwd.get("pwd")==null){
            printError("로그인에 실패했습니다 id와 pwd를 확인해주세요");
            return -1;
        }
        String id = id_pwd.get("id");
        String pwd = id_pwd.get("pwd");

        //서비스 호출해서 id에 맞는 유저정보 가져오기
        user = null;
        try {
            user = UserServiceUtil.INSTANCE.userService.selectOne(id);
        } catch (ClassNotFoundException e){
            printError("데이터베이스 연결 실패");
            return -1;
        } catch (SQLException e) {
            printError("SQL문 에러");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        //id와 연동되는 유저가 없을때
        if(user==null){
            printError("잘못된 회원 정보입니다");
            return -1;
        }
        //id는 일치할경우
        else{
            //pwd가 다른경우
            if(!pwd.equals(user.getPwd())){
                printError("비밀번호가 다릅니다");
                return -1;
            }
        }

        if(user.getId().equals("admin")){
            System.out.println("관리자모드 로그인 성공");
            return -9;
        }
        System.out.println("로그인 성공");
        result=-10;

        return result;
    }
}
=======
package controller;

import dto.User;
import util.UserServiceUtil;
import view.Login;

import java.sql.SQLException;
import java.util.Map;

public class LoginController extends MainController{
    public Integer inLoginMenu(){
        Integer result=0;
        Map<String,String> id_pwd;
        User user;

        id_pwd = Login.login();
        if(id_pwd.get("id")==null||id_pwd.get("pwd")==null){
            printError("로그인에 실패했습니다 id와 pwd를 확인해주세요");
            return -1;
        }
        String id = id_pwd.get("id");
        String pwd = id_pwd.get("pwd");

        //서비스 호출해서 id에 맞는 유저정보 가져오기
        user = null;
        try {
            user = UserServiceUtil.INSTANCE.userService.selectOne(id);
        } catch (ClassNotFoundException e){
            printError("데이터베이스 연결 실패");
            return -1;
        } catch (SQLException e) {
            printError("SQL문 에러");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        //id와 연동되는 유저가 없을때
        if(user==null){
            printError("잘못된 회원 정보입니다");
            return -1;
        }
        //id는 일치할경우
        else{
            //pwd가 다른경우
            if(!pwd.equals(user.getPwd())){
                printError("비밀번호가 다릅니다");
                return -1;
            }
        }

        if(user.getId().equals("admin")){
            System.out.println("관리자모드 로그인 성공");
            return -9;
        }
        System.out.println("로그인 성공");
        result=-10;

        return result;
    }
}
>>>>>>> 5288752f898e494734fe7b6b2020f88b6a7b46b6
