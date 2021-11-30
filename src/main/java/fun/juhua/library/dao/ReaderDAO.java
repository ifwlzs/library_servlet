package fun.juhua.library.dao;

import fun.juhua.library.entity.Reader;

import java.util.List;

/**
 * @program: library
 * @description:
 * @author:
 * @create: 2021-10-26 08:54
 **/
public interface ReaderDAO {
    //增加读者
    public Integer addReader(Reader reader);

    //删除读者
    public Integer delReader(Reader reader);

    //通过id删除读者
    public Integer delReaderByID(String id);

    //更新读者信息
    public Integer updateReader(Reader reader);

    //查询读者
    public List<Reader> getAllReader();

    //通过id查询读者
    public Reader getReaderByID(String id);

    //通过用户名查询读者
    public List<Reader> getReaderByName(String name);

    //根据账密查询
    public Reader findReader(String id, String password);


}
