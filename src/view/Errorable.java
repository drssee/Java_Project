package view;

import util.InputUtil;

public interface Errorable {
    default void printError(){
        System.out.println("다시 시도해 주세요\n");
    }
    default void printError(String msg) {
        System.out.println(msg);
        System.out.println("\n" +
                "　　 ∧_∧ \n" +
                "　　(_  _   )\n" +
                "　　　ヽ ﾉ) \n" +
                "　 　 　 ｣｣\n");
    }

    default boolean confirm(){
        String tmp = "";
        tmp= InputUtil.INSTANCE.inputStr(1,1);
        if(tmp.equalsIgnoreCase("y")){
            return true;
        }
        else if(tmp.equalsIgnoreCase("n")){
            return false;
        }
        else{
            printError("올바른 문자를 입력해주세요");
            confirm();
        }
        return false;
    }
}
