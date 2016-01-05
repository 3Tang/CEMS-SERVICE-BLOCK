package com.vrv.cems.service.block.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import com.sys.common.util.CommentedProperties;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public final class ReadConfigFileUtil {
	private static Properties props = new Properties();
	static CommentedProperties cporp=new CommentedProperties();
	/*在类初始化的时候加载配置文件*/
	static { 
		try {
				File file=new File(SystemConstants.PATH_CONFIG_PROPERTIES);
				props.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据给定的key键，读取器对应的值value
	 * @param key
	 * @return value
	 */
	public static String getValue(String key){
		return props.getProperty(key);
	}
	
	/**
	 * 根据指定的
	 * @param key
	 * @param value
	 */
	public static void setValue(String key,String value){
		props.setProperty(key, value);
	}
	/**
	 * 保存到配置文件
	 */
	public static void save(){
		File file = new File(SystemConstants.PATH_CONFIG_PROPERTIES);  
        if (!file.exists())  
            return;
        try {
        	OutputStream fos = new FileOutputStream(file); 
        	//保存，并加入注释  
			props.store(fos, "修改时间：" + new Date());
			fos.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
