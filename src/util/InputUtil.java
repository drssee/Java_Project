package util;

import view.Errorable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.regex.Pattern;

public enum InputUtil implements Errorable {
    INSTANCE;
    BufferedReader bufferedReader;
    InputUtil(){
        this.bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    }

    public void close(){
        try {
            bufferedReader.close();
        } catch (IOException e) {
            printError("close실패");
        }
    }
    public Integer inputMenuNum(int maxNum){//한자리정수 0~9입력받는 메서드
        char numC = (char)(maxNum+'0');
        String tmp="";
        Integer result = 0;

        try {
            tmp = bufferedReader.readLine();
            while(tmp==null||"".equals(tmp)||tmp.length()!=1||(tmp.charAt(0)<'0'||tmp.charAt(0)>numC)){
                printError("올바른 숫자를 입력해주세요");
                tmp = String.valueOf(inputMenuNum(maxNum));
            }
            result = Integer.valueOf(tmp);
        }
        catch (NumberFormatException e) {
            printError("올바른 숫자를 입력해주세요");
            return -1;
        } catch (IOException e) {
            printError("입력에 실패하였습니다");
            return -1;
        } catch (Exception e){
            printError();
            return -1;
        }
        return result;
    }
    public Integer inputMenuNum(int minNum , int maxNum){//min~max 정수 입력받는 메서드
        String tmp="";
        Integer result = 0;
        try {
            System.out.println("(종료는 q)");
            tmp = bufferedReader.readLine();
            if("q".equals(tmp)||"Q".equals(tmp)){
                return -1;
            }
            while(tmp==null||"".equals(tmp)||minNum>Integer.valueOf(tmp)||maxNum<Integer.valueOf(tmp)){
                printError(minNum+"에서 "+maxNum+"사이의 값을 입력해주세요");
                tmp = String.valueOf(inputMenuNum(minNum,maxNum));
            }
            result = Integer.valueOf(tmp);
        }
        catch (NumberFormatException e) {
            printError("올바른 숫자를 입력해주세요");
            return inputMenuNum(minNum,maxNum);
        } catch (IOException e) {
            printError("입력에 실패하였습니다");
            return -1;
        } catch (Exception e){
            printError();
            return -1;
        }
        return result;
    }
    public Integer inputMenuNum_noQ(int minNum , int maxNum){//min~max 정수 입력받는 메서드
        String tmp="";
        Integer result = 0;
        try {
            tmp = bufferedReader.readLine();
            while(tmp==null||"".equals(tmp)||minNum>Integer.valueOf(tmp)||maxNum<Integer.valueOf(tmp)){
                printError(minNum+"에서 "+maxNum+"사이의 값을 입력해주세요");
                tmp = String.valueOf(inputMenuNum_noQ(minNum,maxNum));
            }
            result = Integer.valueOf(tmp);
        }
        catch (NumberFormatException e) {
            printError("올바른 숫자를 입력해주세요");
            return inputMenuNum_noQ(minNum,maxNum);
        } catch (IOException e) {
            printError("입력에 실패하였습니다");
            return -1;
        } catch (Exception e){
            printError();
            return -1;
        }
        return result;
    }

    public String inputStr(int minNum,int maxNum){
        String tmp="";
        try {
            tmp = bufferedReader.readLine();
            if(tmp==null||"".equals(tmp)||tmp.length()<minNum||tmp.length()>maxNum){
                if(minNum==maxNum){
                    printError(minNum+"자리 문자를 입력해주세요");
                }
                else{
                    printError(minNum+"자리에서 "+maxNum+"자리 사이의 문자를 입력해주세요");
                }
                return tmp = inputStr(minNum,maxNum);
            }
        }
        catch (IOException e) {
            printError("입력에 실패하였습니다");
            return null;
        }
        catch (Exception e){
            printError();
            return null;
        }
        return tmp;
    }
    public String inputStr(int engNumOrKorean,int minNum,int maxNum){
        String tmp = "";
        tmp = inputStr(minNum,maxNum);
        //engNumOrKorean , 1 == 영어+숫자 // engNumOrKorean , 1제외 == 한글만(자음모음조합된)
        if(engNumOrKorean==1){
            if(isEnglish_Num(tmp)){
                return tmp;
            }
            printError(minNum+"에서 "+maxNum+"사이의 영문+숫자를 입력해주세요");
            tmp=inputStr(1,minNum,maxNum);
            return tmp;
        }
        else{
            if(isKorean(tmp)){
                return tmp;
            }
            printError(minNum+"에서 "+maxNum+"사이의 한글만(자음+모음/공백불가) 입력해주세요");
            tmp=inputStr(2,minNum,maxNum);
            return tmp;
        }
    }

    public String inputStr(int minNum,int maxNum,boolean isNum){
        String tmp = "";
        tmp = inputStr(minNum,maxNum);
        if(!isNum){
            for(int i =0;i<tmp.length();i++){
                char ch = tmp.charAt(i);
                if('0'<=ch&&ch<='9'){
                    printError(minNum+"에서 "+maxNum+"사이의 문자를 입력해주세요");
                    tmp=inputStr(minNum,maxNum,isNum);
                    return tmp;
                }
            }
        }//if
        else{
            if(!isNumber(tmp)){
                printError(minNum+"에서 "+maxNum+"사이의 숫자를 입력해주세요");
                tmp=inputStr(minNum,maxNum,isNum);
                return tmp;
            }
        }
        return tmp;
    }

    public String inputStr(int minNum,int maxNum,String keyword){
        String tmp = "";
        tmp=inputStr(minNum,maxNum);
        //keyword @ == email 입력
        if("@".equals(keyword)){
            if(isValidEmail(tmp)){
                return tmp;
            }
            printError(minNum+"에서 "+maxNum+"사이의 "+keyword+"을 포함하는 올바른 이메일 형식을 입력해주세요");
            tmp = inputStr(minNum,maxNum,keyword);
            return tmp;
        }
        if(!tmp.contains(keyword)){
            printError(minNum+"에서 "+maxNum+"사이의 "+keyword+"을 포함하는 문자를 입력해주세요");
            tmp=inputStr(minNum,maxNum,keyword);
            return tmp;
        }
        return tmp;
    }

    public Calendar inputCal(int year, int month, int day, int hour, int min){
        if(year<1900||year>2024||month<1||month>12||day<1||day>31||hour<0||hour>23||min<0||min>59){
            printError("올바른 형식의 날짜를 입력해주세요");
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,day,hour,min,0);
        return cal;
    }
    public Calendar inputCal(int year,int month,int day){
        return inputCal(year,month,day,0,0);
    }

    private boolean isNumber(String str){
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    private boolean isContainsStr(String str){
        return str.contains(str);
    }
    private boolean isEnglish_Num(String str){
        //영문자 정규식 ^[a-zA-Z]*$
        //숫자 정규식 ^[0-9]*$
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            //16진수 아스키 코드로 체크
            if(c >= 0x61 && c <= 0x7A){
                //영어(소문자) ok
            }
            else if(c >=0x41 && c <= 0x5A){
                //영어(대문자) ok
            }
            else if(c >= 0x30 && c <= 0x39){
                //숫자 ok
            }
            else{
                //영어+숫자가 아닌경우
                return false;
            }
        }//for
        return true;
    }
    private boolean isKorean(String str){
        String pattern = "^[가-힣]*$";
        return Pattern.matches(pattern,str);
    }
    private boolean isValidEmail(String str){
        String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        return Pattern.matches(pattern,str);
    }
    public String any() throws IOException {
        return bufferedReader.readLine();
    }
}
