package dao;

import dto.User;
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
        rs.close();
        pstmt.close();
        conn.close();

        return user;

    }
}
