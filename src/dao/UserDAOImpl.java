package dao;

import domain.User;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        pstmt.setTimestamp(5,user.getModDate());
        pstmt.setString(6,user.getId());
        rowCnt = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return rowCnt;
    }

}
