package database.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * Created by aoyonggang on 2022/4/4.
 */
public class NativeDemo {
    public static void main(String[] args) {
        System.out.println("Hello World!-------JavaMySqlJbdc-------------");


        JdbcUtils.mysql_url = "jdbc:mysql://localhost:3306/dummy?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        JdbcUtils.mysql_username = "root";
        JdbcUtils.mysql_password = "";
        JdbcUtils.mysql_driver = "com.mysql.jdbc.Driver";

        System.out.println("url:   " + JdbcUtils.mysql_url);

        //查询----------------------------------------------------------
        String strPassword = getPasswordByUserName("管理员8001");
        System.out.println("密码:   " + strPassword);

        //新增--------------------------------------------------------------
        //SQL语句
        String strId = UUID.randomUUID().toString();//唯一码
        String event = "event 2323";
        String callid = "callid 132343298432";
        String surveyresult = "2";
        String sn = "3121231231.02131";

        String sql = "insert into ls_satisfaction ";
        sql = sql + " (id,event1,callid,surveyresult,sn ) ";
        sql = sql + " values ('" + strId + "','" + event + "','" + callid + "','" + surveyresult + "','" + sn + "' ) ";
        int intResult=ExecuteUpdate(sql);
        System.out.println( "执行结果:   " +intResult);

    }

    private static int ExecuteUpdate(String sql) {
        int intResult = -1;
        Connection conn = JdbcUtils.getConn();
        try (Statement stmt = conn.createStatement()
        ) {
            intResult = stmt.executeUpdate(sql);
            if (intResult > 0) {
                //影响一行以上
            } else {
                //没有影响一行
            }
            conn.close();//关闭连接
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return intResult;
    }

    //查询
    private static String getPasswordByUserName(String userName) {
        //SQL语句
        String sql = "select user_password from user where user_name = " + "'" + userName + "'";
        Connection conn = JdbcUtils.getConn();
        String password = null;
        try (Statement stmt = conn.createStatement();
             //执行语句，得到结果集
             ResultSet ret = stmt.executeQuery(sql);) {

            while (ret.next()) {
                //这里只查询的密码
                password = ret.getString(1);
            }
            ret.close();
            conn.close();//关闭连接
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return password;
    }
}

