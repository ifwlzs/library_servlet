package fun.juhua.library.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: library
 * @description: 数据库工具类
 * @author:
 * @create: 2021-10-26 08:50
 **/
public class DBUtils {
    private static String driverName;
    private static String url;
    private static String username;
    private static String password;

//    static {
//        try {
//            //读取配置文件的信息
//            Properties properties = new Properties();
//            //从资源路径中去获取配置属性
//            InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
//            try {
//                properties.load(is);
//                driverName = properties.getProperty("driverName");
//                url = properties.getProperty("url");
//                username = properties.getProperty("username");
//                password = properties.getProperty("password");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            //加载驱动
//            Class.forName(driverName);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConnection() {
        //获取连接对象
        Connection connection = null;
        connection = C3P0Utils.getConnection();//DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void closeAll(AutoCloseable... cs) {
        if (cs != null) {  //如果只传了一个null过来，则不用进行遍历
            for (AutoCloseable c : cs) {
                if (c != null) {  //如果多个参数中有一个为null，则不用关闭
                    try {
                        c.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
