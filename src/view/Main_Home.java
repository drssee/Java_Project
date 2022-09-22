package view;


import util.InputUtil;

public class Main_Home {

    public Main_Home(){

    }

    public static Integer mainMenu(){
        System.out.println("영화 예매 시스템");
        System.out.println("1.로그인 2.상영중인 영화목록 0.종료");
        return InputUtil.INSTANCE.inputMenuNum(2);
    }
}
