package com.kidd.quartz.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author wangding
 * 时间：2017年7月17日 上午11:15:23
 * 功能:android中的app版本更新
 */
public class AppInfo {
	private long id;
	//当前的app版本号
	private int appCode;
	//当前版本如1.10
	private String appVersionName;
	//当前app logo
	private String logo;
	//下载链接
	private String downloadUrl;
	//创建时间
	private Date createTime;
	//该版本描述
	private String des;
	//APP类型
	private String appType;
	//APP不同端
	private int appTab;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAppCode() {
		return appCode;
	}
	public void setAppCode(int appCode) {
		this.appCode = appCode;
	}
	public String getAppVersionName() {
		return appVersionName;
	}
	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonSerialize(using=JsonDateSerializer.class)  
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public int getAppTab() {
		return appTab;
	}
	public void setAppTab(int appTab) {
		this.appTab = appTab;
	}
	
}
