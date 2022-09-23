package service;

import dto.Movie;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class AdminService {
    //검증
//    private boolean isValidMovie(Movie movie) {
//
//    }
    //등록
    public Integer insertMovie(Movie movie) throws Exception{//sqlexception class exception
        String sql = "insert into movie (title, story, director, runtime, opendate, schedule)\n" +
                "values (?,?,?,?,?,?)";
        int rowCnt;
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement  pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,movie.getTitle());
        pstmt.setString(2,movie.getStory());
        pstmt.setString(3,movie.getDirector());
        pstmt.setInt(4,movie.getRuntime());
        pstmt.setDate(5, (Date) movie.getOpenDate());
        pstmt.setDate(6, (Date) movie.getSchedule());
        rowCnt =  pstmt.executeUpdate();
        pstmt.close();
        conn.close();

        return rowCnt;
    }
}
