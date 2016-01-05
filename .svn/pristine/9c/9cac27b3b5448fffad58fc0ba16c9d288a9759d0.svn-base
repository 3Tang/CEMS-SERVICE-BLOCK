package com.vrv.cems.service.localtest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.dom4j.DocumentException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sys.common.util.PrintUtils;
import com.sys.common.util.security.CRCUtils;
import com.vrv.cems.service.base.SystemConstants;
import com.vrv.cems.service.base.interfaces.IBlockService;
import com.vrv.cems.service.block.bean.AttackDeviceInfoBean;
import com.vrv.cems.service.block.bean.BlockTaskBean;
import com.vrv.cems.service.block.bean.DBServParamBean;
import com.vrv.cems.service.block.bean.DeviceBean;
import com.vrv.cems.service.block.bean.InputBean;
import com.vrv.cems.service.block.bean.OutBean;
import com.vrv.cems.service.block.bean.ServiceParamBean;
import com.vrv.cems.service.block.cache.SendedBlockTaskCache;
import com.vrv.cems.service.block.task.TaskQueue;
import com.vrv.cems.service.block.utils.DBUtil;
import com.vrv.cems.service.block.utils.JsonUtils;
import com.vrv.cems.service.block.utils.PolicyConfigService;

public class TestSomeThing {
/*
	private ServiceParamBean newServBean;
	
	String data="";
	String dataStr="";
	@Before
	public void init()
	{
		 data="{'jdata':[{'ip':'192.168.32.222','mac':'00-01-02-03-04-05','isOpen':'0'}]}";
		 dataStr="{'maxCode':'00010300','minCode':'7','result':'0','desc':'','jdata':[{'ip':'192.168.0.10','mac':'00-00-01-02-03','blockHost':[{'devOnlyId':'66619b56795b49fe8ce1cd0d3477efda','ip':'192.168.0.11','routeIp':'192.168.0.1','udpPort':'65432',}]},{'ip':'192.168.32.10','mac':'00-00-01-02-03','blockHost':[{'devOnlyId':'66619b56795b49fe8ce1cd0d3477efda','ip':'192.168.32.9','routeIp':'192.168.32.1','udpPort':'65432',}]}]}";
	}
	
	
	@Test
	public void test()
	{
		InputBean inputBean = JsonUtils.jsonToBean(data, InputBean.class, DeviceBean.class);
		List<DeviceBean> scanInputBeans = inputBean.getJdata();
		for(DeviceBean db:scanInputBeans){
			PrintUtils.printBean(db);
		}
		
		
		OutBean onlineOutBean = JsonUtils.jsonToBean(dataStr,
				OutBean.class, AttackDeviceInfoBean.class);
		
		List<AttackDeviceInfoBean> attckDevList=new ArrayList<AttackDeviceInfoBean>();
		attckDevList=onlineOutBean.getJdata();
		
		
		for(AttackDeviceInfoBean attackBean:attckDevList)
		{		
			JSONObject jsonObject = JSONObject.fromObject(attackBean);
			
			AttackDeviceInfoBean desBean=JsonUtils.jsonToBlockBean(jsonObject.toString(),
					AttackDeviceInfoBean.class, DeviceBean.class);
			List<DeviceBean> devList=desBean.getBlockHost();
			DeviceBean deviceBean=new DeviceBean();
			deviceBean.setIp(desBean.getIp());
			deviceBean.setMac(desBean.getMac());
			
			SendedBlockTaskCache.put(deviceBean, devList);
			
		}
		
			String fileName = com.vrv.cems.service.block.utils.SystemConstants.SENDED_BLOCK_TASK_CACHE_FILE;
			System.out.println("fileName"+fileName);
			boolean  rs = false;
			ObjectOutputStream os = null;
			try {
				os = new ObjectOutputStream(new FileOutputStream(fileName,false));
				os.writeObject(SendedBlockTaskCache.getMap());
		        rs = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(os != null)	os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
	     
	
		
		
		//1,取数据库中的策略文件 转为策略对象
		DBServParamBean servParam=DBUtil.queryByServiceCode(IBlockService.SERVICE_CODE);
		
		
		
		String xmlPath= SystemConstants.PATH_POLICY_XML;
		
		
		String policyXmlStr = null;
		try {
			//服务本地策略文件
			policyXmlStr = PolicyConfigService.getInstance().policyXML2String(xmlPath);
			System.out.println("本地策略文件为"+policyXmlStr);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//db里的crcCode
		String crcCheck=CRCUtils.getCRC32StringValue(policyXmlStr);
		//数据库中字符串
		String xmlString=servParam.getContent();
		System.out.println("数据库中策略文件为"+xmlString);

		//依次取修改各种配置
		try {
			newServBean = PolicyConfigService.getInstance().modifyLocalPolicyParam(xmlString, policyXmlStr);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//回写
		try {
			PolicyConfigService.getInstance().localServicePolicyFileWrite(newServBean);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		String modifyPolicyXmlStr = null;
		try {
			modifyPolicyXmlStr = PolicyConfigService.getInstance().policyXML2String(xmlPath);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("本地策略文件为"+modifyPolicyXmlStr);
	}
	
	@After
	public void clear()
	{
		
	}*/
	
}
