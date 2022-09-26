package view;

import dto.User;
import util.InputUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Login {
    public static Integer loginMenu(){
        System.out.println("로그인 메뉴");
        System.out.println("1.로그인 2.회원가입");
        return InputUtil.INSTANCE.inputMenuNum(2);
    }
    public static Map<String,String> login(){
        Map<String,String> id_pwd = new HashMap<>();
        String tmp = "";
        System.out.print("1.id를 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr(3,12);
        id_pwd.put("id",tmp);
        System.out.println();
        System.out.print("2.pwd를 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr(5,18);
        id_pwd.put("pwd",tmp);
        return id_pwd;
    }

    public static User inputUser(){
        User user = new User();
        String tmp = "";
        System.out.print("1.id를 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr(3,12);
        user.setId(tmp);
        System.out.print("2.pwd를 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr(5,18);
        user.setPwd(tmp);
        System.out.print("3.이름을 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr(1,12);
        user.setName(tmp);
        System.out.print("4.전화번호를 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr(8,12,true);
        user.setPhone(tmp);
        System.out.print("5.이메일을 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr(6,18,"@");
        user.setEmail(tmp);
        Date date = new Date();
        user.setRegDate(date);
        return user;
    }
}
