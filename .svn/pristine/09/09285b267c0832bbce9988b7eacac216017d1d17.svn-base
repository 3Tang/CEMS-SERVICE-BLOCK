package com.vrv.cems.service.block.security.impl;


import java.security.SecureRandom;



import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.vrv.cems.service.block.security.ISymmetricSecurityService;
/**
 * <B>说 明</B>:DES安全加密service
 * 
 * @author 作 者 名：tom<br/>
 *         E-mail ：zhuxudong@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2014年9月10日 上午11:20:39
 */
public class DesSecurityService
implements ISymmetricSecurityService
{
	private static final String DES = "DES/ECB/PKCS5Padding";

	public byte[] decrypt(byte[] data, byte[] key)
	throws Exception
	{
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(2, securekey, sr);
		return cipher.doFinal(data);
		}

	public byte[] encrypt(byte[] data, byte[] key)
	throws Exception
	{
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(1, securekey, sr);
		return cipher.doFinal(data);
		}

	public static void main(String[] args)
	{
		}
}
