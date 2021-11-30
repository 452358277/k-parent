package com.ppmg.ei.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QuarterDateUtils {

    /**
     * 获取当前季度
     *
     */
    public static String getQuarter() {
        Calendar c = Calendar.getInstance();
        int month = c.get(c.MONTH) + 1;
        int quarter = 0;
        if (month >= 1 && month <= 3) {
            quarter = 1;
        } else if (month >= 4 && month <= 6) {
            quarter = 2;
        } else if (month >= 7 && month <= 9) {
            quarter = 3;
        } else {
            quarter = 4;
        }
        return quarter + "";

    }


        /**
         * 获取某季度的第一天
         *
         * @param num第几季度
         */
    public static String getCurrQuarter(int num) {
        String quarterOne = "";
        String str = "";
        // 设置本年的季
        Calendar quarterCalendar = null;
        switch (num) {
            case 1: // 本年到现在经过了一个季度，在加上前4个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 3);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
             /*   s[0] = str.substring(0, str.length() - 5) + "01-01";
                s[1] = str;*/
                quarterOne = str.substring(0, str.length() - 5) + "01-01";
                break;
            case 2: // 本年到现在经过了二个季度，在加上前三个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 6);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
             /*   s[0] = str.substring(0, str.length() - 5) + "04-01";
                s[1] = str;*/
                quarterOne = str.substring(0, str.length() - 5) + "04-01";
                break;
            case 3:// 本年到现在经过了三个季度，在加上前二个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 9);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
             /*   s[0] = str.substring(0, str.length() - 5) + "07-01";
                s[1] = str;*/
                quarterOne = str.substring(0, str.length() - 5) + "07-01";
                break;
            case 4:// 本年到现在经过了四个季度，在加上前一个季度
                quarterCalendar = Calendar.getInstance();
                str = formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                quarterOne = str.substring(0, str.length() - 5) + "10-01";
                break;
        }
        return quarterOne;
    }

    /**
     * 获取某季度的第一天
     *
     * @param num第几季度
     */
    public static String getCurrQuarterT(int num) {
        String quarterOne = "";
        String str = "";
        // 设置本年的季
        Calendar quarterCalendar = null;
        switch (num) {
            case 1: // 本年到现在经过了一个季度，在加上前4个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 3);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
             /*   s[0] = str.substring(0, str.length() - 5) + "01-01";
                s[1] = str;*/
                quarterOne = str.substring(0, str.length() - 5) + "01-20";
                break;
            case 2: // 本年到现在经过了二个季度，在加上前三个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 6);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
             /*   s[0] = str.substring(0, str.length() - 5) + "04-01";
                s[1] = str;*/
                quarterOne = str.substring(0, str.length() - 5) + "04-20";
                break;
            case 3:// 本年到现在经过了三个季度，在加上前二个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 9);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
             /*   s[0] = str.substring(0, str.length() - 5) + "07-01";
                s[1] = str;*/
                quarterOne = str.substring(0, str.length() - 5) + "07-20";
                break;
            case 4:// 本年到现在经过了四个季度，在加上前一个季度
                quarterCalendar = Calendar.getInstance();
                str = formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                quarterOne = str.substring(0, str.length() - 5) + "10-20";
                break;
        }
        return quarterOne;
    }

    public static String formatDate(Date currentDate, String pattern) {
        if (currentDate == null || "".equals(pattern) || pattern == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(currentDate);

    }


}