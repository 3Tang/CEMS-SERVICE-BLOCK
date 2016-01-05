package com.vrv.cems.service.block.business;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sys.common.util.CommentedProperties;
import com.vrv.cems.service.block.SystemCommonReturn;
import com.vrv.cems.service.block.bean.InputBean;
import com.vrv.cems.service.block.bean.WebNoticeBean;
import com.vrv.cems.service.block.utils.JsonUtils;
import com.vrv.cems.service.block.utils.ReadConfigFileUtil;
import com.vrv.cems.service.block.utils.SystemConstants;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class WebNoticeProcess extends BusinessProcess {
	private static Logger log=Logger.getLogger(WebNoticeProcess.class);
	@Override
	public ByteBuffer process(String maxCode, String minCode, String checkCode,
			boolean isZip, String data) {
		String s = data;
		log.info("WEB平台推来的数据："+s);
		InputBean inputBean = JsonUtils.jsonToBean(s, InputBean.class, WebNoticeBean.class);
		List<WebNoticeBean> InputBeans = inputBean.getJdata();
		if(!InputBeans.isEmpty()){
			WebNoticeBean webNoticeBean  = InputBeans.get(0);
			ReadConfigFileUtil.setValue("isBlock", webNoticeBean.getIsBlock());
			ReadConfigFileUtil.setValue("blockRuleType", webNoticeBean.getBlockRuleType());
			ReadConfigFileUtil.setValue("blockIntervalTime",webNoticeBean.getBlockIntervalTime());
			ReadConfigFileUtil.setValue("blockNumber",webNoticeBean.getBlockNumber());
			ReadConfigFileUtil.setValue("timeoutUpd", webNoticeBean.getTimeoutUpd());
			ReadConfigFileUtil.save();
		}
		return SystemCommonReturn.commonReturn(maxCode, minCode, SystemConstants.REQUEST_SUCCESS, "成功", new ArrayList());
	}

}
