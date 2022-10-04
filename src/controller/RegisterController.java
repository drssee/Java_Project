package controller;

import domain.User;
import util.UserServiceUtil;
import view.Errorable;
import view.InputForm;
import view.Login;

import java.sql.SQLException;

public class RegisterController extends UserController implements Errorable{
    public RegisterController(){}
    @Override
    public Integer register(){
        User user = new User();
        int result = -1;
        try {
            //중복 id 체크
            String id = login.inputId();
            try{
                UserServiceUtil.INSTANCE.userService.selectOne(id);
                if(id.equals(user.getId())){
                    System.out.println("동일한 아이디의 유저가 이미 존재합니다\n");
                    return -1;
                }
            } catch(SQLException e){
                //selectone(id)를 못가져 왔다는것은 id의 유저가 없는경우
                user = login.inputUser(id);
            }
            if(user==null){
                printError();
                throw new Exception();
            }
            result = UserServiceUtil.INSTANCE.userService.registerUser(user);
            if(result == 1){
                result = 1;
                InputForm.INSTANCE.success("회원가입");
                return result;
            }
        } catch(SQLException e){
            printError("회원가입에 실패하셨습니다(sqe)");
            printError();
            return -1;
        }
        catch (Exception e) {
            printError("회원가입에 실패하셨습니다(e)");
            printError();
            return -1;
        }
        return result;
    }
}
