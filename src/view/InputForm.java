package view;

import domain.Movie;
import formatter.EmFormatter;
import util.InputUtil;

public enum InputForm {
    INSTANCE;
    InputForm(){}
    public int inputYear(){
        System.out.print("년도 ✎");
        return InputUtil.INSTANCE.inputMenuNum_noQ(1900,2024);
    }
    public int inputMonth(){
        System.out.print("월 ✎");
        return InputUtil.INSTANCE.inputMenuNum_noQ(1,12);
    }
    public int inputDay(){
        System.out.print("일 ✎");
        return InputUtil.INSTANCE.inputMenuNum_noQ(1,31);
    }
    public int inputHour(){
        System.out.print("시간 ✎");
        return InputUtil.INSTANCE.inputMenuNum_noQ(0,23);
    }
    public int inputMinute(){
        System.out.print("분 ✎");
        return InputUtil.INSTANCE.inputMenuNum_noQ(0,59);
    }
    public String inputId(){
        System.out.print("id를 입력해주세요(영문+숫자) ✎");
        return InputUtil.INSTANCE.inputStr(1,3,12);
    }
    public String inputPwd(){
        System.out.print("pwd를 입력해주세요(영문+숫자) ✎");
        return InputUtil.INSTANCE.inputStr(1,5,18);
    }
    public String inputName(){
        System.out.print("이름을 입력해주세요 ✎");
        return InputUtil.INSTANCE.inputStr(2,6,false);
    }
    public int inputGender(){
        System.out.print("성별을 입력해주세요 ✎");
        return InputUtil.INSTANCE.inputMenuNum_noQ(1,2);
    }
    public int inputAge(){
        System.out.print("나이를 입력해주세요 ✎");
        return InputUtil.INSTANCE.inputMenuNum_noQ(12,100);
    }
    public String inputPhone(){
        System.out.print("전화번호를 입력해주세요 ✎");
        return InputUtil.INSTANCE.inputStr(8,12,true);
    }
    public String inputEmail(){
        System.out.print("이메일을 입력해주세요 ✎");
        return InputUtil.INSTANCE.inputStr(6,18,"@");
    }
    public String inputKeyword(){
        System.out.print("검색할 키워드를 입력해주세요 ✎\n");
        return InputUtil.INSTANCE.inputStr(1,30);
    }
    public String inputMovieName(){
        System.out.print("영화제목을 입력해주세요 ✎");
        return InputUtil.INSTANCE.inputStr(1,12);
    }
    public String inputMovieStory(){
        System.out.print("간단한 줄거리를 입력해주세요(100자이하) ✎");
        return InputUtil.INSTANCE.inputStr(1,100);
    }
    public String inputMovieDirector(){
        System.out.print("영화감독 이름을 입력해주세요 ✎");
        return InputUtil.INSTANCE.inputStr(1,12,false);
    }
    public int inputMovieRuntime(){
        System.out.print("영화의 런타임을 입력해주세요(분 단위입력) ✎");
        return InputUtil.INSTANCE.inputMenuNum_noQ(60,360);
    }
    public String inputActor(){
        System.out.println("영화의 주연배우들을 입력해주세요(배우1,)(배우1,배우2)");
        return InputUtil.INSTANCE.inputStr(2,30,",");
    }
    public void detailMovie(Movie movie , int max , String analysisData){
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
        System.out.println("1.영화제목 - "+movie.getTitle()+" | "+"2. 감독 - "+movie.getDirector()+" | "+"3. 런타임 - "+
                EmFormatter.INSTANCE.formatTime(movie.getRuntime())+" | "+"4. 개봉일&상영스케줄 - "+
                EmFormatter.INSTANCE.formatDate(movie.getOpenDate())+" & "
                + EmFormatter.INSTANCE.formatDate(movie.getSchedule())+"\n\n"+
                "5.주연배우목록 - "+movie.getActor()+"\n\n6.이 영화를 선택한 유저들의 성별 / 연령 - "+(analysisData==null?"아직 데이터가 없습니다\n":analysisData)+"\n"+
                "7.스토리요약\n"+movie.getStory()+"\n\n");
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
    }

    public void toMenu(){
        System.out.println("메뉴로 돌아갑니다");
    }
    public void success(String msg){
        System.out.println(msg+"에 성공했습니다");
    }
    public void success1(String msg){
        System.out.println(msg+" 성공!\n");
    }
    public void yes_No(String msg){
        System.out.println("정말로 "+msg+" 하시겠습니까?y/n");
    }
    public void anyButton(){
        System.out.print("계속 하시려면 아무 버튼이나 눌러주세요");
    }
}
