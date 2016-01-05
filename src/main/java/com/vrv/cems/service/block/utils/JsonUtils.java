package com.vrv.cems.service.block.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class JsonUtils {
	public static final String PARAM_JDATA="jdata";
	
	/**
	 * 将java对象转化成json串
	 * @param obj 被转发对象
	 * @return json字符串
	 */
	public static String getJsonStr(Object obj){
		return JSONObject.fromObject(obj).toString();
	}
	public static String getNoNullJsonStr(Object obj){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				return value == null;
			}
		});
		return JSONObject.fromObject(obj,jsonConfig).toString();
	}
	/**
	 * 将json消息转化成对象
	 * @param jsonString
	 * @param beanCalss
	 * @param cBeanCalss
	 * @return
	 */
	public static <T> T jsonToBean(String jsonString, Class<T> beanCalss,Class cBeanCalss) {
		T bean = null;
		try{
	        JSONObject jsonObject = JSONObject.fromObject(jsonString);
	        if(cBeanCalss != null){
	        	Map classMap = new HashMap();
	        	classMap.put(PARAM_JDATA,cBeanCalss); 
	        	bean = (T) JSONObject.toBean(jsonObject, beanCalss,classMap);
	        }else{
	        	bean = (T) JSONObject.toBean(jsonObject, beanCalss);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
        return bean;
    }
	
	
	public static <T> T jsonToBlockBean(String jsonString, Class<T> beanCalss,Class cBeanCalss) {
		T bean = null;
		try{
	        JSONObject jsonObject = JSONObject.fromObject(jsonString);
	        if(cBeanCalss != null){
	        	Map classMap = new HashMap();
	        	classMap.put("blockHost",cBeanCalss); 
	        	bean = (T) JSONObject.toBean(jsonObject, beanCalss,classMap);
	        }else{
	        	bean = (T) JSONObject.toBean(jsonObject, beanCalss);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
        return bean;
    }
}
