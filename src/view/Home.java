package view;

import controller.MainController;
import util.InputUtil;

public class Home implements Viewable {

    public Home(){}
    public void mainLogo(){
        System.out.println("\n" +
                " _____  ______ _____  _   _      ___  ___               _       \n" +
                "|  ___||___  /|  ___|| \\ | |     |  \\/  |              (_)      \n" +
                "| |__     / / | |__  |  \\| |     | .  . |  ___  __   __ _   ___ \n" +
                "|  __|   / /  |  __| | . ` |     | |\\/| | / _ \\ \\ \\ / /| | / _ \\\n" +
                "| |___ ./ /___| |___ | |\\  |     | |  | || (_) | \\ V / | ||  __/\n" +
                "\\____/ \\_____/\\____/ \\_| \\_/     \\_|  |_/ \\___/   \\_/  |_| \\___|\n" +
                "                                                                \n" +
                "                                                                ");
        System.out.println("영화 예매 시스템");
    }

    public void exit(){
        System.out.println("\n" +
                "┏♪━･･━･･━･･━･･━+☆+┓\n" +
                "이용해주셔서 감사합니다\n" +
                "     EZEN Movie를\n" +
                "        종료합니다!\n" +
                "　　 ∧,,∧　    ∧_∧\n" +
                " (*･ω･*) (*･ω･*)\n" +
                "┗+☆+━〇☆〇･〇☆〇━♬┛\n");
    }

    public Integer mainMenu(){
        int result=-1;
        if(MainController.isInLogin){
            System.out.println("1.로그아웃 2.영화목록조회 3.마이페이지 0.종료");
            result = InputUtil.INSTANCE.inputMenuNum(3);
        }
        else {
            System.out.println("1.로그인 2.영화목록조회(비회원) 0.종료");
            result = InputUtil.INSTANCE.inputMenuNum(2);
        }
        return result;
    }

    public void welcome(int num,String id){
        if(num==-9){
            System.out.println("EZEN_MOVIE 관리자");
        }
        System.out.println(
                id+"님 환영합니다!\n"+
                "｀、、｀ヽ｀ヽ｀、、ヽヽ、｀、ヽ｀ヽ｀ヽヽ｀\n" +
                "ヽ｀、｀ヽ｀、ヽ｀｀、ヽ｀ヽ｀、ヽヽ｀ヽ、ヽ\n" +
                "｀ヽ、ヽヽ｀ヽ｀、｀｀ヽ｀ヽ、ヽ、ヽ｀ヽ｀ヽ\n" +
                "、ヽ｀ヽ｀、ヽヽ｀｀、ヽ｀、ヽヽ \uD800\uDC8A ヽ｀｀\n");
    }
}
