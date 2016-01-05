package com.vrv.cems.service.block.task.test;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sys.common.util.xml.XmlUtils;
import com.vrv.cems.service.base.bean.TimerBean;
import com.vrv.cems.service.block.bean.BlockTaskBean;
import com.vrv.cems.service.block.bean.DeviceBean;
import com.vrv.cems.service.block.task.TaskQueue;
import com.vrv.cems.service.block.utils.SystemConstants;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class TestTaskQueue {
	@Before
	public void onlyOnce(){
		DeviceBean deviceBean = new DeviceBean();
		deviceBean.setIp("192.168.11.52");
		deviceBean.setMac("ddddddddd");
		deviceBean.setUdpPort("8989");
		BlockTaskBean taskBean = new BlockTaskBean();
		taskBean.setBlockTargetDevice(deviceBean);
		TaskQueue.addTask(taskBean);
		Thread thread = new Thread(new Runnable() {
			public void run() {
					while(!TaskQueue.isEmpty()){
						BlockTaskBean blockTaskBean = TaskQueue.poll();
						System.out.println(blockTaskBean.getBlockTargetDevice().getIp() + "----");
					}
				
			}
		});
		thread.start();
	}
	@Test
	public void addTaskTest1(){
		DeviceBean deviceBean = new DeviceBean();
		deviceBean.setIp("192.168.22.52");
		deviceBean.setMac("ddddddddd");
		deviceBean.setUdpPort("8989");
		BlockTaskBean taskBean = new BlockTaskBean();
		taskBean.setBlockTargetDevice(deviceBean);
		TaskQueue.addTask(taskBean);
	}
	@Test
	public void addTaskTest2(){
		DeviceBean deviceBean = new DeviceBean();
		deviceBean.setIp("192.168.33.52");
		deviceBean.setMac("ddddddddd");
		deviceBean.setUdpPort("8989");
		BlockTaskBean taskBean = new BlockTaskBean();
		taskBean.setBlockTargetDevice(deviceBean);
		TaskQueue.addTask(taskBean);
	}
	
}
