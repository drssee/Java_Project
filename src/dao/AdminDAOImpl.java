package dao;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
    public List<Movie> searchedMovieList_byAdmin(PageRequest pageRequest, String keyword) throws Exception {
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
    public Integer getMovieCnt_byAdmin() throws Exception {
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
    public Integer getMovieCnt_Searched_byAd(String keyword) throws Exception {
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

    @Override
    public List<Reservation> selectAllRes(PageRequest pageRequest) throws Exception {
        String sql = "select * from reservation order by rno desc Limit ?,?";
        List<Reservation> reservationList = new ArrayList<>();
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,pageRequest.getSkip());
        pstmt.setInt(2,pageRequest.getSize());
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.setRno(rs.getInt(1));
            reservation.setTitle(rs.getString(2));
            reservation.setSchedule(rs.getTimestamp(3));
            reservation.setSeatNum(rs.getInt(4));
            reservation.setTno(rs.getInt(5));
            reservation.setId(rs.getString(6));
            reservation.setPrice(rs.getInt(7));
            reservation.setRegDate(rs.getTimestamp(8));
            reservation.setModDate(rs.getTimestamp(9));
            reservationList.add(reservation);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return reservationList;
    }

    @Override
    public Integer getResCnt() throws Exception {
        String sql = "select count(*) from reservation";
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
    public List<Reservation> searchedResList(PageRequest pageRequest, String keyword) throws Exception {
        String sql = "select * from reservation where id = ? order by rno desc Limit ? , ?";
        List<Reservation> reservationList = new ArrayList<>();
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,keyword);
        pstmt.setInt(2,pageRequest.getSkip());
        pstmt.setInt(3,pageRequest.getSize());
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.setRno(rs.getInt(1));
            reservation.setTitle(rs.getString(2));
            reservation.setSchedule(rs.getTimestamp(3));
            reservation.setSeatNum(rs.getInt(4));
            reservation.setTno(rs.getInt(5));
            reservation.setId(rs.getString(6));
            reservation.setPrice(rs.getInt(7));
            reservation.setRegDate(rs.getTimestamp(8));
            reservation.setModDate(rs.getTimestamp(9));
            reservationList.add(reservation);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return reservationList;
    }

    @Override
    public Integer getResCnt_Searched(String keywrod) throws Exception {
        String sql = "select count(*) from reservation where id = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,keywrod);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Integer result = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return result;
    }

    @Override
    public Integer updateRes(Reservation reservation) throws Exception {
        String sql = "update reservation\n" +
                "set seatnum = ? , modDate = ?\n" +
                "where rno = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,reservation.getSeatNum());
        pstmt.setTimestamp(2,new Timestamp(reservation.getModDate().getTime()));
        pstmt.setInt(3,reservation.getRno());
        Integer rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return rowCnt;
    }

    @Override
    public List<Reservation> selectResList_byTno(int tno) throws Exception {
        String sql = "select * from reservation where tno = ?";
        List<Reservation> reservationList = new ArrayList<>();
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,tno);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.setRno(rs.getInt(1));
            reservation.setTitle(rs.getString(2));
            reservation.setSchedule(rs.getTimestamp(3));
            reservation.setSeatNum(rs.getInt(4));
            reservation.setTno(rs.getInt(5));
            reservation.setId(rs.getString(6));
            reservation.setPrice(rs.getInt(7));
            reservation.setRegDate(rs.getTimestamp(8));
            reservation.setModDate(rs.getTimestamp(9));
            reservationList.add(reservation);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return reservationList;
    }
}
