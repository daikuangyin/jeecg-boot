package org.jeecg.modules.recycle.utils;


import org.jeecg.common.util.RedisUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class IDHelper {

    public static String getInfoId(RedisUtil redisUtil, String code){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        Object o = redisUtil.get(code+format);
        if(o == null){
            redisUtil.set(code + format,"001");
            Date yesterday = new Date();//取时间
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(yesterday);
            calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
            yesterday = calendar.getTime(); //这个时间就是日期往后推一天的结果
            String dateString = sdf.format(yesterday);
            redisUtil.del(code+dateString);
            return code + format+"001";
        }
        int i = Integer.parseInt(o.toString());
        redisUtil.set(code+format, StringHelper.addLZeroForPassword(String.valueOf(i + 1), 3));
        return code+format+ StringHelper.addLZeroForPassword(String.valueOf(i + 1), 3);
    }
}
