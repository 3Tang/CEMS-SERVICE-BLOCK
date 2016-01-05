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
public abstract interface ISymmetricSecurityService {
	public static final String SECURITY_DES = "DES";
	public static final String SECURITY_MD5 = "MD5";

	public abstract byte[] encrypt(byte[] data, byte[] key) throws Exception;

	public abstract byte[] decrypt(byte[] data, byte[] key) throws Exception;
}
