package com.vrv.cems.service.block.task;

import java.io.File;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.sys.common.util.CommentedProperties;
import com.sys.common.util.xml.XmlUtils;
import com.vrv.cems.service.base.bean.TimerBean;
import com.vrv.cems.service.block.utils.SystemConstants;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class TaskConfig {
	public static void config(){
		File xmlFile=new File(SystemConstants.PATH_TIMER_XML);
		List<TimerBean> timerBeans= XmlUtils.xml2Collection(xmlFile, TimerBean.class);
		String heartTime="";
		String scanTime="";
		for(TimerBean timerBean:timerBeans){
			if(timerBean.getName().equals("HeartTaskJob")){
				heartTime=timerBean.getCycle();
			}else if(timerBean.getName().equals("ScanBlockMemoryJob")){
				scanTime=timerBean.getCycle();
			}
		}
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			JobDetail job = null;
			Trigger trigger = null;
//			job = JobBuilder.newJob(HeartTask.class).withIdentity("HeartTaskJob","group1").build();
//			trigger = TriggerBuilder.newTrigger()
//				    .withIdentity("HeartTaskTrigger", "group1")
//				    .withSchedule(CronScheduleBuilder.cronSchedule(heartTime))
//				    .withPriority(9).forJob(job)
//				    .build();
//			sched.scheduleJob(job,trigger);
//			注册服务失败自行注册，不用定时器启动
			job = JobBuilder.newJob(ScanBlockMemoryTask.class).withIdentity("ScanBlockMemoryJob","group2").build();
			trigger = TriggerBuilder.newTrigger()
				    .withIdentity("ScanBlockMemoryTrigger", "group2")
				    .withSchedule(CronScheduleBuilder.cronSchedule(scanTime))
				    .withPriority(9).forJob(job)
				    .build();
			sched.scheduleJob(job,trigger);
			sched.start();
			/*Thread.sleep(30L * 1000L);
			sched.shutdown(true);*/
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
