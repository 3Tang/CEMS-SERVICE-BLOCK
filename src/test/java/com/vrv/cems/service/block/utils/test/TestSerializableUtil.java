package com.vrv.cems.service.block.utils.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.vrv.cems.service.block.bean.DeviceBean;
import com.vrv.cems.service.block.cache.SendedBlockTaskCache;
import com.vrv.cems.service.block.utils.SerializableUtil;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class TestSerializableUtil {
	@Test
	public void objectToFileTest(){
		DeviceBean targetBlockDevice = new DeviceBean();
		targetBlockDevice.setIp("ssss");;
		targetBlockDevice.setBeginDate(new Date());
		targetBlockDevice.setMac("gfdfgfdfg");
		List<DeviceBean> deviceBeans = new ArrayList<DeviceBean>();
		deviceBeans.add(targetBlockDevice);
		deviceBeans.add(targetBlockDevice);
		SendedBlockTaskCache.put(targetBlockDevice, deviceBeans);
		SerializableUtil.objectToFile(SendedBlockTaskCache.getMap(), "test.txt");
	}
	@Test
	public void fileToObjectTest(){
		Map<DeviceBean, List<DeviceBean>> m = (Map<DeviceBean, List<DeviceBean>>)SerializableUtil.fileToObject("test.txt");
		for(DeviceBean bean : m.keySet()){
			System.out.println(bean.getBeginDate());
			for(DeviceBean b : m.get(bean)){
				System.out.println(b.getBeginDate()+ "=" + b.getIp());
			}
		}
	}
}
