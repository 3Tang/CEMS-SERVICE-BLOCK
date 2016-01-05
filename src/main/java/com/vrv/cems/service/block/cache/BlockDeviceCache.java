package com.vrv.cems.service.block.cache;

import java.util.HashSet;
import java.util.Set;

import com.vrv.cems.service.block.bean.DeviceBean;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class BlockDeviceCache {
	private static Set<DeviceBean> blockDeviceBeans = new HashSet<DeviceBean>();
	public synchronized static void add(DeviceBean bean){
		blockDeviceBeans.add(bean);
	}
	public static boolean contains(DeviceBean bean){
		return blockDeviceBeans.contains(bean);
	}
	public static boolean isEmpty(){
		return blockDeviceBeans.isEmpty();
	}
	public synchronized static boolean remove(DeviceBean bean){
		return blockDeviceBeans.remove(bean);
	}
}
