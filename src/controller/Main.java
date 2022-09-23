package controller;

import dto.Movie;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.sql.PreparedStatement;

public class Main { //관리자로그인 추가 // 유저 로그인 성공 화면 // 관리자 로그인 성공 화면
    public static void main(String[] args) throws Exception {
        //관리자모드 2번 할차례 //
        // movie schedule의 논리적 오류 :
        // 오픈날짜보다 상영날짜가 과거이면 안됨
        new MainController();
//        Movie movie = new Movie();
//        movie.setTitle("ddd");
//        movie.setStory("dddd");
//        movie.setDirector("ddd");
//        movie.setRuntime(3);
//        Calendar cal = Calendar.getInstance();
//        cal.set(2022,02,01);
//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(2022,01,01,18,59,00);
//        Timestamp ts = new Timestamp(cal2.getTimeInMillis());
//        movie.setOpenDate(cal.getTime());
//        movie.setSchedule(ts);
//        String sql = "insert into movie (title, story, director, runtime, opendate, schedule)\n" +
//                "values (?,?,?,?,?,?)";
//        int rowCnt;
//        Connection conn = ConnectionUtil.INSTANCE.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setString(1,movie.getTitle());
//        pstmt.setString(2,movie.getStory());
//        pstmt.setString(3,movie.getDirector());
//        pstmt.setInt(4,movie.getRuntime());
//        pstmt.setDate(5, new java.sql.Date(movie.getOpenDate().getTime()));
//        pstmt.setTimestamp(6, movie.getSchedule());
//        rowCnt =  pstmt.executeUpdate();
//        System.out.println("cnt:"+rowCnt);
//        pstmt.close();
//        conn.close();
    }//main
}
