package domain;

import util.formatter.EmFormatter;

import java.sql.Timestamp;
import java.util.Date;

public class Movie {
    private Integer tno;
    private String title;
    private String story;
    private String director;
    private Integer runtime;
    private Date openDate;
    private Timestamp schedule;
    private Date regDate;
    private int price;
    private String actor;

    public Movie() {}

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Timestamp getSchedule() {
        return schedule;
    }

    public void setSchedule(Timestamp schedule) {
        this.schedule = schedule;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "상영스케줄 - "+ EmFormatter.INSTANCE.formatDate(getSchedule())+"(시간:분)"+
                " | "+"런타임 - "+ EmFormatter.INSTANCE.formatTime(getRuntime())+" | "+
                "요금 - "+getPrice()+" | "+getTitle()+"\n";
    }
}
