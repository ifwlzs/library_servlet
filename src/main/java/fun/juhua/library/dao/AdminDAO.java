package fun.juhua.library.dao;

import fun.juhua.library.entity.Admin;
import fun.juhua.library.entity.Book;
import fun.juhua.library.entity.Borrow;
import fun.juhua.library.entity.Reader;
import fun.juhua.library.util.CommonUtils;
import javafx.scene.AmbientLight;


import java.util.List;

/**
 * @program: library
 * @description:
 * @author:
 * @create: 2021-10-26 08:54
 **/
public interface AdminDAO {
    //增加管理员
    public Integer addAdmin(Admin admin);

    //删除管理员
    public Integer delAdmin(Admin admin);

    //通过id删除管理员
    public Integer delAdminByID(String id);

    //更新管理员信息
    public Integer updateAdmin(Admin admin);

    //查询管理员
    public List<Admin> getAllAdmin();

    //通过id查询管理员
    public Admin getAdminByID(String id);


    //通过用户名查询管理员
    public List<Admin> getAdminByName(String name);

    //根据账密查询
    public Admin findAdmin(String id, String password);


}
