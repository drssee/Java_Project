package util;

import java.sql.Connection;
import java.sql.DriverManager;

public enum ConnectionUtil implements Util{
    INSTANCE;

    ConnectionUtil(){}

    public Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db"
                ,"root","rlaskagus1!");

        return connection;
    }
}
