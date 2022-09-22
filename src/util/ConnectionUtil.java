package util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionUtil{
    INSTANCE;

    ConnectionUtil(){

    }

    public Connection getConnection() throws Exception {//예외는 컨트롤러에서 처리

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb"
                ,"root","root");

        return connection;
    }
}
