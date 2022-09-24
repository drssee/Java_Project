package util;

import view.Errorable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public enum InputUtil implements Errorable {
    INSTANCE;
    BufferedReader bufferedReader;
    private InputUtil(){
        this.bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    }

    public void close(){
        try {
            bufferedReader.close();
        } catch (IOException e) {
            printError("close실패");
            System.exit(0);
        }
    }
    public Integer inputMenuNum(int maxNum){//한자리정수 0~9입력받는 메서드
        char numC = (char)(maxNum+'0');
        String tmp="";
        Integer result = 0;

        try {
            tmp = bufferedReader.readLine();
            if(tmp==null||"".equals(tmp)||tmp.length()!=1||(tmp.charAt(0)<'0'||tmp.charAt(0)>numC)){
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
            tmp = bufferedReader.readLine();
            if(tmp==null||"".equals(tmp)||minNum>Integer.valueOf(tmp)||maxNum<Integer.valueOf(tmp)){
                printError(minNum+"에서 "+maxNum+"사이의 값을 입력해주세요");
                tmp = String.valueOf(inputMenuNum(minNum,maxNum));
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

    public String inputStr(int minNum,int maxNum){
        String tmp="";
        try {
            tmp = bufferedReader.readLine();
            if(tmp==null||"".equals(tmp)||tmp.length()<minNum||tmp.length()>maxNum){
                printError(minNum+"에서 "+maxNum+"사이의 값을 입력해주세요");
                tmp = inputStr(minNum,maxNum);
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

    public Calendar inputCal(int year,int month,int day,int hour,int min){
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
}
