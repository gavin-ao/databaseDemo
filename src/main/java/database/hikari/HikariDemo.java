package database.hikari;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库服务
 * Created by aoyg on 2022/4/4.
 */
public class HikariDemo {


    // 数据库连接数
    private short db_max_conn = 100;

    // 数据库服务器addr
    private String db_url = "127.0.0.1";

    // 数据库连接端口
    private short db_port = 3306;

    // 数据库名称
    private String db_name = "dummy";

    // 数据库登录用户名
    private String db_username = "root";

    // 数据库登录密码
    private String db_password = "loveaoyg";

    // 数据库连接
    private Connection connection;
    private HikariDataSource dataSource;
    private static HikariDemo hikariDemo;

    public static HikariDemo getInstance() {
        if (hikariDemo == null) {
            hikariDemo = new HikariDemo();
        }
        return hikariDemo;
    }

    public void start() throws IOException, SQLException {

        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(db_max_conn);
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", db_url);
        config.addDataSourceProperty("port", db_port);
        config.addDataSourceProperty("databaseName", db_name);
        config.addDataSourceProperty("user", db_username);
        config.addDataSourceProperty("password", db_password);
        dataSource = new HikariDataSource(config);
    }


    public Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.resumePool();
            return null;
        }
    }

    public boolean stop() throws SQLException {
        dataSource.close();
        return true;
    }

    public static void main(String[] args) throws IOException, SQLException {
        getInstance().start();

        // statement用来执行SQL语句
        Statement statement = getInstance().getConnection().createStatement();

        // 要执行的SQL语句id和content是表review中的项。
        String sql = "select * from users where name='aoyg'";

        // 得到结果
        ResultSet rs = statement.executeQuery(sql);

        if(rs.next()){
            System.out.println("Logon");

        }else{
            System.out.println("Login Faild");
        }
        rs.close();
    }
}
