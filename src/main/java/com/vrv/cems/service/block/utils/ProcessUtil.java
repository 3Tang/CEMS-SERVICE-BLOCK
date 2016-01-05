package com.vrv.cems.service.block.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.common.util.security.AESUtils;
import com.sys.common.util.security.DESUtils;
import com.sys.common.util.security.rc.RC4Utils;
import com.sys.common.util.security.sm.SM4Utils;
import com.vrv.cems.service.block.security.IDissymmetricSecurityService;
import com.vrv.cems.service.block.security.ISymmetricSecurityService;
import com.vrv.cems.service.block.security.SecurityServiceFactory;
import com.vrv.cems.service.block.security.impl.RsaSecurityService;

/**
 * <B>说 明</B>:
 * 
 * @author 作 者 名：tom<br/>
 *         E-mail ：zhuxudong@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2014年9月10日 上午11:20:39
 */
public class ProcessUtil {
	private static Log logger = LogFactory.getLog(ProcessUtil.class);
	public static byte[] decryptMsg(String arithmeticType, byte[] key, byte[] data) throws Exception {
		byte[] newMsg = (byte[]) null;
		if ((data != null) && (data.length > 0)) {
			ISymmetricSecurityService iSecurityService = SecurityServiceFactory.getSecurityService(arithmeticType);
			newMsg = iSecurityService.decrypt(data, key);
		}
		return newMsg;
	}

	public static byte[] encryptMsg(String arithmeticType, byte[] key, byte[] data) throws Exception{
		logger.info("对称加密");
		ISymmetricSecurityService iSecurityService = SecurityServiceFactory .getSecurityService(arithmeticType);
		return iSecurityService.encrypt(data, key);
	}

	 public static byte[] encryptNotSymmetryMsg(byte[] msg, byte[] key, String type) throws Exception {
		 IDissymmetricSecurityService securityService = new RsaSecurityService();
		 byte[] newMsg = (byte[]) null;
		 if ("pri".equals(type)){
			 logger.info("非对称私钥加密");
			 newMsg = securityService.encryptByPrivateKey(msg, key);
		 }
		 else if ("pub".equals(type)) {
			 logger.info("非对称公钥加密");
			 newMsg = securityService.encryptByPublicKey(msg, key);
			 }
		 return newMsg;
	}
	 
	 public static byte[] decryptNotSymmetryMsg(byte[] msg, byte[] key, String type) throws Exception {
		 IDissymmetricSecurityService securityService = new RsaSecurityService();
		 byte[] newMsg = (byte[]) null;
		 if ("pri".equals(type)){
			 logger.info("非对称私钥解密");
			 newMsg = securityService.decryptByPrivateKey(msg, key);
		 } else if ("pub".equals(type)) {
			 logger.info("非对称公钥解密");
			 newMsg = securityService.decryptByPublicKey(msg, key);
		 }
		 return newMsg;
	 }
	 
	 public static byte[] decryClientMsg(String type,byte[] msg,String password,byte[] ivParameterSpec)
	 {
		 
		 if("DES".equalsIgnoreCase(type))
		 {
		 	//解密为byte的 不用base64解开
		 	  
			 msg= DESUtils.decrypt(msg, password.getBytes(), "DES/CFB8/NoPadding", ivParameterSpec);
		 }
		 if("AES".equalsIgnoreCase(type))
		 {
			 msg=AESUtils.decrypt(msg, password.getBytes(), "AES/CFB128/NoPadding", ivParameterSpec);
		 }
		 if("RC4".equalsIgnoreCase(type))
		 {
			 msg=RC4Utils.decrypt(msg, password.getBytes());
		 }
		 if("SM4".equalsIgnoreCase(type))
		 {
			 msg= SM4Utils.decrypt(msg, password.getBytes());
		 }	
		 
		 return msg;
	 }
	 
	 
}
