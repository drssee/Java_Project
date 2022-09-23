package controller;


import dto.User;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main { //관리자로그인 추가 // 유저 로그인 성공 화면 // 관리자 로그인 성공 화면
    public static void main(String[] args) {
        new MainController();
    }//main
//    public static void main(String[] args) throws Exception {
//        String sql = "select * from user where id = ?";
//        Connection conn = ConnectionUtil.INSTANCE.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setString(1,"admin");
//        ResultSet rs = pstmt.executeQuery();
//        System.out.println("rs.next()"+rs.next());
//        User user = new User();
//        user.setId(rs.getString(1));
//        user.setPwd(rs.getString(2));
//        user.setName(rs.getString(3));
//        user.setPhone(rs.getString(4));
//        user.setEmail(rs.getString(5));
//        user.setRegDate(rs.getDate(6));
//        System.out.println(user);
//    }
}
