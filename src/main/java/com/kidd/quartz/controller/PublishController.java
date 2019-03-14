package com.kidd.quartz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.kidd.quartz.entity.BlankJob;
import com.kidd.quartz.entity.User;
import com.kidd.quartz.service.QuartzDateUtils;
import com.kidd.quartz.service.QuartzManager;

@RestController
@EnableSwagger2
@Api(value="定时任务创建类")
public class PublishController {
  
  @Autowired
  private QuartzManager quartzManager;
  @Autowired
  private Scheduler scheduler;
  
  @ApiOperation(value="这是一个添加定时任务的操作",notes="动态添加定时任务处理")
  @RequestMapping(value = "/pullsms",method=RequestMethod.GET)
  public String pullSMS(){
    BlankJob blankJob = new BlankJob();
    String name = "pull1";
    String groupName = "pullGroup";
    String triggerName = "trigger1";
    String triggerGroupName = "triggerGroup";
    long time = System.currentTimeMillis() + 8000;
    Date day = new Date(time);
    String cron = QuartzDateUtils.date2Cron(day);
    blankJob.setName("job" + cron);
    User user = new User();
    user.setName("kidd k");
    user.setAge(10);
    quartzManager.addJob(name, groupName, triggerName, triggerGroupName, blankJob.getClass(), cron, user);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return "推送成功" + format.format(day);
  }
  
  @ApiOperation(value="这是一个修改定时任务的操作")
  @RequestMapping(value="/pullsms1",method=RequestMethod.GET)
  public String pullSMS1(){
    BlankJob blankJob = new BlankJob();
    String name = "pull1";
    String groupName = "pullGroup";
    String triggerName = "trigger1";
    String triggerGroupName = "triggerGroup";
    long time = System.currentTimeMillis() + 3000;
    Date day = new Date(time);
    String cron = QuartzDateUtils.date2Cron(day);
    blankJob.setName("job" + cron);
    quartzManager.modifyJobTime(name, groupName, triggerName, triggerGroupName, cron);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return "修改推送成功" + format.format(day);
  }
  
  @ApiOperation(value="这是一个删除定时任务的操作")
  @RequestMapping(value="/pullsms2",method=RequestMethod.GET)
  @ApiImplicitParam(name="name2",value="请求的线程名字",required=true)
  public String pullSMS2(String name2){
    BlankJob blankJob = new BlankJob();
    String name = "pull1";
    String groupName = "pullGroup";
    String triggerName = "trigger1";
    String triggerGroupName = "triggerGroup";
    long time = System.currentTimeMillis() + 3000;
    Date day = new Date(time);
    String cron = QuartzDateUtils.date2Cron(day);
    blankJob.setName("job" + cron);
    quartzManager.removeJob(name, groupName, triggerName, triggerGroupName);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return "移除推送成功" + format.format(day);
  }
}
