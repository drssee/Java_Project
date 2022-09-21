package view;

import util.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class Login {
    public static Integer loginMenu(){
        System.out.println("로그인 메뉴");
        System.out.println("1.로그인 2.회원가입");
        return InputUtil.INSTANCE.inputNum();
    }
    public static List<String> login(){
        List<String> id_pwd = new ArrayList<>();
        String tmp = "";
        System.out.print("1.id를 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr();
        id_pwd.add(tmp);
        System.out.println();
        System.out.print("2.pwd를 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr();
        id_pwd.add(tmp);
        return id_pwd;
    }
}
