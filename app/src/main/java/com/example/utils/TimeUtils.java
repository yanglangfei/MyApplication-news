package com.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * 日期Util类
 *
 * @author calvin
 */
public class TimeUtils {
    private static String defaultDatePattern = "yyyy-MM-dd ";
    private static int age;


    /**
     * @param date
     * @return 判断时间是否是今日
     */
    public static boolean isToday(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date comDate = sdf.parse(date);
            Date currrent = new Date();
            int comYear = comDate.getYear();
            int comMoth = comDate.getMonth();
            int comDay = comDate.getDate();
            int currentYear = currrent.getYear();
            int currentMoth = currrent.getMonth();
            int currentDay = currrent.getDate();
            if (currentYear == comYear && currentDay == comDay && currentMoth == comMoth) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }




    public static int createRandom(int count){
        StringBuffer buffer=new StringBuffer();
        Random random=new Random();
        for(int i=0;i<count;i++){
            int ran=random.nextInt(9);
            buffer.append(ran);
        }
        return  Integer.parseInt(buffer.toString());
    }


    /**
     * @param date
     * @return 判断是否是本月
     */
    public static boolean isMoth(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date comDate = sdf.parse(date);
            Date currrent = new Date();
            int comYear = comDate.getYear();
            int comMoth = comDate.getMonth();
            int currentYear = currrent.getYear();
            int currentMoth = currrent.getMonth();
            if (currentYear == comYear && currentMoth == comMoth) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param birth
     * @return 计算两个时间相隔年份
     */
    public static int getAge(String birth) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(defaultDatePattern);
            Date birthday = sdf.parse(birth);
            long birthM = birthday.getTime();
            long thisM = new Date().getTime();
            age = (int) ((thisM - birthM) / (1000 * 60 * 60 * 24) / 356);
            return age;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }

    /**
     * 返回预设Format的当前日期字符串
     */
    public static String getToday() {
        Date today = new Date();
        return format(today);
    }

    /**
     * 使用预设Format格式化Date成字符串
     */
    public static String format(Date date) {
        return date == null ? " " : format(date, getDatePattern());
    }

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) throws ParseException {
        return StringUtil.isBlank(strDate) ? null : parse(strDate,
                getDatePattern());
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern)
            throws ParseException {

        return StringUtil.isBlank(strDate) ? null : new SimpleDateFormat(
                pattern).parse(strDate);
    }



    public static String parseStrDate(String date,String pattern){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(pattern);
            Date time=sdf.parse(date);
            return sdf.format(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * 在日期上增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    public static String getLastDayOfMonth(String year, String month) {
        Calendar cal = Calendar.getInstance();
        // 年
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        // 月，因为Calendar里的月是从0开始，所以要-1
        // cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        // 日，设为一号
        cal.set(Calendar.DATE, 1);
        // 月份加一，得到下个月的一号
        cal.add(Calendar.MONTH, 1);
        // 下一个月减一为本月最后一天
        cal.add(Calendar.DATE, -1);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
    }

    public static Date getDate(String year, String month, String day)
            throws ParseException {
        String result = year + "- "
                + (month.length() == 1 ? ("0 " + month) : month) + "- "
                + (day.length() == 1 ? ("0 " + day) : day);
        return parse(result);
    }
}