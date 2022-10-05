package dao;

import domain.Movie;
import domain.PageRequest;
import domain.Reservation;
import domain.User;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    public AdminDAOImpl() {
    }

    @Override
    public Integer insertMovie_byAdmin(Movie movie) throws Exception {
        String sql = "insert into movie (title, story, director, runtime, opendate, schedule,regDate,price,actor)\n" +
                "values (?,?,?,?,?,?,?,?,?)";
        int rowCnt;
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, movie.getTitle());
        pstmt.setString(2, movie.getStory());
        pstmt.setString(3, movie.getDirector());
        pstmt.setInt(4, movie.getRuntime());
        pstmt.setDate(5, new java.sql.Date(movie.getOpenDate().getTime()));
        pstmt.setTimestamp(6, movie.getSchedule());
        pstmt.setDate(7, new java.sql.Date(movie.getRegDate().getTime()));
        pstmt.setInt(8, movie.getPrice());
        pstmt.setString(9,movie.getActor());
        rowCnt = pstmt.executeUpdate();
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
        pstmt.setInt(1, skip);
        pstmt.setInt(2, size);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
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
            movie.setActor(rs.getString(10));
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
        pstmt.setString(1, "%" + keyword + "%");
        pstmt.setInt(2, skip);
        pstmt.setInt(3, size);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
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
            movie.setActor(rs.getString(10));
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
        pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Integer result = rs.getInt(1);
        rs.close();
        pstmt.close();
        conn.close();
        return result;
    }

    @Override
    public void updateMovie_byAdmin(Movie movie) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt_movie = null;
        PreparedStatement pstmt_res = null;
        String msg = "변경 도중 오류가 발생 했습니다";

        try {
            String sql_movie = "update movie set title = ? , story = ? , director = ? , " +
                    "runtime = ? , opendate = ? , schedule = ? ,price = ?,actor = ? where tno = ?";
            int rowCnt;
            conn = ConnectionUtil.INSTANCE.getConnection();
            conn.setAutoCommit(false);
            pstmt_movie = conn.prepareStatement(sql_movie);
            pstmt_movie.setString(1, movie.getTitle());
            pstmt_movie.setString(2, movie.getStory());
            pstmt_movie.setString(3, movie.getDirector());
            pstmt_movie.setInt(4, movie.getRuntime());
            pstmt_movie.setDate(5, new Date(movie.getOpenDate().getTime()));
            pstmt_movie.setTimestamp(6, movie.getSchedule());
            pstmt_movie.setInt(7, movie.getPrice());
            pstmt_movie.setString(8,movie.getActor());
            pstmt_movie.setInt(9, movie.getTno());
            rowCnt = pstmt_movie.executeUpdate();
            if (rowCnt != 1) {
                throw new Exception();
            }
//            if(rowCnt==1){ // tx 테스트용 코드
//                throw new Exception();
//            }

            //

            String sql_res = "update reservation set title = ?," +
                    "schedule = ?, price = ?, modDate = ? where tno = ?";
            pstmt_res = conn.prepareStatement(sql_res);
            pstmt_res.setString(1, movie.getTitle());
            pstmt_res.setTimestamp(2, movie.getSchedule());
            pstmt_res.setInt(3, movie.getPrice());
            pstmt_res.setTimestamp(4, new Timestamp(new java.util.Date().getTime()));
            pstmt_res.setInt(5, movie.getTno());
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
                if (pstmt_movie != null) {
                    pstmt_movie.close();
                }
                if (pstmt_res != null) {
                    pstmt_res.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("update clsose error");
            }
        }


    }

    @Override
    public void deleteMovie_byAdmin(Movie movie , List<User> userList) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt_movie = null;
        PreparedStatement pstmt_res = null;
        PreparedStatement pstmt_user = null;
        PreparedStatement pstmt_tmp1 = null;
        PreparedStatement pstmt_tmp2 = null;
        String msg = "삭제 도중 오류가 발생했습니다";

        try {
            conn = ConnectionUtil.INSTANCE.getConnection();
            conn.setAutoCommit(false);

            String sql1 = "set foreign_key_checks=0";
            pstmt_tmp1 = conn.prepareStatement(sql1);
            pstmt_tmp1.execute();

            String sql_movie = "delete from movie where tno = ?";
            pstmt_movie = conn.prepareStatement(sql_movie);
            pstmt_movie.setInt(1, movie.getTno());
            pstmt_movie.executeUpdate();

            //영화 삭제 끝


            String sql_res = "delete from reservation where tno = ?";
            pstmt_res = conn.prepareStatement(sql_res);
            pstmt_res.setInt(1, movie.getTno());
            pstmt_res.executeUpdate();

            //예약 삭제 끝


            //유저 변경
            //삭제한 예약이 아직 상영전 영화면 예매한 유저들에게 환불을 해주어야함
            String sql_user = "update user\n" +
                    "set total_payment = ?\n" +
                    "where id = ?";
            pstmt_user = conn.prepareStatement(sql_user);
            //해당하는 userList 사이즈가 0이면 아무것도 일어나지 않음
            for(int i = 0 ;i<userList.size();i++){
                User user = userList.get(i);
                pstmt_user.setInt(1, (user.getTotal_payment()-movie.getPrice()));
                pstmt_user.setString(2, user.getId());
                pstmt_user.executeUpdate();
            }
            //유저 변경 끝


            sql1 = "set foreign_key_checks=1";
            pstmt_tmp2 = conn.prepareStatement(sql1);
            pstmt_tmp2.execute();

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
                if (pstmt_movie != null) {
                    pstmt_movie.close();
                }
                if (pstmt_res != null) {
                    pstmt_res.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("delete clsose error");
            }
        }
    }

    @Override
    public List<Reservation> selectAllRes(PageRequest pageRequest) throws Exception {
        String sql = "select * from reservation order by rno desc Limit ?,?";
        List<Reservation> reservationList = new ArrayList<>();
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, pageRequest.getSkip());
        pstmt.setInt(2, pageRequest.getSize());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
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
        pstmt.setString(1, keyword);
        pstmt.setInt(2, pageRequest.getSkip());
        pstmt.setInt(3, pageRequest.getSize());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
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
        pstmt.setString(1, keywrod);
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
        pstmt.setInt(1, reservation.getSeatNum());
        pstmt.setTimestamp(2, new Timestamp(reservation.getModDate().getTime()));
        pstmt.setInt(3, reservation.getRno());
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
        pstmt.setInt(1, tno);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
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
    public List<String> selIdList_fromActivatedRes(int tno) throws Exception {
        String sql = "select id from reservation where tno = ? and schedule > now()";
        List<String> idList = new ArrayList<>();
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,tno);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            String id = rs.getString(1);
            idList.add(id);
        }
        rs.close();
        pstmt.close();
        conn.close();
        return idList;
    }
}
