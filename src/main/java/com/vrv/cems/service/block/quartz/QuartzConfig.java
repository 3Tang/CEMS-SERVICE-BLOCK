package com.vrv.cems.service.block.quartz; 


import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.sys.common.util.DateUtils;
import com.sys.common.util.xml.XmlUtils;
import com.vrv.cems.service.base.bean.TimerBean;
import com.vrv.cems.service.block.bean.DeviceBean;
import com.vrv.cems.service.block.bean.ServiceParamBean;
import com.vrv.cems.service.block.cache.SendedBlockTaskCache;
import com.vrv.cems.service.block.utils.SerializableUtil;
import com.vrv.cems.service.block.utils.SystemConstants;
import com.vrv.cems.service.block.utils.XmlUtil;




/** 
 *   <B>说       明</B>:QuartzConfig
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年5月12日上午9:06:57 
 */
public class QuartzConfig  {
	private static final Logger LOGGER = Logger.getLogger(QuartzConfig.class);
	
	private static final QuartzConfig INSTANCE = new QuartzConfig();
	
	public static QuartzConfig getInstance(){
		return INSTANCE;
	}
	
	/**
	 * 任务执行器。
	 */
	private Scheduler scheduler;
	public QuartzConfig(){
		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			scheduler = sf.getScheduler();
		} catch (SchedulerException e) {
			LOGGER.error("Quartz调度器创建失败!",e);
		}
	}
	
    /**
     * 添加调度的job信息 。
     * @param jobName 任务名称
     * @param group 任务所属组名称
     * @param trigger 触发器名称
     * @param cycle 任务执行周期配置
     * @param clazz 任务类型
     */
    public void scheduleJob(String jobName,String group,String trigger,String cycle,Class<? extends Job> clazz){  
    	 try {
         	//创建任务执行工作计划
 			JobDetail jobdetail = newJob(clazz).withIdentity(jobName, group).build();
 			//设置任务执行工作计划定时器执行时间
 			CronTrigger cronTrigger = newTrigger().withIdentity(trigger, group).withSchedule(cronSchedule(cycle)).build();
 			Date date = scheduler.scheduleJob(jobdetail, cronTrigger);
 			LOGGER.info("jobName=["+jobName+"];group=["+group+"];dateTime=["+DateUtils.format(date)+"]定时器添加成功！");
          } catch (SchedulerException e) {
 			LOGGER.error("Quartz调度器关闭失败!",e);
 		}   
    }  
    /**
     * 获取CronTrigger信息。
     * @param triggerKey 触发器Key。
     * @return CronTrigger对象。
     */
    public CronTrigger getTrigger(TriggerKey triggerKey){
    	CronTrigger oldTrigger = null;
		try {
			oldTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e) {
			LOGGER.error("Quartz调度器获取CronTrigger失败!",e);
			return oldTrigger;
		}
		return oldTrigger;
    }
    /**
     * 重新恢复触发器相关的job任务 。
     * @param triggerkey 目标任务Key。
     * @param trigger 计划定时器执行时间。
     * @return 重新恢复时间。
     */
    public  Date rescheduleJob(TriggerKey triggerkey, Trigger trigger){  
        try {
			return scheduler.rescheduleJob(triggerkey, trigger);
		} catch (SchedulerException e) {
			LOGGER.error("Quartz调度器重新恢复["+triggerkey.getName()+"]任务失败!",e);
			throw new RuntimeException(e);
		}  
    }  

	private static final String POLICY_XML_JOB_NAME = "PolicyXmlJob";
	private static final String SCAN_BLOCK_JOB_NAME = "ScanBlockMemoryJob";

	
	public void init() throws FileNotFoundException {
		//把原本放在mainserver中的 启动配置 放到定时器来
	/*	Map<DeviceBean, List<DeviceBean>> map = (Map<DeviceBean, List<DeviceBean>>) SerializableUtil.fileToObject(SystemConstants.SENDED_BLOCK_TASK_CACHE_FILE);
		if(map != null)	SendedBlockTaskCache.putAll(map);*/
		
		try{
		LOGGER.info("定时器配置开始启动...");
		FileInputStream policyFileIn=new FileInputStream(SystemConstants.PATH_POLICY_XML);
		ServiceParamBean serviceBean=XmlUtil.xmlToObject(policyFileIn, ServiceParamBean.class);
		List<TimerBean> timerBeans =serviceBean.getTimers();
		if(timerBeans.isEmpty()){
			throw new IllegalArgumentException("定时器配置参数不能为空！");
		}
		
		Map<JobDetail, Set<? extends Trigger>> triggersAndJobs = new HashMap<JobDetail, Set<? extends Trigger>>();
		Set<CronTrigger> policyXmlJobTriggers = new HashSet<CronTrigger>();
		Set<CronTrigger> scanBlockJobTriggers = new HashSet<CronTrigger>();
		for(TimerBean timerBean : timerBeans){
			String jobName = timerBean.getName();
			String group =timerBean.getGroup();
			String trigger=timerBean.getTrigger();
			String cycle=timerBean.getCycle();
			if(POLICY_XML_JOB_NAME.equals(jobName)){
				scheduleJob(jobName, group, trigger, cycle, PolicyXMLJob.class);
			}
			else if(SCAN_BLOCK_JOB_NAME.equals(jobName)){
				//创建任务执行工作计划
				scheduleJob(jobName, group, trigger, cycle, ScanBlockMemoryJob.class);
			}
			else {
				LOGGER.error("["+jobName+"]为无效的定时器配置项!");
			}
		}
		scheduler.start();
		LOGGER.info("定时器配置启动完成...");
	}catch(SchedulerException se)
		{
			LOGGER.error("Quartz调度器启动失败!",se);
		}
	}


	
	public void destroy() {
		LOGGER.info("定时器配置执行销毁...");
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			LOGGER.error("Quartz调度器关闭失败!",e);
		} 
		LOGGER.info("定时器配置执行销毁完成...");
	}
	}


 