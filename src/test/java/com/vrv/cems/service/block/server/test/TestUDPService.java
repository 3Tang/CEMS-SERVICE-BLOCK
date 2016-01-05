package com.vrv.cems.service.block.server.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.vrv.cems.service.base.interfaces.IBlockService;
import com.vrv.cems.service.block.bean.UDPHead;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class TestUDPService {
	//@SuppressWarnings("static-access")
	//@Test
	/*public void clientUDPTest() throws Exception{
		
		while( true ){
			try {
				sendUDP();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					Thread.currentThread().sleep( 1500*60 );
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public void sendUDP(){
		try {
			Random r = new Random(  ); 
			JSONObject jdataJson = new JSONObject();
			jdataJson.accumulate("taskId", UUID.randomUUID().toString() )
					 .accumulate("blockIp","192.168.32."+(r.nextInt(214)+1)+":1C-6F-65-ED-A"+(r.nextInt(9)+1)+"-0A;192.168.0."+(r.nextInt(252)+1)+":1C-6F-"+(r.nextInt(9)+1)+"5-ED-A3-07;")
					 .accumulate("blockState", ""+r.nextInt( 2))
					 .accumulate("blockRuleType", ""+(r.nextInt(2)+1))
					 .accumulate("blockIntervalTime", ""+(r.nextInt(15)+1)+"")
					 .accumulate("blockNumber",  ""+(r.nextInt(10)+1)+"");
			JSONArray jdataJsonArray = new JSONArray();
			jdataJsonArray.add( jdataJson );
			JSONObject jsonObject = new JSONObject();
			jsonObject
				.element("maxCode", IBlockService.SERVICE_CODE )
				.element("minCode", "4")
				.element("jdata",  jdataJsonArray );
			System.out.println( jsonObject.toString() );
			String data = jsonObject.toString();
			
			
			UDPHead head = new UDPHead();
			head.setDwVersion(1);
			head.setDwIndex(1);
			head.setDwMaxCode(00010400);
			head.setDwMinCode(4); 
			head.setDwCount(1);
			head.setDwMsgCode( 1 );
			head.setSzMark("test");   
			
			byte[] blockData =  data.getBytes("UTF-8");
			head.setDwSize( blockData.length); 
			ByteBuffer message = ByteBuffer.allocate(UDPHead.HEAD_SIZE + blockData.length);
			message.put(head.getCBuffer());
			message.put( blockData ); 
			//发送
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("192.168.88.135");
			DatagramPacket sendPacket = new DatagramPacket( message.array() , message.capacity() , IPAddress, 22116);
			clientSocket.send(sendPacket);
			
			System.out.println( new String( message.array() ,"UTF-8") );
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */
}
