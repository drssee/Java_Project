package dao;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import domain.User;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User selectOne(String id) throws Exception{
        String sql = "select * from user where id=?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        User user = null;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        user = new User();
        user.setId(rs.getString(1));
        user.setPwd(rs.getString(2));
        user.setName(rs.getString(3));
        user.setPhone(rs.getString(4));
        user.setEmail(rs.getString(5));
        user.setRegDate(rs.getDate(6));
        user.setTotal_payment(rs.getInt(7));
        rs.close();
        pstmt.close();
        conn.close();

        return user;

    }

    @Override
    public Integer insertUser(User user) throws Exception {
        String sql = "insert into webdb.user (id, pwd, name, phone, email)\n" +
                "values (?,?,?,?,?);";
        int rowCnt;
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getId());
        pstmt.setString(2,user.getPwd());
        pstmt.setString(3,user.getName());
        pstmt.setString(4,user.getPhone());
        pstmt.setString(5,user.getEmail());
        rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return rowCnt;
    }

    @Override
    public Integer updateUser(User user) throws Exception {
        String sql = "update webdb.user\n" +
                "set pwd = ? , name = ? , phone = ? , email = ? , modDate = ?\n" +
                "where id = ?;";
        int rowCnt;
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getPwd());
        pstmt.setString(2,user.getName());
        pstmt.setString(3,user.getPhone());
        pstmt.setString(4,user.getEmail());
        pstmt.setDate(5,new java.sql.Date(user.getModDate().getTime()));
        pstmt.setString(6,user.getId());
        rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return rowCnt;
    }

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
        return rs.getInt(1);
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

    @Override
    public List<Reservation> selectAll_reservation(int tno) throws Exception {
        String sql = "select * from reservation where tno=?";
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
            reservationList.add(reservation);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return reservationList;
    }

    @Override
    public Integer getReservationCount(int tno) throws Exception {
        String sql = "select count(*) from reservation where tno = ?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,tno);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int result = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return result;
    }

    @Override
    public void reservation(Movie movie,Reservation reservation,User user) {
        //selected = seatnum
        //movie = 예약할 영화
        Connection conn = null;
        PreparedStatement pstmt_user = null;
        PreparedStatement pstmt_res = null;

        try {
            String sql_user = "update webdb.user\n" +
                    "set total_payment = ?\n" +
                    "where id = ?";
            conn = ConnectionUtil.INSTANCE.getConnection();
            conn.setAutoCommit(false);

            pstmt_user = conn.prepareStatement(sql_user);
            pstmt_user.setInt(1,user.getTotal_payment());
            pstmt_user.setString(2,user.getId());
            pstmt_user.executeUpdate();

            //

            String sql_res = "insert into reservation (title, schedule, seatnum, " +
                    "tno, id, price)\n" +
                    "values (?,?,?,?,?,?);";
            pstmt_res = conn.prepareStatement(sql_res);
            pstmt_res.setString(1,reservation.getTitle());
            pstmt_res.setTimestamp(2,reservation.getSchedule());
            pstmt_res.setInt(3,reservation.getSeatNum());
            pstmt_res.setInt(4,movie.getTno());
            pstmt_res.setString(5,user.getId());
            pstmt_res.setInt(6,movie.getPrice());
            pstmt_res.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("예약 도중 오류가 발생 했습니다");
            }
        } finally {
            try {
                if(pstmt_res!=null){
                    pstmt_res.close();
                }
                if(pstmt_user!=null){
                    pstmt_user.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("reservation clsose error");
            }
        }
    }//reservation

    @Override
    public List<Reservation> selectAll_reservation_byUser(String id) throws Exception {
        String sql = "select * from reservation where schedule > now() and id=?";
        List<Reservation> reservationList = new ArrayList<>();
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,id);
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
            reservationList.add(reservation);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return reservationList;
    }
}
