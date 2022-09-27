package dto;

import formatter.DateFormatter;

import java.sql.Timestamp;

public class Reservation {
    private int rno;
    private String title;
    private Timestamp schedule;
    private int seatNum;

    public Reservation() {
    }

    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getSchedule() {
        return schedule;
    }

    public void setSchedule(Timestamp schedule) {
        this.schedule = schedule;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "rno='" + rno + '\'' +
                ", title='" + title + '\'' +
                ", 상영스케줄: "+ DateFormatter.INSTANCE.formatDate(getSchedule())+"(시간:분)" +
                ", seatNum=" + seatNum +
                '}';
    }
}
