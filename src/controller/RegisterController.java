package controller;

import domain.User;
import util.UserServiceUtil;
import view.Errorable;
import view.Login;

public class RegisterController implements Errorable,Controller {
    public Integer register(){
        //뷰 호출해서 입력받고
        User user = new User();
        int result = -1;
        user = Login.inputUser();
        if(user==null){
            printError();
            user=Login.inputUser();
        }
        try {
            result = UserServiceUtil.INSTANCE.userService.registerUser(user);
            if(result == 1){
                result = 1;
                return result;
            }
        } catch (Exception e) {
            printError("회원가입에 실패하셨습니다(e)");
            printError();
            result = register();
        }
        return result;
    }
}
