package com.vrv.cems.service.block.business;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.sys.common.util.StringUtils;
import com.vrv.cems.service.block.SystemCommonReturn;
import com.vrv.cems.service.block.bean.ProtectedDevice;
import com.vrv.cems.service.block.bean.ScanProcessBean;
import com.vrv.cems.service.block.bean.ServiceParamBean.ParamBean;
import com.vrv.cems.service.block.impl.EhCacheServ;
import com.vrv.cems.service.block.utils.CommonUtil;
import com.vrv.cems.service.block.utils.DBUtil;
import com.vrv.cems.service.block.utils.SystemConstants;
import com.vrv.cems.service.configure.utils.ReadConfigPropertiesUtils;

/**
 * <B>说 明</B>:ScanProcess 阻断服务 接受扫描服务接口
 *
 * @author 作 者 名 ：tangtieqiao<br/>
 *         E-mail ：tangtieqiao@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2015年6月8日上午11:11:17
 */
public class ScanProcess extends BusinessProcess {
	private static final Logger log = Logger.getLogger(ScanProcess.class);
	static final Timer timer = new Timer();	
	static SendUDPBlockTask sendUdpTask=new SendUDPBlockTask();
	/*
	 * 扫描服务接口处理主逻辑 <p>Title: process</p> <p>Description: </p>
	 * 
	 * @param maxCode
	 * 
	 * @param minCode
	 * 
	 * @param checkCode
	 * 
	 * @param isZip
	 * 
	 * @param data
	 * 
	 * @return ByteBuffer
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public ByteBuffer process(final String maxCode, final String minCode,
			String checkCode, boolean isZip, String data) {
        log.info("阻断服务2100接口");
		String isBlock = "";
		String blockTime="";
		List<ParamBean> params = CommonUtil.getPolicyPrarams();
		for (ParamBean param : params) {
			if ("isBlock".equalsIgnoreCase(param.getKey())) {
				isBlock = param.getValue();
				log.info("是否阻断的参数isBlock为" + isBlock + ":0为不阻断,1为阻断");
			}	
			if ("blockTime".equalsIgnoreCase(param.getKey())) {
				blockTime = param.getValue();
			}
			
		}
		// 初始化 服务的AreaId
		String localServerAreaId = ReadConfigPropertiesUtils.getValue(
				SystemConstants.PATH_CONFIG_PROPERTIES, "service.serverAreaId");

		if (isBlock.equals("0")) {// 不阻断不进行任何处理直接返回
			return SystemCommonReturn.commonReturn(maxCode, minCode,
					SystemConstants.REQUEST_SUCCESS, "接受数据成功,不执行阻断!",
					new ArrayList());
		}

		/*
		 * InputBean inputBean = JsonUtils.jsonToBean(s, InputBean.class,
		 * DeviceBean.class); List<DeviceBean> scanInputBeans =
		 * inputBean.getJdata();
		 */
		// 传入的参数变了
		// 根据扫描服务推来的数据生成请求在线服务的数据
		 List<ProtectedDevice> protectedDevs = new ArrayList<ProtectedDevice>();
		/*
		 * InputBean onlineInputBean = new InputBean(); JSONObject jsonObj = new
		 * JSONObject();
		 */
		Map<String, ScanProcessBean> spbMap = new HashMap<String, ScanProcessBean>();
		JSONArray jsonarray = JSONArray.fromObject(data);
		Collection<ScanProcessBean> scanProList = (List<ScanProcessBean>) JSONArray
				.toCollection(jsonarray, ScanProcessBean.class);
		log.info("2100接口获取的扫描未注册客户端数量为"+scanProList.size());
		for (ScanProcessBean scanBean : scanProList) {
			// 不论漫游与否,都要存在ScanResult里面,不对mac做强制验证
			if (StringUtils.isNotBlank(scanBean.getIp())&&scanBean.getIsOpened().equals("1")) {
				//DeviceBean deviceBean = new DeviceBean();
				ProtectedDevice protectedDev=new ProtectedDevice();
				String innerKey = scanBean.getIp() + ":" + scanBean.getMac();
				spbMap.put(innerKey, scanBean);
				// 若devOnlyId不空,则走漫游处理逻辑,为空,则走阻断处理逻辑
				if (StringUtils.isNotBlank(scanBean.getDevOnlyId())) {
					if (DBUtil.isRoam(scanBean.getDevOnlyId())) {
						//漫游设备先不处理
					}
				} else {
					protectedDev = DBUtil.getProtectState(scanBean);
				}
				protectedDevs.add(protectedDev);
			}
		}
		// 存储scanResult
		log.info("扫描服务存储到ScanResult缓存的未注册客户端数量为"+spbMap.size());
		EhCacheServ.getInstance().storeScanResult(spbMap);
	
		// 请求在线服务成功的处理
		if (protectedDevs != null && protectedDevs.size() > 0) {
			log.info("单次扫描符合阻断条件的未注册客户端数量为"+protectedDevs.size());
			DeviceStateChangeProcess.changeProcess(protectedDevs);
			timer.scheduleAtFixedRate(sendUdpTask, 0, Long.parseLong(blockTime)*1000*60*2);
				/*CommomThreadPool.joinThreadPool(new Runnable() {
				public void run() {
					// 生成阻断任务
					DeviceStateChangeProcess.changeProcess(protectedDevs);
					// 发送阻断
					SendUDPBlock.send(IBlockService.SERVICE_CODE, minCode);
				}
			});*/

		}// 请求在线服务失败的处理(根据失败类型进行不同处理)
		return SystemCommonReturn.commonReturn(maxCode, minCode,
				SystemConstants.REQUEST_SUCCESS, "成功", new ArrayList());
	}





	

	/**
	 * @return
	 * @Title: deviceRoamProcess
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param scanBean 参数说明
	 * @return void 返回类型
	 * @throws
	 */
	private void deviceRoamProcess(ScanProcessBean scanBean) {
		// TODO 自动生成的方法存根

	}

}
