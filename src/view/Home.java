package view;


import controller.MainController;
import util.InputUtil;

public class Home implements View{

    public Home(){

    }

    public static Integer mainMenu(){
        int result=-1;
        System.out.println("영화 예매 시스템");
        if(MainController.isInLogin){
            System.out.println("1.로그아웃 2.영화목록조회 3.마이페이지 0.종료");
            result = InputUtil.INSTANCE.inputMenuNum(3);
        }
        else {
            System.out.println("1.로그인 2.영화목록조회(비회원) 0.종료");
            result = InputUtil.INSTANCE.inputMenuNum(2);
        }
        return result;
    }
}
