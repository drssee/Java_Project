package view;

public interface Errorable {
    //부모생성자 안에서 자식생성자 호출
    //자원 사용하고 close
    //util date / sql date 변환이 힘들었음
    default void printError(){
        System.out.println("다시 시도해 주세요");
    }
    default void printError(String msg) {
        System.out.println(msg);
    }
}
