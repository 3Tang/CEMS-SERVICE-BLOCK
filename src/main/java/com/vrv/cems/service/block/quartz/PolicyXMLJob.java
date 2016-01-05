package com.vrv.cems.service.block.quartz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;
import org.dom4j.DocumentException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.sys.common.util.IPAddressUtils;
import com.sys.common.util.security.CRCUtils;
import com.vrv.cems.service.base.bean.BaseEnDecryptResultBean;
import com.vrv.cems.service.base.bean.ResultMsgBean;
import com.vrv.cems.service.base.interfaces.IBlockService;
import com.vrv.cems.service.base.interfaces.ICEMSService;
import com.vrv.cems.service.base.interfaces.IOnlineService;
import com.vrv.cems.service.base.interfaces.IUDPService;
import com.vrv.cems.service.base.interfaces.impl.CEMSServiceImpl;
import com.vrv.cems.service.base.util.BaseEncryptDecryptUtils;
import com.vrv.cems.service.base.util.ByteBufferUtils;
import com.vrv.cems.service.block.bean.DBServParamBean;
import com.vrv.cems.service.block.bean.ServiceParamBean;
import com.vrv.cems.service.block.utils.DBUtil;
import com.vrv.cems.service.block.utils.PolicyConfigService;
import com.vrv.cems.service.block.utils.SystemConstants;
import com.vrv.cems.service.configure.utils.ReadConfigPropertiesUtils;

/**
 * <B>说 明</B>:PolicyXMLJob
 *
 * @author 作 者 名 ：tangtieqiao<br/>
 *         E-mail ：tangtieqiao@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2015年5月12日上午9:08:50
 */
public class PolicyXMLJob implements Job {
	private static Log logger = LogFactory.getLog(PolicyXMLJob.class);
	private ServiceParamBean newServBean;

	@Override
	public void execute(JobExecutionContext paramJobExecutionContext) {
		logger.info("阻断服务 策略拉取定时器 开始执行!");
		String xmlPath = SystemConstants.PATH_POLICY_XML;
		@SuppressWarnings("unused")
		// DBServParamBean
		// servParam=DBUtil.queryByServiceCode(IBlockService.SERVICE_CODE);
		String serviceIp = ReadConfigPropertiesUtils.getValue(
				SystemConstants.PATH_CONFIG_PROPERTIES, "service.ip");
		String crcValue = null;
		try {
			crcValue = CRCUtils.getCRC32StringValue(new FileInputStream(
					new File(SystemConstants.PATH_POLICY_XML)));
		} catch (FileNotFoundException e) {
			logger.error("未能加载policy.xml!", e);
		} catch (IOException e) {
			logger.error("加载policy.xml报错!", e);
		}
		if (StringUtils.isNotBlank(serviceIp)) {
			JSONObject json = new JSONObject();
			json.accumulate("serverId", IPAddressUtils.ip2UUID(serviceIp));
			json.accumulate("serviceCode", IBlockService.SERVICE_CODE);
			json.accumulate("crc", crcValue);
			String dataString = json.toString();
			ICEMSService cemsService = new CEMSServiceImpl();
			BaseEnDecryptResultBean encrypt = BaseEncryptDecryptUtils.encrypt(
					SystemConstants.ISENCRYPT, SystemConstants.ISZIP,
					ByteBufferUtils.string2ByteBuffer(dataString));
			ByteBuffer resultByteBuffer;
			try {
				resultByteBuffer = cemsService.getDataTS(
						ICEMSService.SERVICE_CODE, ICEMSService.POLICY_XML,
						encrypt.getCrc(), SystemConstants.ISZIP,
						encrypt.getData(), SystemConstants.ISENCRYPT,
						encrypt.getKey(), encrypt.getFlag());
				ResultMsgBean decrypt = BaseEncryptDecryptUtils.decrypt(
						SystemConstants.ISENCRYPT, SystemConstants.ISZIP,
						resultByteBuffer, encrypt.getKey(), encrypt.getFlag());
				JSONObject jsonObject = (JSONObject) decrypt.getJdata().get(0);
				Integer isChange = Integer.valueOf(jsonObject.get("isChange")
						.toString());
				if (isChange == 1) {
					String policyXmlContent = jsonObject.getString("policyXml");
					// 依次取修改各种配置
					newServBean = PolicyConfigService.getInstance()
							.modifyLocalPolicyParam(policyXmlContent, xmlPath);
					// 回写
					PolicyConfigService.getInstance()
							.localServicePolicyFileWrite(newServBean);
				} else {
					logger.info("服务策略没有变化,不需要更新");
				}
			} catch (TException e) {
				logger.error("访问CEMS平台报错!", e);
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
		} else {
			logger.info("阻断服务 策略拉取定时器 执行成功!");
		}

	}

	
}
