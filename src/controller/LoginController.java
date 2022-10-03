package controller;

import domain.User;
import util.ClassUtil;
import util.UserServiceUtil;
import view.Errorable;
import view.InputForm;
import view.Login;

import java.sql.SQLException;
import java.util.Map;

public class LoginController extends UserController{
    public LoginController(){}
    @Override
    public Integer login(){
        Integer result;
        Map<String,String> id_pwd;
        User user;

        id_pwd = login.login();
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
        }
        catch (ClassNotFoundException e){
            printError("로그인에 실패했습니다(cne)(프로그램 오류)");
            return -1;
        } catch (SQLException e) {
            printError("로그인에 실패했습니다(sqe)(해당유저가 존재하지 않습니다)");
            return -1;
        }
        catch (Exception e) {
            printError("로그인에 실패했습니다(e)");
            return -1;
        }

        //id와 연동되는 유저가 없을때
        if(user==null){
            printError("잘못된 회원 정보입니다");
            return -1;
        }

        //id가 일치할 경우
        else{
            //pwd가 다른경우
            if(!pwd.equals(user.getPwd())){
                printError("비밀번호가 다릅니다");
                return -1;
            }
        }

        //로그인에 성공하면 해당 유저를 static 변수에 올린다
        isInLogin=true;
        loginedUser=user;

        //로그인 성공 케이스중 아이디가 admin인 경우
        if(user.getId().equals("admin")){
            InputForm.INSTANCE.success1("관리자모드 로그인");
            return -9;
        }

        //일반 로그인 성공
        InputForm.INSTANCE.success1("로그인");
        result=-10;

        return result;
    }
}
