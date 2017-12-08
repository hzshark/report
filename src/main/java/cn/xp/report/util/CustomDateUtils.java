package cn.xp.report.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDateUtils {

    public static final String yyyy_MM = "yyyy-MM";

    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String HHmm = "HH:mm";

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd);
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    public static String defaultFormatDate(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, yyyy_MM_dd);
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, pattern);
    }

    public static Date parseDate(String str, String pattern) {
        if (str != null) {
            try {
                return DateUtils.parseDate(str, pattern);
            } catch (ParseException e) {
                // 忽略错误
            }
        }
        return null;
    }

    /**
     * 获取当前月的最后一天的最后后一刻
     * 
     * @return
     */
    public static Date getLastTimeOfMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 日期加减操作
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date addDate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);

        return calendar.getTime();
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse("2012-09-15 10:10:10");
        Date d2 = sdf.parse("2012-09-15 00:00:00");
        System.out.println(daysBetween(d1, d2));

        Date now = new Date();
        System.out.println(addDate(now, Calendar.DATE, 29));
    }
}
