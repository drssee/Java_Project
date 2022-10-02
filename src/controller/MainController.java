package controller;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import domain.User;
import util.*;
import view.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MainController extends Controller{
    AdminController adminController;
    UserController userController;
    MainView mainView = new MainView();
    Login login;
    public MainController(){
        outer:
        while(result!=0){

            //관리자모드
            if(result==-9){
                adminController = new AdminController();
                while(result!=0){
                    result = adminController.admin();
                }
            }

            //메인메뉴
            result = (Integer) ClassUtil.INSTANCE.invoke("view.Home","mainMenu");

            //메뉴선택실패
            if(result==-1){
                printError("메뉴선택에 실패했습니다.");
                break;
            }

            //메뉴선택성공 1.로그인(로그인,회원가입) 2.현재상영중인영화목록 3.마이페이지 0.프로그램종료
            switch (result){

                //로그인(로그인,회원가입)
                case 1 : {
                    if(isInLogin){
                        isInLogin=false;
                        break;
                    }

                    result = (Integer) ClassUtil.INSTANCE.invoke("view.Login","loginMenu");
                    //1.로그인 2.회원가입

                    //1.//로그인
                    if(result==1){
                        userController = new LoginController();
                        while(result!=0){
                            //login() 리턴 값 result ==-9 관리자모드
                            //login() 리턴 값 result ==-10 유저모드
                            result = userController.login();
                            if(result==0){//종료
                                break outer;
                            }
                            else if(result==-1){//에러
                                printError();
                                break;
                            }
                            ClassUtil.INSTANCE.invoke("view.Home","welcome",result,loginedUser.getId());
                            break;
                        }//while
                    }//if(result==1)로그인


                    //2.회원가입
                    else if(result==2){
                        userController = new RegisterController();
                        System.out.println("회원가입");
                        result = userController.register();
                        if(result==1){
                            System.out.println("회원가입에 성공 하셨습니다");
                            System.out.println("서비스를 이용하시려면 로그인을 해주세요\n");
                        }
                    }
                    break;
                }//case 1 로그인(로그인,회원가입) 끝

                case 2: {//상영중인 영화목록
                    try {
                        Integer totalCnt = MovieServiceUtil.INSTANCE.movieService.getTotalCnt();
                        PageRequest pageRequest = new PageRequest(totalCnt);
                        List<Movie> movieList = new ArrayList<>();
                        String keyword = "";
                        int curPage;
                        int selected;
                        while (result != 0) {
                            //검색중이 아닐때
                            if(result!=7) {
                                //검색중이 아닐때 리스트
                                movieList = MovieServiceUtil.INSTANCE.movieService.getMovieList(pageRequest);
                            }

                            //영화목록 게시판을 불러온다
                            tmp = mainView.showMovieList(movieList, pageRequest);
                            //현재 게시판 페이지 기억하는 변수
                            curPage=pageRequest.getPage();

                            //s(검색) m(예약) p(이전) n(다음) q(종료)
                            if(tmp.equalsIgnoreCase("s")){
                                //검색기능
                                //키워드를 입력받아서 키워드에 해당하는 전체 영화 개수를 불러오고 ,
                                //키워드에 해당하는 페이지 정보도 가져온다
                                keyword = mainView.input_Search_Keyword();
                                totalCnt = MovieServiceUtil.INSTANCE.movieService.getSearchedTotalCnt(keyword);
                                pageRequest = new PageRequest(totalCnt);
                                //검색한 리스트
                                movieList = MovieServiceUtil.INSTANCE.movieService.getSearchedMovieList(pageRequest,keyword);
                                //키워드로 찾아온 영화리스트의 사이즈가 0이면 x
                                if(movieList.size()==0){
                                    printError("찾으시는 영화는 존재하지 않습니다");
                                    printError();
                                }
                                else{
                                    //검색조건을 알리기 위한 변수
                                    result = 7;
                                }
                            }
                            else if(tmp.equalsIgnoreCase("m")){
                                //isinlogin이 true일때만 들어갈수있음
                                if(!isInLogin){
                                    printError("먼저 로그인을 해주세요");
                                    break;
                                }
//                                로그인된 유저의 모든 예약정보를 가져온다
                                List<Reservation> reservations_byUser = MainServiceUtil.INSTANCE.mainService
                                        .getReservationList_byUser(loginedUser.getId());

                                //예매는 1인당 최대 5개까지
                                if(reservations_byUser.size()>=5){
                                    printError("1인당 최대 5개의 영화가 예매 가능합니다");
                                    continue;
                                }

                                //예매기능

                                //예매할 영화의 번호를 선택
                                selected = mainView.selectMovie(movieList.size());
                                if(selected==-1){
                                    printError("메뉴로 돌아갑니다");
                                    continue;
                                }
                                //선택된 영화를 가져온다
                                Movie movie=movieList.get(selected-1);
                                //없으면 continue
                                if(movie==null){
                                    printError();
                                    continue;
                                }

                                //선택한 영화의 시간이 기존에 예매한 영화의 시간과 겹치면 알려줌
                                if(!MainServiceUtil.INSTANCE.mainService.checkTime(movie,reservations_byUser)){
                                    printError("선택하신 "+movie.getTitle()+ "의 상영시간에 "+
                                            loginedUser.getId()+"님이 예약하신 영화가 이미 존재합니다");
                                    printError();
                                    continue;
                                }

                                //db의 reservation 테이블을 조회해서 title,schedule이 일치하는 리스트를 가져옴
                                //해당 영화의 reservation이 하나도 없을때
                                if(MainServiceUtil.INSTANCE.mainService.getReservationCnt(movie.getTno())==0){
                                    System.out.println("모든 좌석이 예매 가능합니다");
                                    selected = mainView.showSeatList();
                                }
                                else{
                                    //내가 선택한 영화의 모든 예약 리스트를 가져옴
                                    List<Reservation> reservationList = MainServiceUtil.INSTANCE.mainService
                                            .getReservationList(movie.getTno());

                                    //선택한 영화가 이미 유저가 예매한 영화면 예매불가
                                    for(int i=0;i<reservationList.size();i++){
                                        if(reservationList.get(i).getId().equals(loginedUser.getId())){
                                            printError("이미 "+loginedUser.getId()+"님이 예약한 영화입니다");
                                            continue outer;
                                        }
                                    }

                                    //최대 예약가능한 좌석수가 다 차면 불가능
                                    if(reservationList.size()>=RESERVATION_SIZE){
                                        printError("더이상 예매가 불가능합니다");
                                        continue outer;
                                    }
                                    //예약된 좌석리스트 가져와서 가능한 좌석 확인
                                    System.out.println("예약된 좌석은 X 표시");
                                    List<Integer> seatNumList = reservationList.stream().map(r->r.getSeatNum()).collect(Collectors.toList());
                                    selected = mainView.showSeatList(seatNumList);
                                    //선택한 좌석번호가 이미 등록되있는 좌석이면 다시 입력 받음
                                    while(seatNumList.contains(selected)){
                                        printError("올바른 좌석을 입력해 주세요(e)(중복 좌석 선택)\n예약 종료를 원하시면 q를 눌러 주세요");
                                        selected = mainView.showSeatList(seatNumList);
                                    }
                                }
                                if(selected==-1){
                                    printError("예약을 종료합니다");
                                    continue ;
                                }
                                //여기까지 온 selected는 유효한 숫자
                                //selected를 seatnum로 써야함
                                //reservation 객체를 조립해서 줘야함
                                if(mainView.confirm(movie)){
                                    //결제기능으로
                                    MainServiceUtil.INSTANCE.mainService.reservation(selected,movie,loginedUser);
                                    System.out.println(loginedUser.getId()+"님 "+movie.getTitle()+
                                            "의 예매에 성공하셨습니다");
                                }
                                else{
                                    printError(loginedUser.getId()+"님 "+movie.getTitle()+
                                            "의 예매에 실패하셨습니다");
                                    printError();
                                    //메뉴로
                                }
                                //user에 결제완료 기억해주고
                                //reservation에 insert
                            }
                            else if(tmp.equalsIgnoreCase("p")){
                                //이전
                                if(curPage!=1){
                                    pageRequest.setPage(pageRequest.getPage()-1);
                                    if(result==7){
                                        movieList = MovieServiceUtil.INSTANCE.movieService
                                                .getSearchedMovieList(pageRequest,keyword);
                                    }
                                }//if외부
                                else{
                                    printError("더이상 앞으로 갈 수 없습니다");
                                }
                            }//if(tmp.equalsIgnoreCase("p")) //이전
                            else if(tmp.equalsIgnoreCase("n")){
                                //다음
                                int totalPage = pageRequest.getTotalPage();
                                if(totalPage==0){
                                    //게시판에 아무것도 없는 상태일때
                                    printError("목록이 없습니다");
                                }
                                else if(curPage!=totalPage&&totalPage!=1){
                                    pageRequest.setPage(pageRequest.getPage()+1);
                                    if(result==7){
                                        movieList = MovieServiceUtil.INSTANCE.movieService
                                                .getSearchedMovieList(pageRequest,keyword);
                                    }
                                }
                                else{
                                    printError("더이상 뒤로 갈 수 없습니다");
                                }
                            }//if(tmp.equalsIgnoreCase("n")) //다음
                            else if(tmp.equalsIgnoreCase("q")){
                                result = 1;
                                continue outer;
                            }
                            else{
                                printError("올바른 메뉴를 선택해주세요");
                            }
                        }//while
                    }//try
                    catch(ClassNotFoundException e){
                        printError("해당목록을 조회할수 없습니다(cne)(프로그램 오류)");
                        continue;
                    } catch(SQLException e){
                        printError("해당목록을 조회할수 없습니다(sqe)(해당목록이 존재하지 않습니다)");
                        continue;
                    }
                    catch(Exception e){
                        printError("해당목록을 조회할수 없습니다(e)");
                        continue;
                    }
                    break;
                }//case2 : 영화목록 조회

                case 3 :{
                    if(!isInLogin){
                        printError("먼저 로그인을 해주세요");
                        break;
                    }
                    result = mainView.mypage();
                    //메뉴로
                    if(result == 0 ){
                        result = 1;
                        continue outer;
                    }
                    //회원정보 조회/수정
                    if(result == 1){
                        try {
                            User user = UserServiceUtil.INSTANCE.userService.selectOne(loginedUser.getId());
                            if(user == null){
                                throw new Exception();
                            }
                            mainView.mypage_1_1(user);
                            if(mainView.confirm()){
                                result = mainView.mypage_1_2();
                                //입력받음
                                user = mainView.updateUser(result,loginedUser);
                                if(user == null){
                                    throw new Exception();
                                }
                                //변경
                                result = UserServiceUtil.INSTANCE.userService
                                        .updateUser(user);
                                if(result !=1) {
                                    throw new Exception();
                                }
                                System.out.println("회원정보 변경에 성공했습니다");
                                loginedUser=user;
                            }
                        } catch (Exception e) {
                            printError("회원 정보를 불러오는 것에 실패했습니다(e)");
                            printError();
                            result = 1;
                            continue outer;
                        }
                    }
                    else if(result == 2){//예약정보 조회/수정
                        try {
                            List<Reservation> reservationList_byUser
                                    = MainServiceUtil.INSTANCE.mainService
                                    .getReservationList_byUser(loginedUser.getId());
                            if(reservationList_byUser==null){
                                throw new Exception();
                            }
                            if(reservationList_byUser.size()==0){
                                System.out.println("예매하신 영화는 존재하지 않습니다");
                                result = 1;
                                continue outer;
                            }
                            result = mainView.mypage_2_1(reservationList_byUser);
                            if(result==-1){
                                throw new Exception();
                            }
                            Reservation selectedRes = reservationList_byUser.get(result-1);
                            Timestamp schedule = selectedRes.getSchedule();
                            Long scheduledTime = schedule.getTime();
                            Long now = new Date().getTime();
//                                if(예약시간이 지금시간 -3 -0 사이이면)
                            if(now<scheduledTime&&(scheduledTime-(3*60*60*1000))<=now){
                                printError("취소 실패(상영시간 3시간 이내의 영화 선택)");
                                result = 1;
                                continue outer;
                            }//if
                            System.out.println("정말 취소 하시겠습니까? y/n");
                            if(mainView.confirm()){
                                MainServiceUtil.INSTANCE.mainService
                                        .deleteRes(selectedRes.getRno(),loginedUser,selectedRes.getPrice());
                                System.out.println("선택된 예약이 취소되었습니다");
                            }
                            result = 1;
                            continue outer;
                        } catch (Exception e) {
                            if(result==-1){
                                System.out.println("메뉴로 돌아갑니다\n");
                                continue ;
                            }
                            printError("예약정보를 조회할수 없습니다(e)");
                        }
                    }
                    else{
                        printError("올바른 메뉴를 선택해주세요");
                    }
                    break;
                }

                case 0 : {
                    ClassUtil.INSTANCE.invoke("view.Home","exit");
                    InputUtil.INSTANCE.close();
                    System.exit(0);
                    break;
                }
            }//switch
        }//while
    }
}

