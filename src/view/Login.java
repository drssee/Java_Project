package view;

import domain.User;
import util.InputUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Login implements Viewable {
    public Login() {}
    public Integer loginMenu(){
        System.out.println(" ⚘༉로그인 메뉴⚘༉ ");
        System.out.println("1.로그인 2.회원가입");
        return InputUtil.INSTANCE.inputMenuNum(2);
    }
    public Map<String,String> login(){
        Map<String,String> id_pwd = new HashMap<>();
        String tmp = "";
        tmp= InputForm.INSTANCE.inputId();
        id_pwd.put("id",tmp);
        System.out.println();
        tmp= InputForm.INSTANCE.inputPwd();
        id_pwd.put("pwd",tmp);
        return id_pwd;
    }
    //id 중복체크
    public String inputId(){
        String tmp = "";
        tmp = InputForm.INSTANCE.inputId();
        return tmp;
    }
    public User inputUser(String id){
        User user = new User();
        String tmp = "";
        user.setId(id);
        tmp = InputForm.INSTANCE.inputPwd();
        user.setPwd(tmp);
        tmp = InputForm.INSTANCE.inputName();
        user.setName(tmp);
        tmp = InputForm.INSTANCE.inputPhone();
        user.setPhone(tmp);
        tmp = InputForm.INSTANCE.inputEmail();
        user.setEmail(tmp);
        Date date = new Date();
        user.setRegDate(date);
        return user;
    }
}
