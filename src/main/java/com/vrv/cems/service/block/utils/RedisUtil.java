package com.vrv.cems.service.block.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;

import com.vrv.cems.service.base.bean.cache.DeviceKeyCache;
import com.vrv.cems.service.base.interfaces.CacheService;
import com.vrv.cems.service.base.interfaces.ICacheService;



public class RedisUtil {

	public static DeviceKeyCache getPswBySession(String sessionId) throws TException {
		String password="";
		DeviceKeyCache deviceKeyCache=new DeviceKeyCache();
		CacheService.Client cacheClient = null;
		try {
			cacheClient = CacheClient.generateClient();
			CacheClient.TTransportOpen();
		} catch (TException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		if(StringUtils.isNotBlank(sessionId))
		{
			 deviceKeyCache =cacheClient.queryDeviceKeyBySessionId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEKEYBYSESSIONID, sessionId);
			 password=deviceKeyCache.getPassword();
			 if(StringUtils.isBlank(password))
			 {
				 
				 return null;
			 }
		}
		
		CacheClient.TTransportClose();
		return deviceKeyCache;
	}

}
