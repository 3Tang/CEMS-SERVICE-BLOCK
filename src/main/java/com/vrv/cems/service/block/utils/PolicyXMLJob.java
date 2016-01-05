package com.vrv.cems.service.block.utils;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobExecutionContext;



import com.sys.common.util.security.CRCUtils;
import com.vrv.cems.service.base.SystemConstants;
import com.vrv.cems.service.base.interfaces.IOnlineService;
import com.vrv.cems.service.block.bean.DBServParamBean;
import com.vrv.cems.service.block.bean.ServiceParamBean;



/** 
 *   <B>说       明</B>:PolicyXMLJob
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年5月12日上午9:08:50 
 */
public class PolicyXMLJob implements Job{
	 private static Log logger = LogFactory.getLog(PolicyXMLJob.class);
	 private ServiceParamBean newServBean;
	@Override
	public void execute(JobExecutionContext paramJobExecutionContext) {
		// TODO 自动生成的方法存根
		//读取数据库,取出数据
		String xmlPath= SystemConstants.PATH_POLICY_XML;
		@SuppressWarnings("unused")
		DBServParamBean servParam=DBUtil.queryByServiceCode(IOnlineService.SERVICE_CODE);
		
		String policyXmlStr = null;
		try {
			//服务本地策略文件
			policyXmlStr = PolicyConfigService.getInstance().policyXML2String(xmlPath);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//db里的crcCode
		String crcCheck=CRCUtils.getCRC32StringValue(policyXmlStr);
		//数据库中字符串
		String xmlString=servParam.getContent();
		if(StringUtils.isBlank(xmlString)){
			throw new RuntimeException("查询数据库拉取策略定时器失败！");
		}
		if(StringUtils.isBlank(policyXmlStr)){
			throw new RuntimeException("本地策略文件为空！");
		}
		
		
		//通过crc验证
		try {
		/*if(!crcCheck.equals(CRCUtils.getCRC32StringValue(servParam.getContent())))
		{*/
			
			//对文件进行解析,用一个统一的工具类
			//把String转为配置对象
			
			
				//依次取修改各种配置
				newServBean = PolicyConfigService.getInstance().modifyLocalPolicyParam(xmlString, policyXmlStr);
				//回写
				PolicyConfigService.getInstance().localServicePolicyFileWrite(newServBean);
		
		//}
		
		}//保存配置对象到本地文件
		catch (Exception e) {
			// TODO 自动生成的 catch 块
			
			logger.info("策略配置拉取定时器 失败!"+e.getMessage());
		}
		
		logger.info("策略配置拉取定时任务执行结束...");
	}

	@Test
	public void executeTest() {
		// TODO 自动生成的方法存根
		//读取数据库,取出数据
		String xmlPath= SystemConstants.PATH_POLICY_XML;
		@SuppressWarnings("unused")
		DBServParamBean servParam=DBUtil.queryByServiceCode(IOnlineService.SERVICE_CODE);
		
		String policyXmlStr = null;
		try {
			policyXmlStr = PolicyConfigService.getInstance().policyXML2String(xmlPath);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//db里的crcCode
		String crcCheck=CRCUtils.getCRC32StringValue(policyXmlStr);
		if(StringUtils.isNotBlank(servParam.getContent())){
		//通过crc验证
		try {
		if(!crcCheck.equals(CRCUtils.getCRC32StringValue(servParam.getContent())))
		{
			
			//对文件进行解析,用一个统一的工具类
			//把String转为配置对象
			String xmlString=servParam.getContent();
			
			//依次取修改各种配置
			newServBean = PolicyConfigService.getInstance().modifyLocalPolicyParam(xmlString, xmlPath);
				//回写
				PolicyConfigService.getInstance().localServicePolicyFileWrite(newServBean);
		
		}
		
		}//保存配置对象到本地文件
		catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			logger.info("策略配置拉取定时器 失败!");
		}
		}else{
		
			logger.info("数据库中没有数据!");
	
		}
	}
	

}
