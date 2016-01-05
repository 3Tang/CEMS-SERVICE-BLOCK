package com.vrv.cems.service.block.security.impl;

import java.io.PrintStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * <B>说 明</B>:base64工具
 * 
 * @author 作 者 名：tom<br/>
 *         E-mail ：zhuxudong@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2014年9月10日 上午11:20:39
 */
public class Base64Util {
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return new BASE64Encoder().encode(s.getBytes());
	}

	public static String getBASE64(byte[] s) {
		if (s == null)
			return null;
		return new BASE64Encoder().encode(s);
	}

	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
		}
		return null;
	}

}
