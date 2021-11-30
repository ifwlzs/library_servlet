package fun.juhua.library.dao.impl;

import fun.juhua.library.dao.ReaderDAO;
import fun.juhua.library.entity.Admin;
import fun.juhua.library.entity.Reader;
import fun.juhua.library.util.CommonUtils;

import java.util.List;

/**
 * @program: library
 * @description:
 * @author:
 * @create: 2021-10-26 10:57
 **/
public class ReaderDAOImpl extends CommonUtils<Reader> implements ReaderDAO {

    @Override
    public Integer addReader(Reader reader) {
        String sql = "insert into reader(id,name,password,gender,telephone,email) values(?,?,?,?,?,?)";
        int result = CommonUtils.updateCommon(sql, reader.getId(), reader.getName(), reader.getPassword(), reader.getGender()
                , reader.getTelephone(), reader.getEmail());
        return result;
    }

    @Override
    public Integer delReader(Reader reader) {
        return delReaderByID(reader.getId());
    }

    @Override
    public Integer delReaderByID(String id) {
        String sql = "delete from reader where id=?";
        return CommonUtils.updateCommon(sql, id);
    }

    @Override
    public Integer updateReader(Reader reader) {
        String sql = "update reader set name=?,password=?,gender=?,telephone=?,email=? where id=?";
        int result = CommonUtils.updateCommon(sql, reader.getName(), reader.getPassword(), reader.getGender()
                , reader.getTelephone(), reader.getEmail(), reader.getId());
        return result;
    }

    @Override
    public List<Reader> getAllReader() {
        String sql = "select * from reader";
        List<Reader> readers = super.queryCommon(sql, Reader.class);
        return readers;
    }

    @Override
    public Reader getReaderByID(String id) {
        String sql = "select * from reader where id=? ";
        List<Reader> readers = super.queryCommon(sql, Reader.class, id);
        //如果取到数据，则返回该对象
        if (readers.size() > 0) {
            return readers.get(0);
        }
        //取不到数据，则返回null
        return null;
    }

    @Override
    public List<Reader> getReaderByName(String name) {
        String sql = "select * from reader where name LIKE ?";
        List<Reader> list = super.queryCommon(sql, Reader.class, "%" + name + "%");
        return list;
    }

    @Override
    public Reader findReader(String id, String password) {
        String sql = "select * from reader where id=? and password=?";
        List<Reader> list = super.queryCommon(sql, Reader.class, id, password);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
