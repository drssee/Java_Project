package view;

import util.InputUtil;

public enum InputForm {
    INSTANCE;
    InputForm(){}
    public int inputYear(){
        System.out.print("년도>>");
        return InputUtil.INSTANCE.inputMenuNum(1900,2024);
    }
    public int inputMonth(){
        System.out.print("월>>");
        return InputUtil.INSTANCE.inputMenuNum(1,12);
    }
    public int inputDay(){
        System.out.print("일>>");
        return InputUtil.INSTANCE.inputMenuNum(1,31);
    }
    public int inputHour(){
        System.out.print("시간>>");
        return InputUtil.INSTANCE.inputMenuNum(0,23);
    }
    public int inputMinute(){
        System.out.print("분>>");
        return InputUtil.INSTANCE.inputMenuNum(0,59);
    }
    public String inputId(){
        System.out.print("1.id를 입력해주세요(영문+숫자)");
        return InputUtil.INSTANCE.inputStr(1,3,12);
    }
    public String inputPwd(){
        System.out.print("2.pwd를 입력해주세요(영문+숫자)");
        return InputUtil.INSTANCE.inputStr(1,5,18);
    }
    public String inputName(){
        System.out.print("3.이름을 입력해주세요");
        return InputUtil.INSTANCE.inputStr(2,6,false);
    }
    public String inputPhone(){
        System.out.print("4.전화번호를 입력해주세요");
        return InputUtil.INSTANCE.inputStr(8,12,true);
    }
    public String inputEmail(){
        System.out.print("5.이메일을 입력해주세요");
        return InputUtil.INSTANCE.inputStr(6,18,"@");
    }
    public String inputKeyword(){
        System.out.print("검색할 키워드를 입력해주세요\n");
        return InputUtil.INSTANCE.inputStr(1,12);
    }
    public String inputMovieName(){
        System.out.print("1.영화제목을 입력해주세요");
        return InputUtil.INSTANCE.inputStr(1,12);
    }
    public String inputMovieStory(){
        System.out.print("2.간단한 줄거리를 입력해주세요(100자이하)");
        return InputUtil.INSTANCE.inputStr(1,100);
    }
    public String inputMovieDirector(){
        System.out.print("3.영화감독 이름을 입력해주세요");
        return InputUtil.INSTANCE.inputStr(1,12,false);
    }
    public int inputMovieRuntime(){
        System.out.print("4.영화의 런타임을 입력해주세요(분 단위입력)");
        return InputUtil.INSTANCE.inputMenuNum(60,360);
    }
}
