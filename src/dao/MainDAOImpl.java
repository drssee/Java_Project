package dao;

import controller.MainController;
import domain.Movie;
import domain.Reservation;
import domain.User;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainDAOImpl implements MainDAO{
    public MainDAOImpl() {}

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
    public void reservation(Movie movie, Reservation reservation, User user) throws Exception {
        //selected = seatnum
        //movie = 예약할 영화
        Connection conn = null;
        PreparedStatement pstmt_user = null;
        PreparedStatement pstmt_res = null;
        String msg = "예약 도중 오류가 발생했습니다";

        try {
            String sql_user = "update user\n" +
                    "set total_payment = ?\n" +
                    "where id = ?";
            conn = ConnectionUtil.INSTANCE.getConnection();
            conn.setAutoCommit(false);
            pstmt_user = conn.prepareStatement(sql_user);
            pstmt_user.setInt(1,user.getTotal_payment());
            pstmt_user.setString(2,user.getId());
            pstmt_user.executeUpdate();

            MainController.loginedUser=user;

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
                System.out.println(msg);
                conn.rollback();
                throw new Exception(msg);
            } catch (SQLException ex) {
                throw new RuntimeException(msg);
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

    @Override
    public void deleteReservation(int rno,User user,int price) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt_user = null;
        PreparedStatement pstmt_res = null;
        String msg = "예약 취소 도중 오류가 발생 했습니다";
        try {
            String sql_user = "update user\n" +
                    "set total_payment = ?\n" +
                    "where id = ?";
            conn = ConnectionUtil.INSTANCE.getConnection();
            conn.setAutoCommit(false);
            pstmt_user = conn.prepareStatement(sql_user);
            pstmt_user.setInt(1,user.getTotal_payment()-price);
            pstmt_user.setString(2,user.getId());
            pstmt_user.executeUpdate();
            user.setTotal_payment(user.getTotal_payment()-price);
            //로그인된 유저가 활성화된 상태니 최신화를 해줌
            MainController.loginedUser=user;

            //

            String sql_res = "delete\n" +
                    "from reservation\n" +
                    "where rno = ?;";
            pstmt_res = conn.prepareStatement(sql_res);
            pstmt_res.setInt(1,rno);
            pstmt_res.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            try {
                System.out.println(msg);
                conn.rollback();
                throw new Exception(msg);
            } catch (SQLException ex) {
                throw new RuntimeException(msg);
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
    }

    @Override
    public Map<Integer, Integer> groupByGender(List<User> userList,int tno) throws Exception {
        String sql = "select gender , count(*)\n" +
                "from reservation\n" +
                "    inner join user\n" +
                "    on reservation.id = user.id\n" +
                "where tno = ?\n" +
                "group by 1\n"+
                "order by 1;";
        Map<Integer,Integer> genderMap = new HashMap<>();
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,tno);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            genderMap.put(rs.getInt(1),rs.getInt(2));
        }
        rs.close();
        pstmt.close();
        conn.close();
        return genderMap;
    }

    @Override
    public Map<Integer, Integer> groupByAge(List<User> userList,int tno) throws Exception {
        String sql = "select TRUNCATE(age/10,0) , count(*)\n" +
                "from reservation\n" +
                "    inner join user\n" +
                "    on reservation.id = user.id\n" +
                "where tno = ?\n" +
                "group by 1\n"+
                "order by 1";
        Map<Integer,Integer> ageMap = new HashMap<>();
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,tno);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            ageMap.put(rs.getInt(1),rs.getInt(2));
        }
        rs.close();
        pstmt.close();
        conn.close();
        return ageMap;
    }
}
