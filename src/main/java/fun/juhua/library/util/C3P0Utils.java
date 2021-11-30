package fun.juhua.library.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: library
 * @description: c3p0工具类
 * @author:
 * @create: 2021-10-26 08:48
 **/
public class C3P0Utils {


    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
