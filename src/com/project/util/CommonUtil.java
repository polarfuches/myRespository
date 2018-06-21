package com.project.util;

import java.util.Calendar;

import com.project.controller.ImputExcelController;

public class CommonUtil {


    /**
     * 获取文件夹路径
     *
     * @return
     */
    public static String getPath() {
        String path = ImputExcelController.class.getResource("/").getPath();
        path = path.replace("%20", " ");
        path = path.substring(0, path.lastIndexOf("/WEB-INF"));

        return path;

    }

    /**
     * 生成序号
     *
     * @return 年月日时分秒
     */
    public static String getDate() {
        Calendar cal = Calendar.getInstance();
        String year = cal.get(Calendar.YEAR) + "";
        String month = cal.get(Calendar.MONTH) + 1 + "";
        String day = cal.get(Calendar.DAY_OF_MONTH) + "";
        String hour = cal.get(Calendar.HOUR_OF_DAY) + "";
        String minute = cal.get(Calendar.MINUTE) + "";
        String second = cal.get(Calendar.SECOND) + "";
        if (year.length() == 1) {
            year = "0" + year;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        if (second.length() == 1) {
            second = "0" + second;
        }
        String date = year + month + day + hour + minute + second;
        return date;
    }

    /**
     * 获取年月日
     *
     * @return
     */
    public static String getDateYMD() {
        Calendar cal = Calendar.getInstance();
        String year = cal.get(Calendar.YEAR) + "";
        String month = cal.get(Calendar.MONTH) + 1 + "";
        String day = cal.get(Calendar.DAY_OF_MONTH) + "";
        if (year.length() == 1) {
            year = "0" + year;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }

        String date = year + month + day;
        return date;
    }
}
