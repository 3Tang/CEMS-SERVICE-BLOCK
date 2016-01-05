package com.vrv.cems.service.block.business;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.vrv.cems.service.block.SystemCommonReturn;
import com.vrv.cems.service.block.bean.DeviceBean;
import com.vrv.cems.service.block.bean.InputBean;
import com.vrv.cems.service.block.utils.EhCacheUtil;
import com.vrv.cems.service.block.utils.JsonUtils;
import com.vrv.cems.service.block.utils.SystemConstants;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class OnlineProcess extends BusinessProcess {
	private static final Logger log = Logger.getLogger(OnlineProcess.class);
	@Override
	public ByteBuffer process(final String maxCode, final String minCode, String checkCode,
			boolean isZip, String data) {
		String s = data;
		log.info("在线服务推来的" + s);
		final InputBean inputBean = JsonUtils.jsonToBean(s, InputBean.class, DeviceBean.class);
		DeviceStateChangeProcess.changeProcess(inputBean.getJdata());
		//EhCacheUtil.closeSingletonManager();
		/*CommomThreadPool.joinThreadPool(new Runnable() {
			public void run() {
				//生成阻断任务	
				SendUDPBlock.send(maxCode,minCode);
			}
		});*/
		return SystemCommonReturn.commonReturn(maxCode, minCode, SystemConstants.REQUEST_SUCCESS, "成功", new ArrayList());
	}

}
