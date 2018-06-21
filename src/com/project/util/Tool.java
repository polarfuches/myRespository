package com.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {
    /**
     * ��ȡ�����������
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String time = pattern.format(date);
        return time;
    }
}
