package com.kidd.quartz.entity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * @author wangding
 * 时间：2017年3月30日 下午2:43:30
 * 功能: 时间格式转换
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	@Override
	public void serialize(Date date, JsonGenerator gen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		synchronized(dateFormat) {
	        if(date == null){
				gen.writeStartArray();
				gen.writeEndArray();
			} else {
				String value = dateFormat.format(date);
				gen.writeString(value);					
			}
	    }
		
	}
}
