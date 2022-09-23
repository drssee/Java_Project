package util;

import view.Errorable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum InputUtil implements Errorable {
    //입력횟수 초과하면 프로그램 꺼지는 기능
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
    public Integer inputMenuNum(int maxNum){
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

    public Date inputDate(String date){
//        if(date==null||"".equals(date)){
//            return null;
//        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date rDate = null;
        try {
            rDate = df.parse(date);
        } catch (ParseException e) {
            printError("제대로된 형식의 날짜를 입력해주세요");
            return null;
        }
        return rDate;
    }
}
