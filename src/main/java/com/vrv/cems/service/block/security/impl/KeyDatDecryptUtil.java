package com.vrv.cems.service.block.security.impl; 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.sys.common.util.CommentedProperties;
import com.sys.common.util.security.AESUtils;
import com.vrv.cems.service.base.SystemConstants;

/** 
 *   <B>说       明</B>: key.dat文件解密测试类
 *
 * @author  作  者  名：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月8日 下午3:54:07 
 */
public class KeyDatDecryptUtil {
	
	public static String PublicKey="";
	public static String PrivateKey="";
	/*static{
		try {
			CommentedProperties props = new CommentedProperties();
			String keyDatPath = props.getPropertyFromCemsDat("path.key.dat");
			InputStream inputStream = new FileInputStream(keyDatPath);
			byte [] data = dataStream2Byte(inputStream);
			
			//解密后的字符串
			byte [] decryptData = AESUtils.decrypt(data, SystemConstants.KEYDAT.getBytes());
			
			//把byte数组转成inputStream
			ByteArrayInputStream is = new ByteArrayInputStream(decryptData);  
			CommentedProperties prop = new CommentedProperties();
			prop.load(is);
			PublicKey=prop.getProperty("publicKey");
			PrivateKey=prop.getProperty("privateKey");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
	
	
	/**
	 * 输入流转成字节数组
	 */
	public static byte[] dataStream2Byte(InputStream inputStream){
		
		byte[] returnByte = null;
		
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		int length = -1;
		try {
			while((length = inputStream.read(buffer, 0, 1024)) != -1){
				byteArray.write(buffer, 0, length);
			}
		} catch(OutOfMemoryError e){
			byteArray.reset();
//			log.error("Not enough memory: "+e);
			return null;
		}catch (IOException e) {
			byteArray.reset();
			return null;
		}finally{
			try{
				byteArray.close();
			}catch(IOException e){
//				log.error("byteArry close error: "+e);
			}
		}
		if(byteArray.size() != 0){
			returnByte = byteArray.toByteArray();
		}
		return returnByte;
	}
}
 