package view;

import dto.Movie;
import dto.PageRequest;
import util.InputUtil;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Admin_Movie implements Errorable , View{
    public Admin_Movie(){

    }

    public static Integer AdminMenu(){
        System.out.println("관리자모드");
        System.out.println("1.영화등록 2.영화목록(조회/수정/삭제/검색) 3.전체예매자목록(조회/수정/삭제/검색) 0.관리자모드종료");
        return InputUtil.INSTANCE.inputMenuNum(3);
    }

    public static Movie inputMovie() {
        Movie movie = new Movie();
        String tmpStr="";
        Integer tmpInt1;
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
        System.out.print("년도>>");
        tmpInt1=InputUtil.INSTANCE.inputMenuNum(1900,2024);

        System.out.print("월>>");
        tmpInt2=InputUtil.INSTANCE.inputMenuNum(1,12);

        System.out.print("일>>");
        tmpInt3=InputUtil.INSTANCE.inputMenuNum(1,31);

        movie.setOpenDate(InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3).getTime());

        System.out.println("6.영화의 상영일자를 입력해 주세요(년도 월 일 시 분)");
        System.out.print("년도>>");
        tmpInt1=InputUtil.INSTANCE.inputMenuNum(1900,2024);

        System.out.print("월>>");
        tmpInt2=InputUtil.INSTANCE.inputMenuNum(1,12);

        System.out.print("일>>");
        tmpInt3=InputUtil.INSTANCE.inputMenuNum(1,31);

        System.out.print("시간>>");
        tmpInt4=InputUtil.INSTANCE.inputMenuNum(0,23);

        System.out.print("분>>");
        tmpInt5=InputUtil.INSTANCE.inputMenuNum(0,59);

        movie.setSchedule(new Timestamp(InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3,tmpInt4,tmpInt5).getTimeInMillis()));

        movie.setRegDate(new Date());

        return movie;
    }

    public static String movieList(List<Movie> movieList, PageRequest pageRequest){
        Movie movie;
//        System.out.println("    영화제목       감독      런타임        개봉일            상영스케줄              등록일         줄거리요약");
        for(int i=0;i<movieList.size();i++){
            movie=movieList.get(i);
            System.out.println((i+1)+". "+movie);
        }
        System.out.println("현재 페이지 : "+pageRequest.getPage());
        System.out.print("[등록된 영화검색 s] " +
                "[수정/삭제 m] " +
                "[이전 p] [다음 n] " +
                "[관리자메뉴로 w] [관리자모드종료 q]");
        String tmp = InputUtil.INSTANCE.inputStr(1,1);
        System.out.println();
        return tmp;
    }

    public static int sel_modify_delete1() {
        System.out.println("수정/삭제를 원하는 영화번호를 골라주세요");
        return InputUtil.INSTANCE.inputMenuNum(1,5);
    }

    public static int sel_modify_delete2() {
        System.out.println("1.수정 2.삭제");
        return InputUtil.INSTANCE.inputMenuNum(1,2);
    }

    public static Movie modifyMovie(Movie movie) {
        String tmpStr="";
        Integer tmpInt1;
        Integer tmpInt2;
        Integer tmpInt3;
        Integer tmpInt4;
        Integer tmpInt5;
        Calendar cal = Calendar.getInstance();

        System.out.println(movie);
        System.out.println("수정하고 싶은 옵션을 선택해주세요");
        System.out.print("1.영화제목 2.감독 3.런타임 4.개봉일&상영스케줄 5.스토리요약");
        tmpInt1 = InputUtil.INSTANCE.inputMenuNum(1,5);


        switch (tmpInt1){
            case 1:{
                System.out.print("1.영화제목을 입력해주세요");
                tmpStr =  InputUtil.INSTANCE.inputStr(1,12);
                movie.setTitle(tmpStr);
                break;
            } case 2:{
                System.out.print("2.영화감독 이름을 입력해주세요");
                tmpStr = InputUtil.INSTANCE.inputStr(1,12);
                movie.setDirector(tmpStr);
                break;
            } case 3:{
                System.out.print("3.영화의 런타임을 입력해주세요");
                tmpInt1 = InputUtil.INSTANCE.inputMenuNum(9);
                movie.setRuntime(tmpInt1);
                break;
            } case 4:{
                System.out.println("개봉일&상영스케줄");
                System.out.println("4-1.영화의 오픈 날짜를 입력해주세요(년도 월 일)");
                System.out.print("년도>>");
                tmpInt1=InputUtil.INSTANCE.inputMenuNum(1900,2024);

                System.out.print("월>>");
                tmpInt2=InputUtil.INSTANCE.inputMenuNum(1,12);

                System.out.print("일>>");
                tmpInt3=InputUtil.INSTANCE.inputMenuNum(1,31);

                Date openDate = InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3).getTime();

                //

                System.out.println("4-2.영화의 상영일자를 입력해 주세요(년도 월 일 시 분)");
                System.out.print("년도>>");
                tmpInt1=InputUtil.INSTANCE.inputMenuNum(1900,2024);

                System.out.print("월>>");
                tmpInt2=InputUtil.INSTANCE.inputMenuNum(1,12);

                System.out.print("일>>");
                tmpInt3=InputUtil.INSTANCE.inputMenuNum(1,31);

                System.out.print("시간>>");
                tmpInt4=InputUtil.INSTANCE.inputMenuNum(0,23);

                System.out.print("분>>");
                tmpInt5=InputUtil.INSTANCE.inputMenuNum(0,59);

                Timestamp schedule = new Timestamp(InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3,tmpInt4,tmpInt5).getTimeInMillis());

                if(!openDate.before(schedule)){
                    //상영날짜가 개봉날짜 보다 이전일때
                    Errorable.s_printError("상영날짜는 개봉일보다 이전일수 없습니다");
                    modifyMovie(movie);
                }

                movie.setOpenDate(openDate);

                movie.setSchedule(schedule);

                break;

            } case 5: {
                System.out.print("5.간단한 줄거리를 입력해주세요(100자이하)");
                tmpStr = InputUtil.INSTANCE.inputStr(1, 100);
                movie.setStory(tmpStr);
                break;
            } default: {
                Errorable.s_printError("올바른 메뉴를 선택해 주세요");
                return null;
            }
        }//switch
        return movie;
    }//modifymovie(movie)

    public static int deleteMovie(Movie movie) {
        System.out.println(movie);
        System.out.println("삭제 하시겠습니까? y/n");
        String tmp = InputUtil.INSTANCE.inputStr(1,1);
        if(tmp.equalsIgnoreCase("y")){
            return 1;
        }
        else if(tmp.equalsIgnoreCase("n")){
            return 0;
        }
        Errorable.s_printError("올바른 문자를 입력해주세요");
        return deleteMovie(movie);
    }

    public static String input_Search_Keyword() {
        System.out.print("검색할 키워드를 입력해주세요");
        return InputUtil.INSTANCE.inputStr(1,12);
    }
}


