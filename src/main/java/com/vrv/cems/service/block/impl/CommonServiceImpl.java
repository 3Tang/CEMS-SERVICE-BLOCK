package com.vrv.cems.service.block.impl;
import java.nio.ByteBuffer;

import org.apache.thrift.TException; 
import com.vrv.cems.service.base.SystemConstants;
import com.vrv.cems.service.base.bean.BaseEnDecryptResultBean;
import com.vrv.cems.service.base.interfaces.CommonService; 
import com.vrv.cems.service.base.util.BaseEncryptDecryptUtils;
import com.vrv.cems.service.base.util.ByteBufferUtils;
import com.vrv.cems.service.block.business.BusinessProcess;
import com.vrv.cems.service.block.factory.BusinessProcessFactory;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class CommonServiceImpl implements CommonService.Iface{
	@Override
	public ByteBuffer getDataTS(String maxCode, String minCode,
			String checkCode, boolean isZip, ByteBuffer data, boolean isEncrypt, String key, int flag) throws TException {
		BaseEnDecryptResultBean baseBean = BaseEncryptDecryptUtils.decrypt(isEncrypt, isZip, checkCode, data, key, flag);
		if(baseBean.getResult().equals(SystemConstants.SUCCESS)){
			data = baseBean.getData();
		}
		BusinessProcess businessProcess = BusinessProcessFactory.create(minCode);
		return businessProcess.process(maxCode, minCode, checkCode, isZip, ByteBufferUtils.byteBuffer2String( data ) );
		
	}

	@Override
	public ByteBuffer getDataTC(String maxCode, String minCode,
			String checkCode, boolean isZip, ByteBuffer data, String sessionId,
			int msgCode) throws TException {
		return null;
	}	
}
