package controller;

import dto.Movie;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

public class Main { //관리자로그인 추가 // 유저 로그인 성공 화면 // 관리자 로그인 성공 화면
    public static void main(String[] args) throws Exception {
        new MainController();
//        Calendar cal = Calendar.getInstance();
//        cal.set(2022,11,01);
//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(2022,01,01,18,59,00);
//
//        Date date1 = cal.getTime(); // 2022 12 02
//        System.out.println("cal1:"+cal);
//        Timestamp ts = new Timestamp(cal2.getTimeInMillis()); //2022 01 01
//        System.out.println("cal2:"+cal2);
//
//        if(!date1.before(ts)){
//            System.out.println("ca1>cal2");
//        }


//        String sql = "insert into movie (title, story, director, runtime, opendate, schedule,regDate)\n" +
//                "values (?,?,?,?,?,?,?)";
//        int rowCnt;
//        Connection conn = ConnectionUtil.INSTANCE.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        Movie movie = new Movie();
//        movie.setTitle("title");
//        movie.setStory("story");
//        movie.setDirector("director");
//        movie.setRuntime(1);
//        Calendar cal = Calendar.getInstance();
//        cal.set(2022,05,05);
//        movie.setOpenDate(cal.getTime());
//        cal.set(2022,05,06,12,00);
//        movie.setSchedule(new Timestamp(cal.getTimeInMillis()));
//        movie.setRegDate(new Date());
//        for(int i=1;i<=100;i++){
//            pstmt.setString(1,movie.getTitle()+i);
//            pstmt.setString(2,movie.getStory()+i);
//            pstmt.setString(3,movie.getDirector()+i);
//            pstmt.setInt(4,movie.getRuntime());
//            pstmt.setDate(5, new java.sql.Date(movie.getOpenDate().getTime()));
//            pstmt.setTimestamp(6, movie.getSchedule());
//            pstmt.setDate(7,new java.sql.Date(movie.getRegDate().getTime()));
//            rowCnt =  pstmt.executeUpdate();
//        }
//        pstmt.close();
//        conn.close();
    }//main
}
