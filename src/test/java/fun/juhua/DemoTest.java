package fun.juhua;

import fun.juhua.library.entity.Reader;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: library
 * @description: 常用的测试
 * @author:
 * @create: 2021-10-26 10:15
 **/
public class DemoTest {
    @Test
    public void test1(){
        String id="001";
        String checkAdmin=id.substring(0,1);
        //System.out.println(checkAdmin);
        if(checkAdmin.equals("m")){
            //System.out.println("m");
        }else if(checkAdmin.equals("r")){
            //System.out.println("r");
        }else{
            //System.out.println("错误");
        }
    }
    @Test
    public void timeTest(){
        //System.out.println(new DateUtils().toDate("2021-12-12 10:10:00"));
    }

    @Test
    public void toList(){
        List<Reader> readerList=null;
        Reader reader = new Reader("1", "2", "3", "男", "4", "5");
        readerList=new ArrayList<>();
        readerList.add(reader);
        //System.out.println("DemoTest -> toList(49): " + readerList);
    }
}
