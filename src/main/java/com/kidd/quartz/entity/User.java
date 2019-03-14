package com.kidd.quartz.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String name;
  private int age;
  private Date birthDay = new Date();
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  
  public Date getBirthDay() {
    return birthDay;
  }
  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }
  @Override
  public String toString() {
    return "User [name=" + name + ", age=" + age + ", birthDay=" + birthDay + "]";
  }
  
  
  
}
