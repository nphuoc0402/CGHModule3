package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

    public static Connection getConnection(){
        Connection connection = null;
        String jdbcURL = "jdbc:mysql://localhost:3306/customer_manage?useSSL=false";
        String jdbcUsername = "root";
        String jdcbPassword = "NguyenVanPhuoc123";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdcbPassword);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
}
