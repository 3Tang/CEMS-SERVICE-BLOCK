/**   
 * @Title: EhCacheTest.java 
 * @Package com.vrv.cems.service.block.server.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
 * @date 2015年10月9日 下午2:50:54 
 * @version V1.0   
 */
package com.vrv.cems.service.block.server.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.vrv.cems.service.block.bean.BlockResult;
import com.vrv.cems.service.block.bean.ScanProcessBean;
import com.vrv.cems.service.block.utils.EhCacheUtil;
import com.vrv.cems.service.block.utils.SystemConstants;

/**
 * @ClassName: EhCacheTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author tangtieqiao tangtieqiao@vrvmail.com.cn
 * @date 2015年10月9日 下午2:50:54
 * 
 */
public class EhCacheTest {

	@Test
	public void add() {

		String ip1 = "110.111.112.113";
		String mac1 = "10-11-12-13-14-15";
		String key1 = ip1 + ":" + mac1;
		
		String ip2= "110.111.112.114";
		String mac2 = "10-11-12-13-14-15";
		String key2 = ip2 + ":" + mac2;
		
		
		String ip3 = "110.111.112.115";
		String mac3 = "10-11-12-13-14-15";
		String key3 = ip3 + ":" + mac3;
		
		
		String ip4 = "110.111.112.116";
		String mac4 = "10-11-12-13-14-15";
		String key4 = ip4 + ":" + mac4;
		
		
		String ip5 = "110.111.112.117";
		String mac5 = "10-11-12-13-14-15";
		String key5 = ip5 + ":" + mac5;
		
		
		String ip6 = "110.111.112.118";
		String mac6 = "10-11-12-13-14-15";
		String key6 = ip6 + ":" + mac6;
		
		
		ScanProcessBean scanResult1 = new ScanProcessBean();
		scanResult1.setIp(ip1);
		scanResult1.setMac(mac1);
		
		ScanProcessBean scanResult2 = new ScanProcessBean();
		scanResult2.setIp(ip2);
		scanResult2.setMac(mac2);
		
		ScanProcessBean scanResult3 = new ScanProcessBean();
		scanResult3.setIp(ip3);
		scanResult3.setMac(mac3);
		
		
		ScanProcessBean scanResult4 = new ScanProcessBean();
		scanResult4.setIp(ip4);
		scanResult4.setMac(mac4);
		
		
		ScanProcessBean scanResult5 = new ScanProcessBean();
		scanResult5.setIp(ip5);
		scanResult5.setMac(mac5);
		
		
		ScanProcessBean scanResult6 = new ScanProcessBean();
		scanResult6.setIp(ip6);
		scanResult6.setMac(mac6);

		// 获取cachemanageer
		Long start=System.currentTimeMillis();
		EhCacheUtil.set(key1, scanResult1);
		EhCacheUtil.set(key2, scanResult2);
		EhCacheUtil.set(key3, scanResult3);
		EhCacheUtil.set(key4, scanResult4);
		EhCacheUtil.set(key5, scanResult5);
		EhCacheUtil.set(key6, scanResult6);
		Long cost=System.currentTimeMillis()-start;
		System.out.println("method 1 cost:"+cost);
		
		
		start=System.currentTimeMillis();
	    List<ScanProcessBean> scanList=new ArrayList<ScanProcessBean>();
	    scanList.add(scanResult1);
	    scanList.add(scanResult2);
	    scanList.add(scanResult3);
	    scanList.add(scanResult4);
	    scanList.add(scanResult5);
	    scanList.add(scanResult6);
	    String outterKey="scanProcess";
		Map<String,Object> spbMap=new HashMap<String,Object>();
		for(ScanProcessBean scanBean:scanList)
		{
			String innerKey = scanBean.getIp() + ":" + scanBean.getMac();
			spbMap.put(innerKey, scanBean);	
		}
		EhCacheUtil.set(outterKey, spbMap);
		Map<String,Object> sMap=(Map<String, Object>) EhCacheUtil.get(outterKey);
		for(Map.Entry<String, Object> entry : sMap.entrySet())
		{
			System.out.println("map key is"+entry.getKey()+"..."+entry.getValue());
		}
		 cost=System.currentTimeMillis()-start;
		System.out.println("method 2 cost:"+cost);
		
	
	}
	
	@Test
	public void save()
	{
		String ip6 = "110.111.112.118";
		String mac6 = "10-11-12-13-14-15";
		String key6 = ip6 + ":" + mac6;
		
		
		ScanProcessBean scanResult1 = new ScanProcessBean();
		scanResult1.setIp(ip6);
		scanResult1.setMac(mac6);
		Map<String, ScanProcessBean> spbMap1 = new HashMap<String, ScanProcessBean>();
		Map<String, ScanProcessBean> spbMap2 = new HashMap<String, ScanProcessBean>();
		String OutterKey="outterKey";
		String InnerKey="innerKey";
		
		spbMap1.put(InnerKey, scanResult1);
		spbMap2.put(InnerKey, scanResult1);
		
		//EhCacheServ.getInstance().storeScanResult(spbMap1);	
		
		  Map<String, ScanProcessBean> scanMap=(Map<String, ScanProcessBean>) EhCacheUtil.get(SystemConstants.SCAN_RESULT_LIST);
			//EhCacheServ.getInstance().storeScanResult(spbMap2);
			//EhCacheUtil.remove(SystemConstants.SCAN_RESULT_LIST);
		    System.out.println("scanMap size is"+scanMap.size());
		
	    
	  //  EhCacheUtil.closeSingletonManager();
	  
	
	  
	}
	/*
	@Test
	public void fetch()
	{
		
		if(EhCacheUtil.isExist(SystemConstants.SCAN_RESULT_LIST))
		{
			System.out.println("依然存在!");
		}
		Map<String, BlockResult> scanMap=(Map<String, BlockResult>) EhCacheUtil.get(SystemConstants.BLOCK_FAIL_LIST);
		
		System.out.println("scanMap size"+scanMap.size());
		
		String ip6 = "110.111.112.118";
		String mac6 = "10-11-12-13-14-15";
		String key6 = ip6 + ":" + mac6;
		ScanProcessBean scanResult1 = new ScanProcessBean();
		scanResult1.setIp(ip6);
		scanResult1.setMac(mac6);
		Map<String, ScanProcessBean> spbMap1 = new HashMap<String, ScanProcessBean>();
		spbMap1.put(key6, scanResult1);
		
		
		//EhCacheServ.getInstance().storeScanResult(spbMap1);
		
		
		
	}*/
}
