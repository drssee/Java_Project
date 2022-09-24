package view;

public interface Errorable {
    default void printError(){
        System.out.println("다시 시도해 주세요");
    }
    default void printError(String msg) {
        System.out.println(msg);
    }

    static void s_printError(String msg) {
        System.out.println(msg);
    }
}
