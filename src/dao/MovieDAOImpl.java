package dao;

import domain.Movie;
import domain.PageRequest;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO{
    public MovieDAOImpl() {}

    @Override
    public List<Movie> selectAll_byDate(PageRequest pageRequest) throws Exception {
        String sql = "select * from movie where schedule > now() Limit ? , ?";
        List<Movie> movieList = new ArrayList<>();
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,pageRequest.getSkip());
        pstmt.setInt(2,pageRequest.getSize());
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
    public List<Movie> SearchList_byDate(PageRequest pageRequest, String keyword) throws Exception {
        List<Movie> movieList = new ArrayList<>();
        int skip = pageRequest.getSkip();
        int size = pageRequest.getSize();
        String sql = "select * from movie where schedule > now() and title Like ? order by tno desc Limit ?,?";
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
    public Integer getTotalCount() throws Exception {
        String sql = "select count(*) from movie where schedule > now()";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int result = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return result;
    }

    @Override
    public Integer getSearchedTotalCount(String keyword) throws Exception {
        String sql = "select count(*) from movie where schedule > now() and title Like ?";
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
}
