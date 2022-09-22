package util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionUtil{
    INSTANCE;
    private DataSource ds;
    ConnectionUtil(){

    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb"
                    ,"root","root");
        } catch (ClassNotFoundException e) {
            System.out.println("error : getConnection() driver클래스를 찾을수 없습니다");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error : getConnection()");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
