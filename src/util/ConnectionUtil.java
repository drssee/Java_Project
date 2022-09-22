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
<<<<<<< HEAD
                ,"root","rlaskagus1!");
=======
                ,"root","root");
>>>>>>> 5288752f898e494734fe7b6b2020f88b6a7b46b6

        return connection;
    }
}
