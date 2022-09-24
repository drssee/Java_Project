package formatter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum DateFormatter {
    INSTANCE;

    DateFormatter() {
    }

    public String formatDate(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    public String formatDate(Timestamp timestamp){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return df.format(timestamp);
    }
}
