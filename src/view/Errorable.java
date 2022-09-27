package view;

public interface Errorable {
    default void printError(){
        System.out.println("다시 시도해 주세요");
        System.out.println();
    }
    default void printError(String msg) {
        System.out.println(msg);
        System.out.println();
    }

    static void s_printError(String msg) {
        System.out.println(msg);
        System.out.println();
    }
}
