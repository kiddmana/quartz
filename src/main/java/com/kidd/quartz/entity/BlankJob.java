package com.kidd.quartz.entity;

import java.util.Date;
import java.util.Map.Entry;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author wangding
 * 时间：2019年3月7日 下午7:05:29
 * 任务实体
 */
@Component
public class BlankJob implements Job {
  
  private String name;
  @Autowired
  private AppInfoMapper appInfoMapper;
  
  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    JobKey key = jobExecutionContext.getJobDetail().getKey();
    JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    System.out.println(appInfoMapper);
    for(Entry<String, Object> entry:dataMap.entrySet()){
      System.out.println(entry.getKey() + " -- " + entry.getValue() + new Date());
    }
    System.out.println("key = " + key);
  }
  
  public void doNothing(){
    System.out.println(new Date() + ": blank job doing nothing...");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "BlankJob [name=" + name + "]";
  }
  
  
}
