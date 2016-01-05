/**
 * <b>说	明:</b>
 * @author 作      者   :alex-tang <br>
 * 		   Email:alex-tang@vrvmail.com.cn
 *
 * @version 版   本 :V1.0<br>
 *			时   间 :2015年6月8日下午2:21:14
 */

package com.vrv.cems.service.localtest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.sys.common.util.UUIDUtils;
import com.sys.common.util.security.Base64Utils;
import com.sys.common.util.security.HexUtils;
import com.sys.common.util.security.MD5Utils;
import com.vrv.cems.service.base.bean.cache.DeviceOnlineCache;


/** 
 *   <B>说       明</B>:TestLocalUDPService
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年6月8日下午2:21:14 
 */
public class TestLocalUDPService {

	
	
	private final static int DEFAULT_PORT=18080;
	private final static int MAX_PACKET_SIZE=65507;

	public static void main(String[] args) {

	int port=  DEFAULT_PORT;
	byte[] buffer=new byte[MAX_PACKET_SIZE];
	try{
	    DatagramSocket server=new DatagramSocket(port);
	           DatagramPacket packet=new DatagramPacket(buffer,buffer.length);
	           /*double d1=Long.parseLong();
	           double d2=Long.parseLong(MD5Utils.getMD5("中关村"));
	           double d3=Long.parseLong(MD5Utils.getMD5("星巴克"));
	           */
	           
	        /*  Long long1= Long.parseLong(HexUtils.toHexString(MD5Utils.getMD5("北京").substring(0, 7).getBytes()));
	          Long long2= Long.parseLong(HexUtils.toHexString(MD5Utils.getMD5("中关村").substring(0, 7).getBytes()));
	          Long long3= Long.parseLong(HexUtils.toHexString(MD5Utils.getMD5("星巴克").substring(0, 7).getBytes()));
	           //.getBASE64(UUIDUtils.get32UUID().substring(0, 11));
	          System.out.println(long1+long2+long3);
	          System.out.println(long1+long2+long3);
	          System.out.println(long1+long2+long3);*/
	         

	while(true)
	           {
	            server.receive(packet);
	            String s=new String(packet.getData(),packet.getOffset(),packet.getLength(),"UTF-8");
	            
	            System.out.println(packet.getAddress()+" at port:"
	                +packet.getPort()+ " says:\n"+s);
	      
	//设置以后需要接受的长度
	            packet.setLength(buffer.length);
	            String echoString="{'maxCode':'00010400','minCode':'1','result':'0','desc':'阻断成功','jdata':[]}";
	            byte[] echoByte=echoString.getBytes();
	            DatagramPacket echo = new DatagramPacket(echoByte, echoByte.length, packet.getAddress(), packet.getPort());
	       
                //将数据包发送给客户端
	              server.send(echo);
	           }

	   }catch(SocketException e)
	   {

	   }catch(IOException e)
	   {

	   }
	 }
	
}
