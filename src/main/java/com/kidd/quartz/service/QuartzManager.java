package com.kidd.quartz.service;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuartzManager {
  @Autowired
  private Scheduler scheduler;

  /**
   * 添加定时任务
   * 
   * @param jobName 任务名
   * @param jobGroupName 任务组名
   * @param triggerName 触发器名
   * @param triggerGroupName 触发器组名
   * @param jobClass 任务的类类型
   * @param cron 时间设置 表达式
   * @param objects
   */
  public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cron, Object... objects) {
    try {
      // 创建任务名 任务组，任务执行类
      JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
      if (objects != null) {
        for (int i = 0; i < objects.length; i++) {
          // 传递参数
          jobDetail.getJobDataMap().put("data" + (i + 1), objects[i]);
        }
      }
      TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
      // 触发器名 触发器组名
      triggerBuilder.withIdentity(triggerName, triggerGroupName);
      triggerBuilder.startNow();
      // 触发器时间设定
      triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
      // 创建trigger对象
      CronTrigger trigger = (CronTrigger) triggerBuilder.build();
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  /**
   * 编辑定时任务
   * 
   * @param jobName 任务名
   * @param jobGroupName 任务组名
   * @param triggerName 触发器名
   * @param triggerGroupName 触发器组名
   * @param cron 时间设置 表达式
   */
  public void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron) {
    try {
      // 获取触发器
      TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
      CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
      if (trigger == null) {
        return;
      }
      String oldCron = trigger.getCronExpression();
      // 和以前的定时任务时间不一样修改
      if (!oldCron.equalsIgnoreCase(cron)) {
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        triggerBuilder.withIdentity(triggerName, triggerGroupName);
        triggerBuilder.startNow();
        // 设置触发器时间
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
        // 创建trigger对象
        trigger = (CronTrigger) triggerBuilder.build();
        scheduler.rescheduleJob(triggerKey, trigger);
      }
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 
   * @param jobName 任务名
   * @param jobGroupName 任务组名
   * @param triggerName 触发器名
   * @param triggerGroupName 触发器组名
   */
  public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
    try {
      // 获取触发器key
      TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
      //停止触发器
      scheduler.pauseTrigger(triggerKey);
      //移除触发器
      scheduler.unscheduleJob(triggerKey);
      //删除定时任务
      scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 启动所有定时任务
   */
  public void startJobs(){
    try {
      scheduler.start();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 关闭所有定时任务
   */
  public void shutdownJobs(){
    try {
      if(!scheduler.isShutdown()){
        scheduler.shutdown();
      }
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}
