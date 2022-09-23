package view;

import util.InputUtil;

public class Admin {
    public Admin(){

    }

    public static Integer AdminMenu(){
        System.out.println("관리자모드");
        System.out.println("1.영화등록 2.영화 목록 3.전체예매자 목록 4.예매자 검색");
        return InputUtil.INSTANCE.inputMenuNum(4);
    }
}
