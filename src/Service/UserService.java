package Service;

import dto.User;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    //db 열어서 회원가입
    public User selectOne(String id) throws SQLException {
        String sql = "select * from user where id=?";
        Connection conn = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString(1));
        user.setPwd(rs.getString(2));
        user.setName(rs.getString(3));
        user.setPhone(rs.getString(4));
        user.setEmail(rs.getString(5));
        user.setRegDate(rs.getDate(6));
        return user;
    }
}
