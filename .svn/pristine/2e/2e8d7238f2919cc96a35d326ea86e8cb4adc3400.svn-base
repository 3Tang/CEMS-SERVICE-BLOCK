/**
 * <b>说	明:</b>
 * @author 作      者   :alex-tang <br>
 * 		   Email:alex-tang@vrvmail.com.cn
 *
 * @version 版   本 :V1.0<br>
 *			时   间 :2015年6月26日下午2:09:56
 */

package com.vrv.cems.service.block.business;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sys.common.util.StringUtils;
import com.vrv.cems.service.base.interfaces.IBlockService;
import com.vrv.cems.service.base.interfaces.IOnlineService;
import com.vrv.cems.service.base.server.Service;
import com.vrv.cems.service.base.server.factory.ServiceFactory;
import com.vrv.cems.service.block.CommomThreadPool;
import com.vrv.cems.service.block.SystemCommonReturn;
import com.vrv.cems.service.block.bean.DeviceBean;
import com.vrv.cems.service.block.bean.InputBean;
import com.vrv.cems.service.block.utils.JsonUtils;
import com.vrv.cems.service.block.utils.SystemConstants;
import com.vrv.cems.service.configure.utils.ReadConfigPropertiesUtils;
import com.vrv.im.client.ConfigServerUtil;
import com.vrv.im.client.pool.ClientHandler;
import com.vrv.im.service.ServiceConfigBean;

/** 
 *   <B>说       明</B>:ServerAreaUpdateService
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年6月26日下午2:09:56 
 */
public class ServerAreaUpdateService extends BusinessProcess {
	Service service = ServiceFactory.DEFAULT.getService();
	private static final Logger logger = Logger.getLogger(ServerAreaUpdateService.class);
	public ByteBuffer process(final String maxCode, final String minCode, String checkCode,
			boolean isZip, String data) {
		String s = data;
		logger.info("网页平台 更改服务区域接口 数据" + s);
		JSONObject dataJson = JSONObject.fromObject(data);
		final String serverAreaId = (String) dataJson.get("serverAreaId");
		if(StringUtils.isNotBlank(serverAreaId)){
			String localServerAreaId = ReadConfigPropertiesUtils.getValue(SystemConstants.PATH_CONFIG_PROPERTIES, "service.serverAreaId");
			if(!localServerAreaId.equals(serverAreaId)&localServerAreaId!=null){
				final Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					int count=3;
					@Override
					public void run() {
						count--;
						service.reRegister(serverAreaId);
						String areaId = "";
						List<ServiceConfigBean> serviceBeanList = null;
						ClientHandler ch = null;
						try {
							ch = ConfigServerUtil.getConfigServiceClient();
						} catch (Exception e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						try {
							serviceBeanList = (List<ServiceConfigBean>) ch.invokeHandler(
									"loadServices", null);
							for (ServiceConfigBean servBean : serviceBeanList) {
								if (servBean.serviceID.equals(IBlockService.SERVICE_CODE)) {
									areaId = servBean.getOrgID();
								}
							}								
								if(areaId.equals(serverAreaId)||count<=0)
								{
									timer.cancel();
								}
						} catch (Exception e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
				}
				}, 0, 60*1000);
				
				return SystemCommonReturn.commonReturn(maxCode, minCode, SystemConstants.REQUEST_SUCCESS, "成功", new ArrayList());
			}else{
				logger.info("服务区域未改变,无需重新注册");
			}
			
		}else{		
			return SystemCommonReturn.commonReturn(maxCode, minCode, SystemConstants.REQUEST_FAIL, "失败", new ArrayList());
		}
		return null;

	}
}
