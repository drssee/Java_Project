package domain;

import formatter.NumberFormatter;

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

    public Movie() {
    }

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

//    @Override
//    public String toString() {
//        return "영화제목: "+getTitle()+"/ "+"감독: "+getDirector()+"/ "+"런타임: "+getRuntime()+"시간"+"/ "+ "개봉일: "+DateFormatter.INSTANCE.formatDate(getOpenDate())+"/ "+ "상영스케줄: "+DateFormatter.INSTANCE.formatDate(getSchedule())+"(시간:분)"+"/ "+ "등록일: "+DateFormatter.INSTANCE.formatDate(getRegDate())+"/ "+"가격: "+getPrice()+"/ "+"줄거리요약: "+getStory();
//    }
    @Override
    public String toString() {
        return "상영스케줄 - "+ NumberFormatter.INSTANCE.formatDate(getSchedule())+"(시간:분)"+" | "+"런타임 - "+NumberFormatter.INSTANCE.formatTime(getRuntime())+" | "+"요금 - "+getPrice()+" | "+getTitle()+"\n";
    }
}
