package view;

import dto.Movie;
import dto.PageRequest;
import util.InputUtil;

import java.util.List;

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
}
