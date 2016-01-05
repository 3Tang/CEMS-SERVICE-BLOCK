package com.vrv.cems.service.block.security;

/**
 * <B>说 明</B>:
 * 
 * @author 作 者 名：tom<br/>
 *         E-mail ：zhuxudong@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2014年9月10日 上午11:20:39
 */
import com.vrv.cems.service.block.security.impl.DesSecurityService;

public class SecurityServiceFactory {
	public static ISymmetricSecurityService getSecurityService(String type) {
		ISymmetricSecurityService service = null;
		if ("DES".equals(type))
			service = new DesSecurityService();
		else
			"MD5".equals(type);
		return service;
	}
}
