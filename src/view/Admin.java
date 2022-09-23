package view;

import dto.Movie;
import util.InputUtil;

import java.sql.Timestamp;
import java.util.Calendar;

public class Admin {
    public Admin(){

    }

    public static Integer AdminMenu(){
        System.out.println("관리자모드");
        System.out.println("1.영화등록 2.영화 목록 3.전체예매자 목록 4.예매자 검색 0.관리자모드 종료");
        return InputUtil.INSTANCE.inputMenuNum(4);
    }

    public static Movie inputMovie() {
        Movie movie = new Movie();
        String tmpStr="";
        Integer tmpInt1; //날짜 입력받는 방법 바꿔야함
        Integer tmpInt2;
        Integer tmpInt3;
        Integer tmpInt4;
        Integer tmpInt5;
        Calendar cal = Calendar.getInstance();

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
        tmpInt1 = InputUtil.INSTANCE.inputMenuNum(9);
        movie.setRuntime(tmpInt1);
        System.out.println("5.영화의 오픈 날짜를 입력해주세요(년도 월 일)");
        System.out.print("년도");
        tmpInt1=InputUtil.INSTANCE.inputMenuNum(1900,2024);
        System.out.println();
        System.out.print("월");
        tmpInt2=InputUtil.INSTANCE.inputMenuNum(1,12);
        System.out.println();
        System.out.print("일");
        tmpInt3=InputUtil.INSTANCE.inputMenuNum(1,31);
        System.out.println();
        movie.setOpenDate(InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3).getTime());

        System.out.print("6.영화의 상영일자를 입력해 주세요(ex 2022,02,05,18,59)");
        System.out.print("년도");
        tmpInt1=InputUtil.INSTANCE.inputMenuNum(1900,2024);
        System.out.println();
        System.out.print("월");
        tmpInt2=InputUtil.INSTANCE.inputMenuNum(1,12);
        System.out.println();
        System.out.print("일");
        tmpInt3=InputUtil.INSTANCE.inputMenuNum(1,31);
        System.out.println();
        System.out.print("시간");
        tmpInt4=InputUtil.INSTANCE.inputMenuNum(0,23);
        System.out.println();
        System.out.print("분");
        tmpInt5=InputUtil.INSTANCE.inputMenuNum(0,59);
        System.out.println();
        movie.setSchedule(new Timestamp(InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3,tmpInt4,tmpInt5).getTimeInMillis()));

        return movie;
    }
}
