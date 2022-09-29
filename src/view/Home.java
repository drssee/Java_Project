package view;


import controller.MainController;
import util.InputUtil;

public class Home implements View{

    public Home(){

    }

    public static Integer mainMenu(){
        System.out.println("영화 예매 시스템");
        if(MainController.isInLogin){
            System.out.println("1.로그아웃 2.영화목록조회 0.종료");
        }
        else {
            System.out.println("1.로그인 2.영화목록조회 3.마이페이지 0.종료");
        }
        return InputUtil.INSTANCE.inputMenuNum(3);
    }
}
