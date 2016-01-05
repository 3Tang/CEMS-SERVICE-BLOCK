package com.vrv.cems.service.block.business;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.sys.common.util.DateUtils;
import com.sys.common.util.UUIDUtils;
import com.sys.common.util.security.CRCUtils;
import com.vrv.cems.service.base.interfaces.IBlockService;
import com.vrv.cems.service.block.bean.BlockBean;
import com.vrv.cems.service.block.bean.BlockDevice;
import com.vrv.cems.service.block.bean.BlockFailBean;
import com.vrv.cems.service.block.bean.BlockInfo;
import com.vrv.cems.service.block.bean.BlockResult;
import com.vrv.cems.service.block.bean.BlockUDP;
import com.vrv.cems.service.block.bean.InputBean;
import com.vrv.cems.service.block.bean.ServiceParamBean.ParamBean;
import com.vrv.cems.service.block.bean.UDPHead;
import com.vrv.cems.service.block.cache.SendedBlockTaskCache;
import com.vrv.cems.service.block.client.UDPClientCall;
import com.vrv.cems.service.block.impl.EhCacheServ;
import com.vrv.cems.service.block.utils.CommonUtil;
import com.vrv.cems.service.block.utils.DBUtil;
import com.vrv.cems.service.block.utils.JsonUtils;
import com.vrv.cems.service.block.utils.SystemConstants;

/**
 * <B>说 明</B>:
 *
 * @author 作 者 名：dongyifei<br/>
 *         E-mail ：dongyifei@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51
 */


public class SendUDPBlockTask extends TimerTask {
	
	private static Logger log = Logger.getLogger(SendUDPBlockTask.class);
	
	
	public void run() {
		Map<String,BlockResult> blockResults=new HashMap<String,BlockResult>();
		log.info("SendUDPBlockTask运行!");
		String minCode=IBlockService.SCAN;
		String blockRuleType = "";
		String blockTime = "";
		List<ParamBean> params = CommonUtil.getPolicyPrarams();
		for (ParamBean param : params) {
			if ("blockRuleType".equalsIgnoreCase(param.getKey())) {
				blockRuleType = param.getValue();
			}
			if ("blockTime".equalsIgnoreCase(param.getKey())) {
				blockTime = param.getValue();
			}
		}

		List<BlockFailBean> bfbs = new ArrayList<BlockFailBean>();
		List<BlockDevice> blockDevs = EhCacheServ.getInstance()
				.getBlockHostList();
		
		log.info("SendUDPBlockTask从缓存BLOCK_HOST_LIST获取的条数为:"+blockDevs.size());
		
		if (blockDevs == null || blockDevs.size() < 1) {
			log.error("BlockHostList为空!" + blockDevs.size());
		} else {
			for (BlockDevice blockDev : blockDevs) {
				List<BlockBean> blockBeans=toBlockBeans(blockRuleType, blockTime, blockDev);
				// 设置 data 参数
				final ByteBuffer message = getUDPMsg(minCode, blockBeans);

				// 存入缓存
				// SendedBlockTaskCache.put(targetBlockDevice, deviceBeans);

				boolean blockflag = false;
				int repeatCount = 3;
				while (repeatCount > 0) {
					if (StringUtils.isNotBlank(blockDev.getRouteIp())
							& StringUtils.isNotBlank(blockDev.getUdpPort())) {
						try {
					
							// 阻断服务与客户端UDP通讯 ，调用 客户端UDP服务
							byte[] udpRs = UDPClientCall.sendUDPData(
									message.array(), blockDev.getRouteIp(),
									Integer.valueOf(blockDev.getUdpPort()));
							
							if (udpRs.length > 1) {
								blockflag = true;
								break;
							} else {
								repeatCount--;
							}
						} catch (Exception e) {
							log.error("Exception", e);
							repeatCount--;
						}
					}
				}

				String reportTime=DateUtils.format(new Date());
		     	BlockUDP blockUDP=new BlockUDP();
				BlockResult blockResult=new BlockResult();
				List<BlockUDP> udpList=new ArrayList<BlockUDP>();
				List<BlockInfo> blockIFs=blockDev.getBlockInfo();
				if(blockIFs!=null&&blockIFs.size()>0)
				{
					for(BlockInfo blockIF:blockIFs)
					{
						String ipMacKey=blockIF.getIp()+":"+blockIF.getMac();
						blockResult.setIp(blockIF.getIp());
						blockResult.setMac(blockIF.getMac());
						blockResult.setBlockHostNum(blockIF.getBlockHostNum());
						blockResult.setReportTime(reportTime);	
						String sendUDPTime=DateUtils.format(new Date());
						blockUDP.setDevOnlyId(blockDev.getDevOnlyId());
						blockUDP.setBlockIp(blockDev.getBlockIp());
						blockUDP.setBlockTime(sendUDPTime);
						blockUDP.setLastBlockTime(sendUDPTime);
						if(blockflag){
							blockUDP.setBlockResult(SystemConstants.BLOCK_SUC);
						}
						else
						{
							blockUDP.setBlockResult(SystemConstants.BLOCK_FAIL);
						}
						blockUDP.setBlockRuleType(blockRuleType);
						
						if(blockResults.containsKey(ipMacKey))
						{
							List<BlockUDP> oldBL=blockResults.get(ipMacKey).getBlockInfo();
							oldBL.add(blockUDP);
							blockResult.setBlockInfo(oldBL);
						}
						else
						{
							udpList.add(blockUDP);
							blockResult.setBlockInfo(udpList);
						}
						blockResults.put(ipMacKey, blockResult);
						}
				}
			}
			
			//存入缓存结构
			EhCacheServ.getInstance().storeBlockResult(blockResults);
		}
		// 序列化阻断任务缓存到文件
		/*
		 * SerializableUtil.objectToFile(SendedBlockTaskCache.getMap(),
		 * SystemConstants.SENDED_BLOCK_TASK_CACHE_FILE);
		 */
		
		DBUtil.saveDeviceBlock(SendedBlockTaskCache.getMap());
	
	}

	

	/** 
	* @Title: toBlockBeans 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param blockRuleType
	* @param @param blockTime
	* @param @param blockBeans
	* @param @param blockDev  参数说明 
	* @return void    返回类型 
	* @throws 
	*/
	public static List<BlockBean> toBlockBeans(String blockRuleType, String blockTime,BlockDevice blockDev) {
		
		List<BlockBean> blockBeans=new ArrayList<BlockBean>();
		BlockBean blockBean = new BlockBean();
		List<BlockInfo> blockIFs=blockDev.getBlockInfo();
		StringBuffer sub=new StringBuffer();
		for(int i=0;i<blockIFs.size();i++)
		{
			String blockTarget=blockIFs.get(i).getIp()+":"+blockIFs.get(i).getMac();
			if(blockIFs.size()>1&&i!=(blockIFs.size()-1))
			{
				
				sub.append(blockTarget).append(";");
				//sub.append(blockIFs[i].get)
			}else{
			   sub.append(blockTarget);
			}
		}
		blockBean.setIp(blockDev.getBlockIp());
		blockBean.setBlockIp(sub.toString());
		blockBean.setBlockState(SystemConstants.BLOCK_STATE);
		blockBean.setBlockRuleType(blockRuleType);
		blockBean.setBlockTime(blockTime);
		blockBeans.add(blockBean);
		return blockBeans;
	}

	/**
	 * @Title: getUDPMsg
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param minCode
	 * @param @param blockBeans
	 * @param @return 参数说明
	 * @return ByteBuffer 返回类型
	 * @throws
	 */
	public static ByteBuffer getUDPMsg(String minCode,
			List<BlockBean> blockBeans) {
		InputBean inputBean = new InputBean();
		inputBean.setMaxCode(IBlockService.SERVICE_CODE);
		inputBean.setMinCode(minCode);
		inputBean.setJdata(blockBeans);
		byte[] blockData = null;
		try {
			blockData = JsonUtils.getJsonStr(inputBean).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			log.error("Exception", e1);
			throw new RuntimeException(e1);
		}
		UDPHead head = new UDPHead();
		head.setDwFlag("pde_");
		head.setDwVersion(0x01010101);
		head.setDwSize(blockData.length);
		String crc = CRCUtils.getCRC32StringValue(blockData);
		head.setDwCrc(crc);
		String session = UUIDUtils.get32UUID();
		head.setSzSessionId(session);
		head.setDwMsgCode(1);
		head.setDwMaxCode("0x" + IBlockService.SERVICE_CODE);
		head.setDwMinCode(Integer.parseInt(minCode));
		head.setwHeadSize((short) UDPHead.HEAD_SIZE);
		head.setwType((short) 1);
		head.setwCount((short) 1);//分包的数量,只有一个包,应设1
		head.setwIndex((short) 1);

		final ByteBuffer message = ByteBuffer.allocate(UDPHead.HEAD_SIZE
				+ blockData.length);
		message.put(head.getCBuffer());
		message.put(blockData);
		return message;
	}


	
}
