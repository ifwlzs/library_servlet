package fun.juhua.library.util;


import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @program: library
 * @description: 公共的数据库操作工具类
 * @author:
 * @create: 2021-10-26 10:38
 **/
//公共的数据库操作工具类--增删改
public class CommonUtils<T> {
    //公共类--增删改
    public static int updateCommon(String sql, Object... objs) {
        //获取连接对象，再获取执行对象，操作添加
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = DBUtils.getConnection();
            prst = conn.prepareStatement(sql);
            //遍历传入的对象的多个字段
            for (int i = 0; i < objs.length; i++) {
                prst.setObject(i + 1, objs[i]);
            }
            return prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(prst, conn);
        }
        return 0;
    }

    //公共类---查询---注意：要传一个反射对象进来
    public List<T> queryCommon(String sql, Class<T> clazz, Object... objs) {
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();  //获取连接对象
            prst = conn.prepareStatement(sql);
            //遍历传入的对象的多个字段
            for (int i = 0; i < objs.length; i++) {
                prst.setObject(i + 1, objs[i]);
            }

            resultSet = prst.executeQuery();  //获取到结果集
            //---最终将结果集的信息存储到List集合中---
            while (resultSet.next()) {  //循环一次，就是一条记录（一个对象）
                //clazz.getFields();  //私有的属性获取不到
                Field[] fields = clazz.getDeclaredFields();
                T t = clazz.newInstance();  //通过反射对象获取到实体类对象
                for (Field f : fields) {
                    String fieldName = f.getName();  //得到属性名

                    //获取字段对应值
                    Object value = null;
                    try {
                        value = resultSet.getObject(fieldName);  //如何确定获取的字段---对应上属性名
                    } catch (SQLException e) {
                        //获取映射属性中的字段名（is_admin）
                        Properties properties = new Properties();
                        try {
                            properties.load(CommonUtils.class.getClassLoader().getResourceAsStream("mapping.properties"));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        fieldName = properties.getProperty("isAdmin");
                        value = resultSet.getObject(fieldName);
                    }

                    f.setAccessible(true);  //设置权限
                    f.set(t, value);    //把值设置的对象的属性中
                }
                list.add(t);  //将对象存储到集合中
            }

        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(resultSet, prst, conn);
        }
        return list;
    }

    //-------分页获取总条数-------
    public static int getTotalCount(String sql) {
        //获取连接对象，再获取执行对象，操作添加
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            prst = conn.prepareStatement(sql);
            resultSet = prst.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);  //获取第一个字段
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(resultSet, prst, conn);
        }
        return -1;
    }


}
