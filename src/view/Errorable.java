<<<<<<< HEAD
package view;

public interface Errorable {
    default void printError(){
        System.out.println("다시 시도해 주세요");
    }
    default void printError(String msg) {
        System.out.println(msg);
    }
}
=======
package view;

public interface Errorable {
    default void printError(){
        System.out.println("다시 시도해 주세요");
    }
    default void printError(String msg) {
        System.out.println(msg);
    }
}
>>>>>>> 5288752f898e494734fe7b6b2020f88b6a7b46b6
