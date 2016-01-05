package com.vrv.cems.service.block.utils; 

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.log4j.Logger;

import com.sys.common.util.UUIDUtils;
import com.sys.common.util.compress.CompressFormat;
import com.sys.common.util.compress.Compressor;
import com.sys.common.util.security.CRCUtils;
import com.vrv.cems.service.base.bean.cache.DeviceKeyCache;
import com.vrv.cems.service.base.util.BaseEncryptDecryptUtils;
import com.vrv.cems.service.base.util.ByteBufferUtils;
import com.vrv.cems.service.block.ServiceMain;
import com.vrv.cems.service.block.bean.ServiceParamBean;
import com.vrv.cems.service.block.bean.ServiceParamBean.ParamBean;
import com.vrv.cems.service.block.business.ScanProcess;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年10月30日 上午10:54:16 
 */
public class CommonUtil {
	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	public final static int MAX_BUF = 1024 * 8;

	static String key = UUIDUtils.get32UUID();
	static int flag = BaseEncryptDecryptUtils.rondom();
	
	public static String byteBufferToString(ByteBuffer buffer){
		CharBuffer decode = null;
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder charsetDecoder = charset.newDecoder();
		try {
			decode = charsetDecoder.decode(buffer.asReadOnlyBuffer());
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		return decode.toString();
	}
	
	
	
	/**
	 * ByteBuffer算crc
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String byteBufferCrc(ByteBuffer data) throws IOException {
		return byteCrc(data.array());
	}
	
	/**
	 * byteCrc 算byte的crc
	 * 
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String byteCrc(byte[] b) throws IOException {
		InputStream is = new ByteArrayInputStream(b);
		byte by[] = new byte[MAX_BUF];
		CRC32 c = new CRC32();
		int len = 0;
		while ((len = is.read(by, 0, MAX_BUF)) != -1)
			c.update(by, 0, len);
		long result = c.getValue();
		is.close();
		return Long.toHexString(result);
	}

	public static String tsParams2String(String checkCode, boolean isZip,
			ByteBuffer data, boolean isEncrypt, String key, int flag) {
		// 先进行crc校验 checkCode data
				//解压缩 isZip
				//解密 isEncrypt key flag
	
		 String	dataStr = byteBufferToString(data);
		logger.info("开始校验输入信息！");
		logger.info("接口传入的crc为:"+checkCode);
		logger.info("服务端生成的crc为:"+CRCUtils.getCRC32StringValue(dataStr));
		try {//(checkCode)
			 if (!CRCUtils.getCRC32StringValue(dataStr).equalsIgnoreCase(checkCode)) {
				 logger.error("CRC校验失败！");
			 return null;
			 }
			 logger.info("CRC校验成功！");
			 
			 if (isZip) 
			 {
				 logger.info("数据解压！");
				 data = Compressor.getInstance(CompressFormat.ZIP).decompress(data);	 	
			 } 
			 else
			 {
				 logger.info("数据无需解压!");
			 }
			  if(isEncrypt) 
			   {
				 logger.info("开始服务之前DES解密！");
				 logger.info("在线服务获取的key为"+key);
				 logger.info("在线服务获取的flag为"+flag);
				 //msgArray = ProcessUtil.decryptMsg(SystemConfig.SERVER_COMMUNICATE_ARITHMETIC_TYPE,key, upZipData);
				 data = BaseEncryptDecryptUtils.decrypt(key, flag, data);
				 
				 }
			  
			logger.info("输入的data为：" + dataStr);
			return dataStr;			
			 //return false;
		} catch (Exception e) {
			logger.error("校验输入信息出错！" + e.getMessage());
			return null;
		}
		
	}

	public static String tcParams2String(String checkCode, boolean isZip,
			ByteBuffer data, String sessionId, int msgCode) {
		String  dataStr = byteBufferToString(data);
		logger.info("开始校验输入信息！"+dataStr);
		logger.info("接口传入的crc为:"+checkCode);
		logger.info("服务端生成的crc为:"+CRCUtils.getCRC32StringValue(dataStr));
		try {
			 if (!CRCUtils.getCRC32StringValue(dataStr).equalsIgnoreCase(checkCode)) {
				 logger.error("CRC校验失败！");
			 return null;
			 }
			 logger.info("CRC校验成功！");
			 
			 if (isZip) 
			 {
				 logger.info("数据解压！");
				 data = Compressor.getInstance(CompressFormat.ZIP).decompress(data);	 	
			 }
			 else
			 {
				 logger.info("无需解压!");
			 }
			/*DeviceKeyCache deviceKeyCache= RedisUtil.getPswBySession(sessionId);
			byte[] msgArray=ProcessUtil.decryClientMsg(deviceKeyCache.getKeyType(), data.array(),deviceKeyCache.getPassword(), deviceKeyCache.getOffsetVector().getBytes());
			data= data.wrap(msgArray);
			*/
			
	}catch (Exception e) {
		logger.error("校验输入信息出错！" + e.getMessage());
		return null;
	}
		return dataStr;
	}
	
	/** 
	* @Title: getPolicyPrarams 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return  参数说明 
	* @return List<ParamBean>    返回类型 
	* @throws 
	*/
	public static List<ParamBean> getPolicyPrarams() {
		if (ServiceParamBean.StaticParams == null) {
			ServiceParamBean.StaticParams = ServiceMain.serviceParamBean
					.getParams();
		}
		List<ParamBean> params = ServiceParamBean.StaticParams;
		return params;
	}

}
 