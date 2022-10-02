package view;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import domain.User;
import formatter.DatetimeFormatter;
import util.InputUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class MainView implements Viewable, Errorable{
    public String showMovieList(List<Movie>movieList, PageRequest pageRequest){
        System.out.println("════════════════════════════════════════════════════예매 가능한 영화 목록════════════════════════════════════════════════════\n");
        Movie movie;
        for(int i=0;i<movieList.size();i++){
            movie=movieList.get(i);
            System.out.println(" "+(i+1)+". "+movie);
        }
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
        System.out.print(" [이전 p] [현재 페이지: "+pageRequest.getPage()+"] [총 페이지: "+pageRequest.getTotalPage()+"] [다음 n] \n [등록된 영화검색 s] " +
                "[자세히 보기 d] " +
                "[영화예매 m] " +
                "[메뉴로 q]");
        String tmp = InputUtil.INSTANCE.inputStr(1,1);
        System.out.println();
        return tmp;
    }
    public String input_Search_Keyword() {
        return InputForm.INSTANCE.inputKeyword();
    }

    public int selectMovie(int size) {
        System.out.println("예매할 영화의 번호를 입력해주세요\t");
        return InputUtil.INSTANCE.inputMenuNum(1,size);
    }

    public int showSeatList(){
        return showSeatList(new ArrayList<Integer>());
    }
    public int showSeatList(List<Integer> seatNumList){
        List<String> seat = new ArrayList<>();
        IntStream.rangeClosed(1,100).forEach(i->seat.add(String.valueOf(i)));

        for(int i=0;i<seatNumList.size();i++){
            seat.set((seatNumList.get(i)-1)," X");
        }

        for(int i=1;i<=seat.size()/10;i++){
            for(int a=1;a<=10;a++){
                if(a==5){
                    System.out.print("┌────┐\t\t");
                }
                else{
                    System.out.print("┌────┐");
                }
            }
            System.out.println();
            for(int b=1;b<=10;b++){
//                String tmp = ""+(i-1)+b;
                String tmp  = seat.get(((i-1)*10)+(b-1))+"";
                if(b==10){
                    if(i==10){
                        System.out.printf("|%3s |",tmp);
                    }
                    else{
                        System.out.printf("| %2s |",tmp);
                    }
                }
                else if(b==5){
                    System.out.printf("| %2s |\t\t",tmp);
                }
                else{
                    System.out.printf("| %2s |",tmp);
                }
            }
            System.out.println();
            for(int c=1;c<=10;c++){
                if(c==5){
                    System.out.print("└────┘\t\t");
                }
                else{
                    System.out.print("└────┘");
                }
            }
            System.out.println();
        }
        System.out.print("예약할 좌석을 입력해주세요\t");
        return InputUtil.INSTANCE.inputMenuNum(1,100);
    }
    public boolean confirm(Movie movie) {
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
            printError("올바른 문자를 입력해주세요");
            confirm(movie);
        }
        return false;
    }

//    public boolean confirm(){
//        String tmp = "";
//        tmp=InputUtil.INSTANCE.inputStr(1,1);
//        if(tmp.equalsIgnoreCase("y")){
//            return true;
//        }
//        else if(tmp.equalsIgnoreCase("n")){
//            return false;
//        }
//        else{
//            printError("올바른 문자를 입력해주세요");
//            confirm();
//        }
//        return false;
//    }

    public Integer mypage() {
        System.out.println("1.회원정보 조회/수정 2.나의예약 조회/취소 0.메뉴로 ");
        return InputUtil.INSTANCE.inputMenuNum(2);
    }
    public User updateUser(int num,User user) throws Exception {
        String tmp = "";
        switch (num){
            //변경할 정보가 pwd이면 변경전 pwd를 확인후 변경
            case 1:{
                System.out.print("1.변경전 pwd를 입력해주세요");
                tmp=InputUtil.INSTANCE.inputStr(5,18);
                while(!user.getPwd().equals(tmp)){
                    printError("잘못된 pwd를 입력하셨습니다\n다시 입력해주세요\n종료를 원하시면 exitq를 입력");
                    tmp=InputUtil.INSTANCE.inputStr(5,18);
                    if("exitq".equals(tmp)||"exitq".equals(tmp)){
                        throw new Exception();
                    }
                }
                System.out.print("1.변경할 pwd를 입력해주세요");
                tmp=InputUtil.INSTANCE.inputStr(5,18);
                while(user.getPwd().equals(tmp)){
                    System.out.println("이전과 다른 정보를 입력해주세요\n종료를 원하시면 exitq를 입력");
                    tmp=InputUtil.INSTANCE.inputStr(5,18);
                    if("exitq".equals(tmp)||"exitq".equals(tmp)){
                        throw new Exception();
                    }
                }
                user.setPwd(tmp);
                break;
            }
            case 2:{
                tmp = InputForm.INSTANCE.inputName();
                while(user.getName().equals(tmp)){
                    System.out.println("이전과 다른 정보를 입력해주세요");
                    tmp=InputForm.INSTANCE.inputName();
                }
                user.setName(tmp);
                break;
            }
            case 3:{
                tmp = InputForm.INSTANCE.inputPhone();
                while(user.getPhone().equals(tmp)){
                    System.out.println("이전과 다른 정보를 입력해주세요");
                    tmp = InputForm.INSTANCE.inputPhone();
                }
                user.setPhone(tmp);
                break;
            }
            case 4:{
                tmp = InputForm.INSTANCE.inputEmail();
                while(user.getEmail().equals(tmp)){
                    System.out.println("이전과 다른 정보를 입력해주세요");
                    tmp = InputForm.INSTANCE.inputEmail();
                }
                user.setEmail(tmp);
                break;
            }
        }
        Timestamp ts = new Timestamp(new Date().getTime());
        user.setModDate(ts);
        return user;
    }

    public void mypage_1_1(User user) {
        System.out.println(user);
        System.out.println("수정을 원하시면 y 아니면 n");
    }

    public Integer mypage_1_2() {
        System.out.println("수정 가능 한 회원 정보");
        System.out.println("1.비밀번호 2.회원이름 3.전화번호 4.이메일");
        System.out.println("수정을 원하시는 정보를 입력해주세요");
        return InputUtil.INSTANCE.inputMenuNum_noQ(1,4);
    }

    public Integer mypage_2_1(List<Reservation> reservationList){
        System.out.println("예매하신 영화 리스트");
        for(int i=0;i<reservationList.size();i++){
            Reservation r = reservationList.get(i);
            System.out.println((i+1)+". 영화제목:"+r.getTitle()
            +" 상영일자:"+r.getSchedule()+" 좌석번호:"+r.getSeatNum()
            +" 예매한시간:"+ DatetimeFormatter.INSTANCE
                            .formatDate(r.getRegDate()));
        }
        System.out.println("취소하고 싶으신 예약이 있으시면");
        System.out.println("번호를 입력해주세요");
        System.out.print("(주의)상영 3시간 이전의 영화는 취소 불가합니다\t");
        return InputUtil.INSTANCE.inputMenuNum(1,reservationList.size());
    }
}
