package com.vrv.cems.service.block.server.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;
import org.junit.Test;

import com.sys.common.util.CommentedProperties;
import com.sys.common.util.UUIDUtils;
import com.sys.common.util.security.CRCUtils;
import com.vrv.cems.service.base.interfaces.IBlockService;
import com.vrv.cems.service.base.interfaces.IOnlineService;
import com.vrv.cems.service.base.util.BaseEncryptDecryptUtils;
import com.vrv.cems.service.block.impl.BlockServiceImpl;
import com.vrv.voa.client.ServiceFactory;


/** 
 *    在线服务策略测试类
 *
 * @author  作  者  名：alex-tang;
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0;
 *          创建时间：2015年4月20日 上午11:37:55 
 */ 
public class UpdateOnliePolicyServiceTest {
	private static Log logger = LogFactory.getLog(UpdateOnliePolicyServiceTest.class);
	 @Test
	public void test() 
	{
		 String path="src/main/resources/policy.xml";
		 File file = new File(path);
		 InputStream in = null;
		 logger.info("asdfasdf");
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		 String xmlStr = null;
		try {
			xmlStr = inputStream2String(in);
			System.out.println("从网页平台传过来的数据为"+xmlStr);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		 //Document document = DocumentHelper.parseText(xmlStr);
		 //String text = document.asXML();  
	        String maxCode=IOnlineService.SERVICE_CODE;
			String minCode=IOnlineService.POLICY_XML;
			
			ByteBuffer inputBuffer=null;
			inputBuffer=ByteBuffer.wrap(xmlStr.getBytes());
     
      String key="";//UUIDUtils.get32UUID();
      int flag=0;//BaseEncryptDecryptUtils.rondom();

      //inParam.setData(BaseEncryptDecryptUtils.encrypt(key, flag, inParam.getData()));
		
			boolean isZip=false;
			boolean isEncrypt=false;
			String checkCode = null;
			int msgCode=0;
			
			//BlockServiceImpl blockService=new BlockServiceImpl();
			IBlockService blockService = ServiceFactory.getService(IBlockService.class, 5000);

			checkCode = CRCUtils.getCRC32StringValue(inputBuffer.array());
			System.out.println("发送前的checkCode为"+checkCode);
			ByteBuffer outParam = null;
			//客户端调用都用tc
			try {
				outParam= blockService.getDataTS(maxCode, minCode, checkCode,
						isZip, inputBuffer,isEncrypt,key,flag);
			
			} catch (TException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			System.out.println("服务接受已加密的数据"+new String(outParam.array()));
			//进行解密
			//outParam=BaseEncryptDecryptUtils.decrypt(key, flag, outParam);
			System.out.println("服务接受已解密的数据"+new String(outParam.array()));
		

  
	}
	 
	 String inputStream2String(InputStream is) throws IOException{
		   BufferedReader in = new BufferedReader(new InputStreamReader(is));
		   StringBuffer buffer = new StringBuffer();
		   String line = "";
		   while ((line = in.readLine()) != null){
		     buffer.append(line);
		   }
		   return buffer.toString();
		}
}
