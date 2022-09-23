package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {
    private Integer tno;
    private String title;
    private String story;
    private String director;
    private Integer runtime;
    private Date openDate;
    private Date schedule;

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

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "tno=" + tno +
                ", title='" + title + '\'' +
                ", story='" + story + '\'' +
                ", director='" + director + '\'' +
                ", runtime='" + runtime + '\'' +
                ", openDate=" + formatDate(openDate) +
                ", schedule=" + formatDate(schedule) +
                '}';
    }

    public String formatDate(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return df.format(date);
    }
}
