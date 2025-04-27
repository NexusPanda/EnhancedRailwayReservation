package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5434/postgres";
        String username = "postgres";
        String password = "JAVA";
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}
