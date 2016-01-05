package com.vrv.cems.service.block.quartz;

import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.vrv.cems.service.block.ServiceMain;
import com.vrv.cems.service.block.bean.DeviceBean;
import com.vrv.cems.service.block.bean.ServiceParamBean;
import com.vrv.cems.service.block.bean.ServiceParamBean.ParamBean;
import com.vrv.cems.service.block.cache.SendedBlockTaskCache;
import com.vrv.cems.service.block.utils.SystemConstants;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class ScanBlockMemoryJob implements Job {
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String blockRuleType="";
		String blockIntervalTime="";
		if(ServiceParamBean.StaticParams!=null){
			ServiceParamBean.StaticParams=ServiceMain.serviceParamBean.getParams();
		}
		List<ParamBean> params=ServiceParamBean.StaticParams;
		for(ParamBean param:params)
		{
			if("blockRuleType".equalsIgnoreCase(param.getKey()))
			{
				blockRuleType=param.getValue();
			}
			if("blockIntervalTime".equalsIgnoreCase(param.getKey()))
			{
				blockIntervalTime=param.getValue();
			}
		}
		Map<DeviceBean,List<DeviceBean>> m = SendedBlockTaskCache.getMap();
		for(DeviceBean deviceBean : m.keySet()){
			if(blockRuleType.equals("1")){
				if(((System.currentTimeMillis() - deviceBean.getBeginDate().getTime()) / (60 * 1000)) > Long.valueOf(blockIntervalTime)){
					SendedBlockTaskCache.remove(deviceBean);
				}
			}else{//暂不实现
				
			}
		}
	}

}
