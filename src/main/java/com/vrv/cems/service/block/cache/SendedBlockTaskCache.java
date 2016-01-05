package com.vrv.cems.service.block.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vrv.cems.service.block.bean.DeviceBean;

/** 
 *   <B>说       明</B>:已发送的阻断任务缓存
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class SendedBlockTaskCache {
	private static Map<DeviceBean,List<DeviceBean>> blockDeviceBeans = new HashMap<DeviceBean, List<DeviceBean>>();
	public synchronized static void put(DeviceBean bean, List<DeviceBean> beans){
		blockDeviceBeans.put(bean,beans);
	}
	public static void putAll(Map<DeviceBean,List<DeviceBean>> map){
		blockDeviceBeans.putAll(map);
	}
	public static boolean contains(DeviceBean bean){
		return blockDeviceBeans.containsKey(bean);
	}
	public static boolean isEmpty(){
		return blockDeviceBeans.isEmpty();
	}
	public static List<DeviceBean> get(DeviceBean bean){
		return blockDeviceBeans.get(bean);
	}
	public synchronized static void remove(DeviceBean bean){
		blockDeviceBeans.remove(bean);
	}
	public static Map<DeviceBean,List<DeviceBean>> getMap(){
		return blockDeviceBeans;
	}
}
