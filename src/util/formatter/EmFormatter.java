package util.formatter;

import util.Util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum EmFormatter implements Util {
    INSTANCE;

    EmFormatter() {}

    public String formatDate(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    public String formatDate(Timestamp timestamp){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(timestamp);
    }
    public String formatTime(int minute){
        if(0<=minute&&minute<60){
            return minute+"분";
        }
        else if(minute>=60){
            if(minute%60==0){
                return String.format("%dH    ",minute/60);
            }
            return String.format("%dH %2dM",minute/60,minute%60);
        }
        return null;
    }
    public String formatText(String str , int max){
        if(str.length()<max||max<0||max>100){
            return str;
        }
        String result = "";
        int line = str.length()/max;
        for(int i=1;i<=line;i++){
            if(i==1){
                result+=str.substring(((i-1)*max),(max*i))+"\n";
            }
            else{
                result+="              "+str.substring(((i-1)*max),(max*i))+"\n";
            }
            if(i==line){
                result+="              "+str.substring((max*i));
            }
        }
        return result;
    }
}
