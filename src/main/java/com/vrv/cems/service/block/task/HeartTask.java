package com.vrv.cems.service.block.task; 

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import com.sys.common.util.IPAddressUtils;
import com.vrv.cems.service.block.utils.SystemConstants;
import com.vrv.cems.service.register.impl.RegisterServiceImpl;
import com.vrv.im.client.ConfigServerUtil;
import com.vrv.im.client.pool.ClientHandler;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年10月13日 下午3:32:28 
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class HeartTask implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try{
			RegisterServiceImpl.registerService();
//			byte b = (Byte)ConfigServerUtil.getConfigServiceClient().invokeHandler("serviceHeart", new Object[]{"1","00010400","1.0"});
//			if(b == 3){
//				ServiceConfigBean configBean = new ServiceConfigBean();
//				configBean.setServiceID("00010400");
//				configBean.setName("CEMS-SERVICE-BLOCK");
//				configBean.setLocation(System.getProperty("user.dir"));
//				configBean.setVersion("1.0");
//				//SSID 唯一标识
//				configBean.setSSID("1");
//				configBean.setIp(IPAddressUtils.getLocalStringIPAddress());
//				configBean.setPort(SystemConstants.PORT);
//				configBean.setOrgID("1");
//				configBean.setServerID(IPAddressUtils.ip2UUID(IPAddressUtils.getLocalStringIPAddress()));
//				ClientHandler ch = ConfigServerUtil.getConfigServiceClient();
//				byte a = (Byte)ch.invokeHandler("registerService", new Object[]{configBean});
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
 