package domain;

import formatter.DateFormatter;

import java.sql.Timestamp;
import java.util.Date;

public class Reservation {
    private int rno;
    private String title;
    private Timestamp schedule;
    private int seatNum;
    private int tno;
    private String id;
    private int price;
    private Timestamp regDate;
    private Timestamp modDate;
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

    public int getTno() {
        return tno;
    }

    public void setTno(int tno) {
        this.tno = tno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "rno=" + rno +
                ", title='" + title + '\'' +
                ", schedule=" + schedule +
                ", seatNum=" + DateFormatter.INSTANCE.formatDate(getSchedule())+"(시간:분)"  +
                ", tno=" + tno +
                ", id='" + id + '\'' +
                ", price=" + price +
                ", regDate=" + regDate +
                ", modDate=" + modDate +
                '}';
    }
}
