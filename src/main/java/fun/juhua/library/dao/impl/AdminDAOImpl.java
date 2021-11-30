package fun.juhua.library.dao.impl;

import fun.juhua.library.dao.AdminDAO;
import fun.juhua.library.entity.Admin;
import fun.juhua.library.util.CommonUtils;

import java.util.List;

/**
 * @program: library
 * @description: AdminDao实现类
 * @author:
 * @create: 2021-10-26 11:01
 **/
public class AdminDAOImpl extends CommonUtils<Admin> implements AdminDAO {
    @Override
    public Integer addAdmin(Admin admin) {
        String sql = "insert into admin(id,name,password,gender,telephone,email) values(?,?,?,?,?,?)";
        int result = CommonUtils.updateCommon(sql, admin.getId(), admin.getName(), admin.getPassword(), admin.getGender()
                , admin.getTelephone(), admin.getEmail());
        return result;
    }

    @Override
    public Integer delAdmin(Admin admin) {
        return delAdminByID(admin.getId());
    }

    @Override
    public Integer delAdminByID(String id) {
        String sql = "delete from admin where id=?";
        return CommonUtils.updateCommon(sql, id);
    }

    @Override
    public Integer updateAdmin(Admin admin) {
        String sql = "update admin set name=?,password=?,gender=?,telephone=?,email=? where id=?";
        int result = CommonUtils.updateCommon(sql, admin.getName(), admin.getPassword(), admin.getGender()
                , admin.getTelephone(), admin.getEmail(), admin.getId());
        return result;
    }

    @Override
    public List<Admin> getAllAdmin() {
        String sql = "select * from admin";
        List<Admin> admins = super.queryCommon(sql, Admin.class);
        return admins;
    }

    @Override
    public Admin getAdminByID(String id) {
        String sql = "select * from admin where id=? ";
        List<Admin> admins = super.queryCommon(sql, Admin.class, id);
        //如果取到数据，则返回该对象
        if (admins.size() > 0) {
            return admins.get(0);
        }
        //取不到数据，则返回null
        return null;
    }

    @Override
    public List<Admin> getAdminByName(String name) {
        String sql = "select * from admin where name LIKE ?";
        List<Admin> list = super.queryCommon(sql, Admin.class, "%" + name + "%");
        return list;
    }

    @Override
    public Admin findAdmin(String id, String password) {
        String sql = "select * from admin where id=? and password=?";
        List<Admin> list = super.queryCommon(sql, Admin.class, id, password);
        if (list.size() > 0) {
            //System.out.println("AdminDAOImpl -> findAdmin(68): " + list.get(0));
            return list.get(0);
        }
        return null;
    }
}
