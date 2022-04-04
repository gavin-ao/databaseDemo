package database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by aoyonggang on 2022/4/4.
 */
public class JdbcUtils {
    //是否连接数据库
    public static String mysql_open="false";
    //数据库连接地址
    public static String mysql_url="jdbc:mysql://localhost:3306/dummy";
    //用户名
    public static String mysql_username= "root";
    //密码
    public static String mysql_password ="";//loveaoyg
    //驱动名称
    public static String mysql_driver = "com.mysql.jdbc.Driver";


    private static Connection conn = null;

    public static Connection getConn() {

        try {
            Class.forName(mysql_driver);
            conn = DriverManager.getConnection(mysql_url, mysql_username, mysql_password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            closeConn();
        }
        return conn;
    }

    public static void closeConn(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
