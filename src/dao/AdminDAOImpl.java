package dao;

import dto.Movie;
import dto.PageRequest;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public Integer insert(Movie movie) throws Exception{
        String sql = "insert into movie (title, story, director, runtime, opendate, schedule,regDate)\n" +
                "values (?,?,?,?,?,?,?)";
        int rowCnt;
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,movie.getTitle());
        pstmt.setString(2,movie.getStory());
        pstmt.setString(3,movie.getDirector());
        pstmt.setInt(4,movie.getRuntime());
        pstmt.setDate(5, new java.sql.Date(movie.getOpenDate().getTime()));
        pstmt.setTimestamp(6, movie.getSchedule());
        pstmt.setDate(7,new java.sql.Date(movie.getRegDate().getTime()));
        rowCnt =  pstmt.executeUpdate();
        pstmt.close();
        conn.close();

        return rowCnt;
    }

    @Override
    public List<Movie> selectMovieList(PageRequest pageRequest) throws Exception {
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie();
        int skip = pageRequest.getSkip();
        int size = pageRequest.getSize();
        String sql = "select * from movie order by tno desc Limit ?,?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,1);
        pstmt.setInt(2,2);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            movie.setTno(rs.getInt(1));
            movie.setTitle(rs.getString(2));
            movie.setStory(rs.getString(3));
            movie.setDirector(rs.getString(4));
            movie.setRuntime(rs.getInt(5));
            movie.setOpenDate(rs.getDate(6));
            movie.setSchedule(rs.getTimestamp(7));
            movie.setRegDate(rs.getDate(8));
            movieList.add(movie);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return movieList;
    }

    @Override
    public Integer getCount() throws Exception {
        String sql = "select count(*) from movie";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Integer result = rs.getInt(1);
        return result;
    }
}
