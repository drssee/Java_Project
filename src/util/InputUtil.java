package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public enum InputUtil {
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
            e.printStackTrace();
            System.out.println("error : close()");
            throw new RuntimeException(e);
        }
    }
    public Integer inputMenuNum(int maxNum){
        char numC = (char)(maxNum+'0');
        String tmp="";
        Integer result = 0;

        try {
            tmp = bufferedReader.readLine();
            if(tmp==null||"".equals(tmp)||tmp.length()!=1||(tmp.charAt(0)<'0'||tmp.charAt(0)>numC)){
                System.out.println("올바른 메뉴를 입력해주세요");
                tmp = String.valueOf(inputMenuNum(maxNum));
            }
            result = Integer.valueOf(tmp);
        } catch (NumberFormatException e) {
            System.out.println("error(NumberFormatException) : input()");//형식
            return -1;
        } catch (IOException e) {
            System.out.println("error(IOException) : input()");
            return -1;
        }
        return result;
    }

    public String inputStr(int minNum,int maxNum){
        String tmp="";
        try {
            tmp = bufferedReader.readLine();
            if(tmp==null||"".equals(tmp)||tmp.length()<minNum||tmp.length()>maxNum){
                System.out.println(minNum+"에서 "+maxNum+"사이의 값을 입력해주세요");
                tmp = inputStr(minNum,maxNum);
            }
        } catch (NumberFormatException e) {
            System.out.println("error : input()");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("error : input()");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tmp;
    }
}
