package com.vrv.cems.service.block.server.test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.junit.Test;

import com.vrv.cems.service.base.bean.cache.DeviceOnlineCache;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.block.bean.BlockBean;
import com.vrv.cems.service.block.business.SendUDPBlockTask;
import com.vrv.cems.service.block.client.UDPClientCall;
import com.vrv.voa.client.ServiceFactory;

public class TestSendUDP {

	/*private final static int DEFAULT_PORT=18080;
	private final static int MAX_PACKET_SIZE=65507;

	@Test
	public  void testSendUDP() {
		
		BlockTaskBean taskBean = new BlockTaskBean();
		DeviceBean target=new DeviceBean();
		target.setIp("192.168.131.81");
		target.setMac("abcd0e123231");
		taskBean.setBlockTargetDevice(target);
		 List<DeviceBean> deviceBeanList=new ArrayList<DeviceBean>();
		taskBean.setBlockSrcDevices(deviceBeanList);
		//TaskQueue.addTask(taskBean);
		
		//BlockTaskBean blockTaskBean = TaskQueue.poll();
		//DeviceBean targetBlockDevice = blockTaskBean.getBlockTargetDevice();
		//final List<DeviceBean> deviceBeans = blockTaskBean.getBlockSrcDevices();
		target.setBeginDate(new Date());
		BlockBean blockBean = new BlockBean();
		blockBean.setBlockIp(target.getIp()+":"+target.getMac()+";");
		blockBean.setBlockState(target.getBlockState());

	
		//设置  data 参数
		InputBean inputBean = new InputBean();
		inputBean.setMaxCode(IBlockService.SERVICE_CODE);
		inputBean.setMinCode(SystemConstants.CLIENT_UDP_MINCODE);
		List<BlockBean> blockBeans = new ArrayList<BlockBean>();
		blockBeans.add(blockBean); 
		inputBean.setJdata(blockBeans);
		byte[] blockData = null;
		try {
			blockData = JsonUtils.getJsonStr(inputBean).getBytes("UTF-8");
			System.out.println("包体的内容为");
		} catch (UnsupportedEncodingException e1) {
			
			throw new RuntimeException( e1 ) ;
		}
		
	
		UDPHead head = new UDPHead();
		head.setDwFlag("pde_");
		head.setDwVersion(0x01010101);
		head.setDwSize( blockData.length);
		String crc=CRCUtils.getCRC32StringValue(blockData);
		head.setDwCrc(crc);
		String session=UUIDUtils.get32UUID();
		head.setSzSessionId(session);
		head.setDwMsgCode( 1 );
		head.setDwMaxCode("0x"+IBlockService.SERVICE_CODE);
		head.setDwMinCode(2100);
		head.setwHeadSize((short)UDPHead.HEAD_SIZE);
		head.setwType((short)1);		 
		head.setwCount((short)1);
		head.setwIndex((short)1);
	
		 
		final ByteBuffer message = ByteBuffer.allocate(UDPHead.HEAD_SIZE + blockData.length);
		message.put(head.getCBuffer());
		message.put( blockData ); 
		
		System.out.println("包头+包体的内容为"+new String(message.array()));
		
		
		String ip="192.168.0.133";//"192.168.131.81";
		int udp=8080;
		
		byte[] udpRs = null;
		try {
			udpRs = UDPClientCall.sendUDPData(message.array() , ip,udp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutBean rsBean = JsonUtils.jsonToBean(new String(udpRs), OutBean.class, null);
	}
*/
	
	@Test
	public void sendUDPTest()
	{
		List<BlockBean> blockBeans=new ArrayList<BlockBean>();
		String sendIp="192.168.88.94";
		//String sendUDP="22116";
		
		String ip="192.168.1.59";
		int sendUDPport=22116;
		String blockIp="192.168.1.36:00-0c-29-40-97-f9;192.168.1.35:3c-46-d8-3b-41-f9";
		String blockState="1";
		String blockRuleType="1";
		String blockTime="5";
		BlockBean block=new BlockBean(ip,blockIp,blockState,blockRuleType,blockTime);
		
		blockBeans.add(block);
		
		/*ICacheService cacheClient =ServiceFactory.getService(ICacheService.class);
		DeviceOnlineCache devOnline=new DeviceOnlineCache();
		try {
			devOnline=cacheClient.queryDeviceOnlineByIp(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEONLINEBYIP, sendIp);
		} catch (TException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}*/
		//sendUDPport=Integer.parseInt(devOnline.getUdpPort());
		ByteBuffer message=SendUDPBlockTask.getUDPMsg("2100", blockBeans);
		byte[] udpRs=null;
		try {
			
				System.out.println("测试端口为:"+sendUDPport);
		 udpRs = UDPClientCall.sendUDPData(
					message.array(),sendIp,
					sendUDPport);
		 if(udpRs!=null){
				System.out.println("客户端返回的信息为:"+new String(udpRs)+"端口为i:"+sendUDPport);
			 }
			
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		
	}
}
