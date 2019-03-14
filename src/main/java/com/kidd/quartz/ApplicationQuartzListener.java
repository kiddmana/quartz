package com.kidd.quartz;

import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.kidd.quartz.entity.AppInfo;
import com.kidd.quartz.entity.AppInfoMapper;
import com.kidd.quartz.service.QuartzManager;

/**
 * 
 * @author wangding
 * 时间：2019年3月9日 下午1:51:58
 * 系统初始化时添加定时任务
 */
@Configuration
public class ApplicationQuartzListener implements ApplicationListener<ApplicationReadyEvent> {
  @Autowired
  private QuartzManager quartzManager;
  @Autowired
  private AppInfoMapper appInfoMapper;
//  @Override
//  public void onApplicationEvent(ContextRefreshedEvent event) {
//    quartzManager.startJobs();
//    List<AppInfo> appInfos = appInfoMapper.getAppInfoListByString("2,3,5");
//    System.out.println("=================================appInfo " + appInfos.size() + " - " + appInfos.get(0).getAppVersionName());
//  }
  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
  quartzManager.startJobs();
  List<AppInfo> appInfos = appInfoMapper.getAppInfoListByString("2,3,5");
  System.out.println("=================================appInfo " + appInfos.size() + " - " + appInfos.get(0).getAppVersionName());
    
  }
  
}
