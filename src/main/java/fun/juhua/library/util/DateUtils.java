package fun.juhua.library.util;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @program: library
 * @description: 时间工具类，实现字符串转换成Date类型
 * @author:
 * @create: 2021-10-28 20:29
 **/
public class DateUtils {
    public Date toDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date date = formatter.parse(strDate, pos);
        return Timestamp.valueOf(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE).format(date)
        );
    }
}
