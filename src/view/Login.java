package view;

import util.InputUtil;

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
//        List<String> id_pwd = new ArrayList<>();//map?<String,String> 아이디비밀번호 검증
        String tmp = "";
        System.out.print("1.id를 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr(4,12);
        System.out.println("id "+tmp);
        id_pwd.put("id",tmp);
        System.out.println();
        System.out.print("2.pwd를 입력해주세요");
        tmp=InputUtil.INSTANCE.inputStr(6,18);
        System.out.println("pwd "+tmp);
        id_pwd.put("pwd",tmp);
        return id_pwd;
    }
}
