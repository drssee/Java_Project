package formatter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum NumberFormatter {
    INSTANCE;

    NumberFormatter() {
    }

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
}
