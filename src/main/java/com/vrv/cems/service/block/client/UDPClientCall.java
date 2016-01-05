package com.vrv.cems.service.block.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.List;

import org.apache.log4j.Logger;

import com.vrv.cems.service.block.ServiceMain;
import com.vrv.cems.service.block.bean.ServiceParamBean;
import com.vrv.cems.service.block.bean.UDPHead;
import com.vrv.cems.service.block.bean.UDPResponse;
import com.vrv.cems.service.block.bean.ServiceParamBean.ParamBean;
import com.vrv.cems.service.block.utils.SystemConstants;

/** 
 *   <B>说       明</B>:调用终端所提供的接口
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class UDPClientCall {
	private static Logger log=Logger.getLogger(UDPClientCall.class);
	static UDPResponse response = null;
	public static byte[] sendUDPData(byte[] sendData,String ip,int port) throws Exception{
		String timeoutUdp="30";
	/*	if(ServiceParamBean.StaticParams!=null){
			ServiceParamBean.StaticParams=ServiceMain.serviceParamBean.getParams();
		}
		List<ParamBean> params=ServiceParamBean.StaticParams;
		for(ParamBean param:params)
		{
			if("timeoutUdp".equalsIgnoreCase(param.getKey()))
			{
				timeoutUdp=param.getValue();
			}
		}*/
		
		DatagramSocket clientSocket = new DatagramSocket();
	    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ip), port);
	    try{
	    	log.info("阻断发送给客户端的UDP数据为:"+new String(sendData));
	    	clientSocket.send(sendPacket);
	    }catch(IOException e){
	    	log.error("UDPClientCall->clientSocket.send(sendPacket) 数据包发送异常！",e);
	    }
	    response=new UDPResponse();
	    int repeatCount = 3;
	    byte[] rsData = null;
	    while(repeatCount>0){
	    	try {
	    	  byte[] receiveData = new byte[8192];
	  	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length,InetAddress.getByName(ip), port);
	  	      //设置超时时间
	  	      clientSocket.setSoTimeout(Integer.valueOf(timeoutUdp) * 100);
	          clientSocket.receive(receivePacket);
	          rsData = new byte[receivePacket.getLength()];
	          System.arraycopy(receivePacket.getData(), 0, rsData,0, receivePacket.getLength());
	          log.info("阻断服务接受客户端返回数据为"+receivePacket.getAddress()+" at port:"
	                +receivePacket.getPort()+ " says:\n"+new String(receiveData));
	          break;
	    	}catch(SocketTimeoutException e){
	    		log.error("SocketTimeoutException",e);
	    		repeatCount--;
	    	}catch (Exception e) {
				e.printStackTrace();
				break;
			}
	    }
	  
	    clientSocket.close();
        return rsData;
	}
	
}
