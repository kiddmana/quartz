package com.kidd.quartz.entity;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/***
 * 
 * @author wangding
 * 时间：2017年7月17日 上午11:52:26
 * 功能: app信息描述
 */
@Mapper
public interface AppInfoMapper {
	@Insert("INSERT INTO mg_appinfo(appCode,appVersionName,logo,downloadUrl,createTime,des,appType,appTab) VALUES(#{appCode},#{appVersionName},#{logo},#{downloadUrl},NOW(),#{des},#{appType},#{appTab})")
	public int insert(AppInfo appInfo);
	
    @Select("SELECT * FROM mg_appinfo WHERE id=(SELECT LAST_INSERT_ID())")
    public AppInfo queryLastInsert();
    
    @Select("select * from mg_appinfo where appTab=#{appTab} order by createTime desc limit 1")
    public AppInfo getLastApp(@Param("appTab") int appTab);
    
    @Select("select * from mg_appinfo order by createTime desc")
    public List<AppInfo> getAppInfoList();
    
    @Select("select * from mg_appinfo where FIND_IN_SET(appCode, #{appCode})>0 AND appTab=1 order by createTime desc")
    public List<AppInfo> getAppInfoListByString(@Param("appCode")String appCode);
}
