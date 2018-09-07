package com.colin.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {

    //将当前日期转换成JuLian
    public static int dateToJuLian(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR) - 1900;
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        return year * 1000 + dayOfYear;
    }

    //juLian 转换成正常日期
    public static java.util.Date juLianToDate(int date) {
        int year = (date / 1000) + 1900;
        int dayOfYear = date % 1000;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return calendar.getTime();
    }


    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static Date getDayBefore(Date date, int before) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, before);
        date = calendar.getTime();
        return date;
    }


    public static Map<String, String> getJiDuFirstAndLast(int year, int jidu) {
        Map<String,String> map = new HashMap<String,String>();
        switch (jidu){
            case 1:
                map.put("start",getFisrtDayOfMonth(year,1));
                map.put("end",getLastDayOfMonth(year,3));
                break;
            case 2:
                map.put("start",getFisrtDayOfMonth(year,4));
                map.put("end",getLastDayOfMonth(year,6));
                break;
            case 3:
                map.put("start",getFisrtDayOfMonth(year,7));
                map.put("end",getFisrtDayOfMonth(year,9));
                break;
            case 4:
                map.put("start",getFisrtDayOfMonth(year,10));
                map.put("end",getLastDayOfMonth(year,12));
                break;
        }
            return map;
    }


    /**
     * 获取某年某月的第一天
     *
     * @throws
     * @Title:getFisrtDayOfMonth
     * @Description:
     * @param:@param year
     * @param:@param month
     * @param:@return
     * @return:String
     */
    public static String getFisrtDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获取某月的最后一天
     *
     * @throws
     * @Title:getLastDayOfMonth
     * @Description:
     * @param:@param year
     * @param:@param month
     * @param:@return
     * @return:String
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

}
