package dao;

import domain.Movie;
import domain.PageRequest;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public Integer insertMovie_byAdmin(Movie movie) throws Exception{
        String sql = "insert into movie (title, story, director, runtime, opendate, schedule,regDate,price)\n" +
                "values (?,?,?,?,?,?,?,?)";
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
        pstmt.setInt(8,movie.getPrice());
        rowCnt =  pstmt.executeUpdate();
        pstmt.close();
        conn.close();

        return rowCnt;
    }

    @Override
    public List<Movie> selectMovieList_byAdmin(PageRequest pageRequest) throws Exception {
        List<Movie> movieList = new ArrayList<>();
        int skip = pageRequest.getSkip();
        int size = pageRequest.getSize();
        String sql = "select * from movie order by tno desc Limit ?,?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,skip);
        pstmt.setInt(2,size);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Movie movie = new Movie();
            movie.setTno(rs.getInt(1));
            movie.setTitle(rs.getString(2));
            movie.setStory(rs.getString(3));
            movie.setDirector(rs.getString(4));
            movie.setRuntime(rs.getInt(5));
            movie.setOpenDate(rs.getDate(6));
            movie.setSchedule(rs.getTimestamp(7));
            movie.setRegDate(rs.getDate(8));
            movie.setPrice(rs.getInt(9));
            movieList.add(movie);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return movieList;
    }

    @Override
    public List<Movie> SearchedMovieList_byAdmin(PageRequest pageRequest, String keyword) throws Exception {
        List<Movie> movieList = new ArrayList<>();
        int skip = pageRequest.getSkip();
        int size = pageRequest.getSize();
        String sql = "select * from movie where title Like ? order by tno desc Limit ?,?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"%"+keyword+"%");
        pstmt.setInt(2,skip);
        pstmt.setInt(3,size);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Movie movie = new Movie();
            movie.setTno(rs.getInt(1));
            movie.setTitle(rs.getString(2));
            movie.setStory(rs.getString(3));
            movie.setDirector(rs.getString(4));
            movie.setRuntime(rs.getInt(5));
            movie.setOpenDate(rs.getDate(6));
            movie.setSchedule(rs.getTimestamp(7));
            movie.setRegDate(rs.getDate(8));
            movie.setPrice(rs.getInt(9));
            movieList.add(movie);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return movieList;
    }

    @Override
    public Integer getCount_byAdmin() throws Exception {
        String sql = "select count(*) from movie";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Integer result = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return result;
    }

    @Override
    public Integer getCount_Searched_byAdmin(String keyword) throws Exception {
        String sql = "select count(*) from movie where title Like ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"%"+keyword+"%");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Integer result = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return result;
    }

    @Override
    public Integer updateMovie_byAdmin(Movie movie) throws Exception {
        String sql = "update movie set title = ? , story = ? , director = ? , " +
                "runtime = ? , opendate = ? , schedule = ? ,price = ? where tno = ?";
        int rowCnt;
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,movie.getTitle());
        pstmt.setString(2,movie.getStory());
        pstmt.setString(3,movie.getDirector());
        pstmt.setInt(4,movie.getRuntime());
        pstmt.setDate(5, new java.sql.Date(movie.getOpenDate().getTime()));
        pstmt.setTimestamp(6, movie.getSchedule());
        pstmt.setInt(7,movie.getPrice());
        pstmt.setInt(8,movie.getTno());
        rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();

        return rowCnt;
    }

    @Override
    public Integer deleteMovie_byAdmin(Movie movie) throws Exception {
        String sql = "delete from movie where tno = ?";
        int rowCnt;
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,movie.getTno());
        rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return rowCnt;
    }
}
