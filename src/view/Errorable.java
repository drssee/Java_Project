package view;

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
}
