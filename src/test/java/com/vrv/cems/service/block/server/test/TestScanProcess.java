package com.vrv.cems.service.block.server.test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;
import org.junit.Test;

import com.sys.common.util.security.CRCUtils;
import com.vrv.cems.service.base.interfaces.IBlockService;
import com.vrv.cems.service.block.ServiceMain;
import com.vrv.cems.service.block.impl.BlockServiceImpl;
import com.vrv.voa.client.ServiceFactory;

public class TestScanProcess {
	 private static Log logger = LogFactory.getLog(TestScanProcess.class);
	
	@Test
	public void test() throws UnsupportedEncodingException
	{
		String scanStr1="[{'areaId':'','devOnlyId':'','ip':'192.168.32.100','mac':'00-01-02-03-04-05','hostName':'majiandong-pc','groupName':'WORK','isOpened':'1','orgId':'2322','isFireWall':'1'}]";
		
		String scanStr2="[{'devOnlyId':'74bf0bc7199247a4b3dae1ed3529702b','regUserId':'31bf0bc7199247a4b3dae1ed3529efca','userId':'a4bf0bc7199247a4b3dae1ed3529d30f','jdata':[{'devOnlyId':'c4019b56795b49fe8ce1cd0d3477efe8','areaId':'','ip':'192.168.32.100','mac':'00-01-02-03-04-05','hostName':'majiandong-pc','groupName':'WORK','isOpened':'1','orgId':'2322','isFireWall':'1'}]}]";
		
        String scanStr3="[{'areaId':'','devOnlyId':'','groupName':'','hostName':'','ip':'192.168.88.1','isFireWall':'2','isOpened':'1','mac':'','orgId':'8729ddixusjwusjajsusjauq'},{'areaId':'','devOnlyId':'','groupName':'WORKGROUP      ','hostName':'XIUFENG        ','ip':'192.168.88.248','isFireWall':'2','isOpened':'1','mac':'90-2B-34-4D-DB-AD','orgId':'8729ddixusjwusjajsusjauq'}]";
        
		String scanStr4="[{'areaId':'','devOnlyId':'','groupName':'','hostName':'','ip':'192.168.119.76','isFireWall':'2','isOpened':'1','mac':'08-9E-01-AE-37-8C','orgId':''}]";
		
		String scanStr5="[{'areaId':'serverAreaMain','devOnlyId':'cd7ad608566c1e80868baeddd183a68c','groupName':'WORKGROUP','hostName':'WIN-2OFRIS727LP','ip':'192.168.88.232','isFireWall':'2','isOpened':'1','mac':'44-58-07-00-00-00','orgId':'org1','regAreaId':'serverAreaMain1','regOrgId':'5786c92b09e64d0d8d8441e86b33786e'}]";
		
		String scanStr6="[{'areaId':'serverAreaMain','devOnlyId':'','groupName':'VRV-E5C75B211F3','hostName':'WORKGROUP      ','ip':'192.168.32.71','isFireWall':'2','isOpened':'1','mac':'00-0C-29-A7-C1-39','orgId':'org','regAreaId':'serverAreaMain','regOrgId':'org'}]";
		
		String scanNew="[{'areaId':'serverAreaMain','devOnlyId':'','groupName':'WORKGROUP      ','hostName':'WDB-M5OUBV2JQ3 ','ip':'192.168.118.18','isFireWall':'2','isOpened':'1','mac':'08-60-6E-83-49-DC','orgId':'org','regAreaId':'serverAreaMain','regOrgId':'org'}]";
        byte[] inData1=scanNew.getBytes("UTF-8");
		byte[] inData2=scanStr3.getBytes();
	
		
		BlockServiceImpl blockService=new BlockServiceImpl();
		//IBlockService blockService = ServiceFactory.getService(IBlockService.class, 5000);

		String checkCode=CRCUtils.getCRC32StringValue(inData1);
		ByteBuffer data=ByteBuffer.wrap(inData1);
		try {
			//blockService.getDataTC(IBlockService.SERVICE_CODE, IBlockService.SCAN, checkCode, false, data, "", 0);
			blockService.getDataTS(IBlockService.SERVICE_CODE, IBlockService.SCAN, checkCode, false, data,false, "", 0);
		} catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		
	}
	
}
