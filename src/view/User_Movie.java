package view;

import controller.MainController;
import domain.Movie;
import domain.PageRequest;
import util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class User_Movie implements View{
    public static String showMovie(List<Movie>movieList, PageRequest pageRequest){
        System.out.println("예매 가능한 영화 목록");
        Movie movie;
//        System.out.println("    영화제목       감독      런타임        개봉일            상영스케줄              등록일         줄거리요약");
        for(int i=0;i<movieList.size();i++){
            movie=movieList.get(i);
            System.out.println((i+1)+". "+movie);
        }
        System.out.println("현재 페이지 : "+pageRequest.getPage());
        System.out.print("[등록된 영화검색 s] " +
                "[영화예매 m] " +
                "[이전 p] [다음 n] " +
                "[메뉴로 q]");
        String tmp = InputUtil.INSTANCE.inputStr(1,1);
        System.out.println();
        return tmp;
    }
    public static String input_Search_Keyword() {
        System.out.print("검색할 키워드를 입력해주세요");
        return InputUtil.INSTANCE.inputStr(1,12);
    }

    public static int selectMovie(int size) {
        System.out.println("예매할 영화의 번호를 입력해주세요");
        return InputUtil.INSTANCE.inputMenuNum(1,size);
    }

    public static int showSeatList(List<Integer> seatNumList){
        List<String> seat = new ArrayList<>();
        IntStream.rangeClosed(1,100).forEach(i->seat.add(String.valueOf(i)));


        for(int i=0;i<seatNumList.size();i++){
            seat.set((seatNumList.get(i)-1)," X");
        }

        for(int i=1;i<=seat.size();i++){
            if(i<=10){
                System.out.printf(" %2s ",seat.get(i-1));
            }
            else{
                System.out.print(" "+seat.get(i-1)+" ");
            }
            if(i%10==0) {
                System.out.println();
            }
        }
        System.out.println("예약할 좌석을 입력해주세요");
        return InputUtil.INSTANCE.inputMenuNum(1,100);
    }
    public static int showSeatList(){
        for(int i=1;i<= MainController.RESERVATION_SIZE;i++){
            if(i<10){
                System.out.printf(" %2d ",i);
            }
            else{
                System.out.print(" "+i+" ");
            }
            if(i%10==0) {
                System.out.println();
            }
        }
        System.out.println("예약할 좌석을 입력해주세요");
        return InputUtil.INSTANCE.inputMenuNum(1,100);
    }

    public static boolean confirm(Movie movie) {
        String tmp = "";
        System.out.println(movie);
        System.out.println("선택하신 "+movie.getTitle()+"을 예매하시겠습니까? "
                +movie.getPrice()+"원 결제"+"y/n");
        tmp=InputUtil.INSTANCE.inputStr(1,1);
        if(tmp.equalsIgnoreCase("y")){
            return true;
        }
        else if(tmp.equalsIgnoreCase("n")){
            return false;
        }
        else{
            System.out.println("올바른 문자를 입력해주세요");
            confirm(movie);
        }
        return false;
    }

    public static Integer mypage() {
        System.out.println("1.회원정보 조회/수정 2.나의예약 조회/취소 ");
        return InputUtil.INSTANCE.inputMenuNum(2);
    }
}
