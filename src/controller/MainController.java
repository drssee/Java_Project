package controller;

import dto.Movie;
import dto.PageRequest;
import dto.User;
import util.AdminServiceUtil;
import util.InputUtil;
import util.UserServiceUtil;
import view.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainController implements Errorable,Controller {
    public static boolean isInLogin=false;
    Integer result=-1;
    public MainController(){

        AdminController adminController = new AdminController();
        LoginController loginController = new LoginController();
        RegisterController registerController = new RegisterController();

        outer:
        while(result!=0){
            //관리자모드
            if(result==-9){
                while(result!=0){
                    result = adminController.admin();
                }
            }

            //메인메뉴
            result = Home.mainMenu();

            //메뉴선택실패
            if(result==-1){
                printError("메뉴선택에 실패했습니다.");
                break;
            }

            //메뉴선택성공 1.로그인(로그인,회원가입) 2.현재상영중인영화목록 0.프로그램종료
            switch (result){
                //로그인(로그인,회원가입)
                case 1 : {
                    if(isInLogin){
                        isInLogin=false;
                        break;
                    }
                    result = Login.loginMenu();
                    //1.로그인 2.회원가입

                    //1.//로그인
                    if(result==1){
                        while(result!=0){
                            result = loginController.login();
                            if(result==0){//종료
                                break outer;
                            }
                            else if(result==-1){//에러
                                printError();
                                continue;
                            }
                            else if(result==-9){//관리자모드 로그인 성공
                                isInLogin=true;
                                break;
                            }
                            else if(result==-10){//유저모드 로그인 성공
                                //로그인 기억 하는 변수
                                isInLogin=true;
                                break;
                            }
                        }//while
                    }//if(result==1)로그인


                    //2.회원가입
                    else if(result==2){
                        System.out.println("회원가입");
                        result = registerController.register();
                        if(result==1){
                            System.out.println("회원가입에 성공 하셨습니다");
                        }
                        else{
                            printError("회원가입에 실패하셨습니다");
                            printError();
                        }
                    }
                    break;
                }//case 1 로그인(로그인,회원가입) 끝

                case 2: {//상영중인 영화목록

                    //isinlogin이 true일때만 들어갈수있음
                    if(!isInLogin){
                        printError("먼저 로그인을 해주세요");
                        break;
                    }
                    try {
                        Integer totalCnt = UserServiceUtil.INSTANCE.userService.getTotalCnt();
                        PageRequest pageRequest = new PageRequest(totalCnt);
                        List<Movie> movieList = new ArrayList<>();
                        String keyword = "";
                        int curPage;
                        int selected;
                        while (result != 0) {
                            //검색기능이 활성화되지 않은 경우
                            if(result!=7) {
                                movieList = UserServiceUtil.INSTANCE.userService.getMovieList(pageRequest);
                            }

                            String tmp = User_Movie.showMovie(movieList, pageRequest);

                            curPage=pageRequest.getPage();

                            //s m(예약) p n w q
                            if(tmp.equalsIgnoreCase("s")){
                                //검색기능
                                keyword= User_Movie.input_Search_Keyword();
                                totalCnt = UserServiceUtil.INSTANCE.userService.getSearchedTotalCnt(keyword);
                                pageRequest = new PageRequest(totalCnt);
                                movieList = UserServiceUtil.INSTANCE.userService.getSearchedMovieList(pageRequest,keyword);
                                if(movieList.size()==0){
                                    printError("찾으시는 영화는 존재하지 않습니다");
                                    printError();
                                }
                                result = 7;
                            }
                            else if(tmp.equalsIgnoreCase("p")){
                                //이전
                                if(curPage!=1){
                                    pageRequest.setPage(pageRequest.getPage()-1);
                                    if(result==7){
                                        movieList = UserServiceUtil.INSTANCE.userService.getSearchedMovieList(pageRequest,keyword);
                                    }
                                }
                                else{
                                    printError("더이상 앞으로 갈 수 없습니다");
                                }
                            }//if(tmp.equalsIgnoreCase("p")) //이전
                            else if(tmp.equalsIgnoreCase("n")){
                                //다음
                                int totalPage = pageRequest.getTotalPage();
                                if(totalPage==0){
                                    printError("목록이 없습니다");
                                }
                                else if(curPage!=totalPage&&totalPage!=1){
                                    pageRequest.setPage(pageRequest.getPage()+1);
                                    if(result==7){
                                        movieList = UserServiceUtil.INSTANCE.userService.getSearchedMovieList(pageRequest,keyword);
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

                }//case2 : 영화목록 조회

                case 0 : {
                    System.out.println("종료합니다");
                    InputUtil.INSTANCE.close();
                    break;
                }
            }//switch
        }//while
    }
}
