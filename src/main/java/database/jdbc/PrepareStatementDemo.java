package database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by aoyonggang on 2022/4/4.
 */
public class PrepareStatementDemo {
    public static void main(String[] args) {
         /*
        * 一、得到Connection
        * 二、得到Statement
        * 三、得到ResultSet
        * 四、rs.next()返回的是什么，我们就放回什么
        * */
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dummy?useServerPrepstmts=ture&cachePreStmts=true";
        String mysqlUsername = "root";
        String mysqlPassword = "";
        Connection con = JdbcUtils.getConn();

        /*
        * 一、得到PreparedStatement
        * 1、给出sql模板：所有的参数使用？来替代
        * 2、调用Connection方法，得到PreparedStatement
        * */
        //SELECT * FROM t_user WHERE username=? AND password=?
        String sql = "SELECT * FROM user WHERE username=?";
        String username = "aoyg";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            //调用查询方法，向数据库发送查询语句
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                    ;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
