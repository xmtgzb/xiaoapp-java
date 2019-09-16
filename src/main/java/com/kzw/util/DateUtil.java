package com.kzw.util;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
public class DateUtil {

    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");
        sdf.format(new Date());
        return  sdf.format(new Date());
    }
}
