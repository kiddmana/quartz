package com.kidd.quartz.service;

import java.util.Calendar;
import java.util.Date;

public class QuartzDateUtils {
  /**
   * 时间返回定时任务表达式
   * 
   * @param date
   * @return
   */
  public static String date2Cron(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);
    String cronRex = second + " " + minute + " " + hour + " " + day + " " + month + " " + "?";
    return cronRex;
  }
}
