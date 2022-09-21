package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public enum InputUtil {
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
    public Integer inputNum(){
        String tmp="";
        try {
            tmp = bufferedReader.readLine();
            if(tmp==null||"".equals(tmp)||tmp.length()!=1||(tmp.charAt(0)<'0'||tmp.charAt(0)>'2')){
                System.out.println("올바른 메뉴를 입력해주세요");
                tmp = String.valueOf(inputNum());
            }
        } catch (NumberFormatException e) {
            System.out.println("error : input()");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("error : input()");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return Integer.valueOf(tmp);
    }

    public String inputStr(){
        String tmp="";
        try {
            tmp = bufferedReader.readLine();
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
