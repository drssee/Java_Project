package util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionUtil{
    INSTANCE;
    private DataSource ds;
    ConnectionUtil(){
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb"
//                , "root", "root")) {
//            Class.forName("org.jdbc.cj.mysql.com");
//        } catch (Exception e){
//
//        }
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb"
                    ,"root","rlaskagus1!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
