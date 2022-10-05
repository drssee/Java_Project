package view;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import util.InputUtil;
import util.MainServiceUtil;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Admin implements Viewable, Errorable{
    public Admin(){}

    public Integer AdminMenu(){
        System.out.println("관리자모드");
        System.out.println("1.영화등록 2.영화목록(조회/수정/삭제/검색) 3.전체예매자목록(조회/수정/삭제/검색) 0.관리자모드종료");
        return InputUtil.INSTANCE.inputMenuNum(3);
    }

    public void curMovieList(){
        System.out.println("현재 등록된 영화 목록");
    }

    public Movie inputMovie() {
        Movie movie = new Movie();
        String tmpStr="";
        Integer tmpInt1;
        Integer tmpInt2;
        Integer tmpInt3;
        Integer tmpInt4;
        Integer tmpInt5;
        Calendar cal = Calendar.getInstance();

        System.out.println("영화등록 모드");
        tmpStr = InputForm.INSTANCE.inputMovieName();
        movie.setTitle(tmpStr);
        tmpStr = InputForm.INSTANCE.inputMovieStory();
        movie.setStory(tmpStr);
        tmpStr = InputForm.INSTANCE.inputMovieDirector();
        movie.setDirector(tmpStr);
        tmpInt1 = InputForm.INSTANCE.inputMovieRuntime();
        movie.setRuntime(tmpInt1);
        tmpStr = InputForm.INSTANCE.inputActor();
        movie.setActor(tmpStr);

        System.out.println("6.영화의 오픈 날짜를 입력해주세요(년도 월 일)");
        tmpInt1=InputForm.INSTANCE.inputYear();

        tmpInt2=InputForm.INSTANCE.inputMonth();

        tmpInt3=InputForm.INSTANCE.inputDay();

        movie.setOpenDate(InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3).getTime());

        System.out.println("7.영화의 상영일자를 입력해 주세요(년도 월 일 시 분)");
        tmpInt1=InputForm.INSTANCE.inputYear();

        tmpInt2=InputForm.INSTANCE.inputMonth();

        tmpInt3=InputForm.INSTANCE.inputDay();

        tmpInt4=InputForm.INSTANCE.inputHour();

        tmpInt5=InputForm.INSTANCE.inputMinute();

        movie.setSchedule(new Timestamp(InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3,tmpInt4,tmpInt5).getTimeInMillis()));

        movie.setRegDate(new Date());

        return movie;
    }
    public String showMovieList(List<Movie> movieList, PageRequest pageRequest){
        System.out.println("════════════════════════════════════════════════════등록된 영화 관리═══════════════════════════════════════════════════════\n");
        Movie movie;
        for(int i=0;i<movieList.size();i++){
            movie=movieList.get(i);
            System.out.println(" "+(i+1)+". "+movie);
        }
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
        System.out.print(" [이전 p] [현재 페이지: "+pageRequest.getPage()+"] [총 페이지: "+pageRequest.getTotalPage()+"] [다음 n] \n\n [영화제목 검색 s] " +
                "[자세히 보기 d] " +
                "[수정/삭제 m] " +
                "[관리모드메뉴로 w] " +
                "[관리모드종료 q]");
        String tmp = InputUtil.INSTANCE.inputStr(1,1);
        System.out.println();
        return tmp;
    }
    public int sel_modify(String msg) {
        System.out.print(msg+"를 원하는 영화번호를 골라주세요\t");
        return InputUtil.INSTANCE.inputMenuNum(1,5);
    }

    public int sel_modify_delete() {
        System.out.print("1.수정 2.삭제\t");
        return InputUtil.INSTANCE.inputMenuNum(1,2);
    }

    public Movie modifyMovie(Movie movie,String analysis) {
        String tmpStr="";
        Integer tmpInt1;
        Integer tmpInt2;
        Integer tmpInt3;
        Integer tmpInt4;
        Integer tmpInt5;
        Calendar cal = Calendar.getInstance();
        InputForm.INSTANCE.detailMovie(movie,40,analysis);
        System.out.println("수정하고 싶은 옵션을 선택해주세요");
        System.out.print("1.영화제목 2.감독 3.배우목록 4.런타임 5.개봉일&상영스케줄 6.스토리요약\t");
        tmpInt1 = InputUtil.INSTANCE.inputMenuNum(1,6);
        if(tmpInt1==-1){
            return null;
        }
        switch (tmpInt1){
            case 1:{
                tmpStr = InputForm.INSTANCE.inputMovieName();
                movie.setTitle(tmpStr);
                break;
            } case 2:{
                tmpStr = InputForm.INSTANCE.inputMovieDirector();
                movie.setDirector(tmpStr);
                break;
            } case 3:{
                tmpStr = InputForm.INSTANCE.inputActor();
                movie.setActor(tmpStr);
            } case 4:{
                tmpInt1 = InputForm.INSTANCE.inputMovieRuntime();
                movie.setRuntime(tmpInt1);
                break;
            } case 5:{
                System.out.println("개봉일&상영스케줄");
                System.out.println("4-1.영화의 오픈 날짜를 입력해주세요(년도 월 일)");
                tmpInt1=InputForm.INSTANCE.inputYear();

                tmpInt2=InputForm.INSTANCE.inputMonth();

                tmpInt3=InputForm.INSTANCE.inputDay();

                Date openDate = InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3).getTime();

                //

                System.out.println("4-2.영화의 상영일자를 입력해 주세요(년도 월 일 시 분)");
                tmpInt1=InputForm.INSTANCE.inputYear();

                tmpInt2=InputForm.INSTANCE.inputMonth();

                tmpInt3=InputForm.INSTANCE.inputDay();

                tmpInt4=InputForm.INSTANCE.inputHour();

                tmpInt5=InputForm.INSTANCE.inputMinute();

                Timestamp schedule = new Timestamp(InputUtil.INSTANCE.inputCal(tmpInt1,tmpInt2,tmpInt3,tmpInt4,tmpInt5).getTimeInMillis());

                if(!openDate.before(schedule)){
                    //상영날짜가 개봉날짜 보다 이전일때
                    printError("상영날짜는 개봉일보다 이전일수 없습니다");
                    modifyMovie(movie,analysis);
                }

                movie.setOpenDate(openDate);

                movie.setSchedule(schedule);

                break;

            } case 6: {
                tmpStr=InputForm.INSTANCE.inputMovieStory();
                movie.setStory(tmpStr);
                break;
            } default: {
                printError("올바른 메뉴를 선택해 주세요");
                return null;
            }
        }//switch
        return movie;
    }//modifymovie(movie)

    public int deleteMovie(Movie movie,String analysis) {
        InputForm.INSTANCE.detailMovie(movie,40,analysis);
        System.out.println("삭제 하시겠습니까? y/n");
        String tmp = InputUtil.INSTANCE.inputStr(1,1);
        if(tmp.equalsIgnoreCase("y")){
            return 1;
        }
        else if(tmp.equalsIgnoreCase("n")){
            return 0;
        }
        printError("올바른 문자를 입력해주세요");
        return deleteMovie(movie,analysis);
    }

    public String input_Search_Keyword() {
        return InputForm.INSTANCE.inputKeyword();
    }
    public String showResList(List<Reservation> reservationList, PageRequest pageRequest){
        System.out.println("══════════════════════════════════════════════════════════════════════════등록된 예매 관리═════════════════════════════════════════════════════════════════════════════\n");
        Reservation reservation;
        for(int i=0;i<reservationList.size();i++){
            reservation=reservationList.get(i);
            System.out.println(" "+(i+1)+". "+reservation);
        }
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
        System.out.print(" [이전 p] [현재 페이지: "+pageRequest.getPage()+"] [총 페이지: "+pageRequest.getTotalPage()+"] [다음 n] \n\n [고객id검색 s] " +
                "[예약수정/삭제 m] " +
                "[관리모드메뉴로 w] " +
                "[관리모드종료 q]");
        String tmp = InputUtil.INSTANCE.inputStr(1,1);
        System.out.println();
        return tmp;
    }

    public Integer resConMenu() {
        System.out.println("현재 메뉴에선 좌석번호만 수정 가능합니다, 영화정보의 변경을 원하시면 무비탭으로 이동해주세요");
        System.out.print("1.수정 2.삭제\t");
        return InputUtil.INSTANCE.inputMenuNum(1,2);
    }
    public Integer resConMenu1(String msg){
        System.out.print(msg+"을 원하시는 예약 리스트를 선택해주세요(예약번호 입력 x)\t");
        return InputUtil.INSTANCE.inputMenuNum(1,5);
    }

}


