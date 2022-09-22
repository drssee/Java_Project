package service;

import dto.User;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.stream.IntStream;

public class UserService { //서비스 인터페이스 상속
    User isValidUser(User user){
        //isempty() , isblank() 활용
        if(user.getId()==null||"".equals(user.getId())
                ||user.getPwd()==null||"".equals(user.getPwd())
                ||user.getName()==null||"".equals(user.getName())
                ||user.getName().length()<2||user.getName().length()>6
                ||user.getPhone()==null||"".equals(user.getPhone())
                ||user.getPhone().length()<10||user.getPhone().length()>16
                ||user.getEmail()==null||"".equals(user.getEmail())
                ||user.getEmail().length()<6||user.getEmail().length()>20
                ||user.getRegDate()==null||user.getRegDate().after(new Date())){
            return null;
        }
        return user;
    }
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

        return isValidUser(user);
    }


}
