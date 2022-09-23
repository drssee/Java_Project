package view;

import dto.Movie;
import util.InputUtil;

import java.util.Date;

public class Admin {
    public Admin(){

    }

    public static Integer AdminMenu(){
        System.out.println("관리자모드");
        System.out.println("1.영화등록 2.영화 목록 3.전체예매자 목록 4.예매자 검색");
        return InputUtil.INSTANCE.inputMenuNum(4);
    }

    public static Movie inputMovie() {
        Movie movie = new Movie();
        String tmpStr="";
        Integer tmpInt;
        Date tmpDate;
        System.out.println("영화등록 모드");
        System.out.print("1.영화제목을 입력해주세요");
        tmpStr =  InputUtil.INSTANCE.inputStr(1,12);
        movie.setTitle(tmpStr);
        System.out.print("2.간단한 줄거리를 입력해주세요(100자이하)");
        tmpStr = InputUtil.INSTANCE.inputStr(1,100);
        movie.setStory(tmpStr);
        System.out.print("3.영화감독 이름을 입력해주세요");
        tmpStr = InputUtil.INSTANCE.inputStr(1,12);
        movie.setDirector(tmpStr);
        System.out.print("4.영화의 런타임을 입력해주세요");
        tmpInt = InputUtil.INSTANCE.inputMenuNum(9);
        movie.setRuntime(tmpInt);
        System.out.print("5.영화의 오픈 날짜를 입력해주세요(ex 2022/02/05)");
        tmpDate = InputUtil.INSTANCE.inputDate(InputUtil.INSTANCE.inputStr(10,10));
        movie.setOpenDate(tmpDate);
        System.out.print("6.영화의 상영일자를 입력해 주세요(ex 2022/02/05 18:59:00");
        tmpDate = InputUtil.INSTANCE.inputDate(InputUtil.INSTANCE.inputStr(19,19));
        movie.setSchedule(tmpDate);
        return movie;

    }
}
