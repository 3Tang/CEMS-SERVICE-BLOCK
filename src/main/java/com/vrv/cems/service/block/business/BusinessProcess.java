package com.vrv.cems.service.block.business;

import java.nio.ByteBuffer;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public abstract class BusinessProcess {
	public abstract ByteBuffer process(String maxCode, String minCode,
			String checkCode, boolean isZip, String data);
	
	
}
