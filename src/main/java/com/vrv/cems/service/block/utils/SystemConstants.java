package com.vrv.cems.service.block.utils;

import java.util.List;

import com.sys.common.util.CommentedProperties;
import com.vrv.cems.service.block.ServiceMain;
import com.vrv.cems.service.block.bean.ServiceParamBean.ParamBean;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class SystemConstants extends com.vrv.cems.service.base.SystemConstants {

	static CommentedProperties prop=new CommentedProperties();
	/**扫描服务子功能号*/
	public static final String SCAN_SERVICE_CODE = "1";
	/**在线服务子功能号*/
	public static final String ONLINE_SERVICE_CODE = "2";
	/**Web平台子功能号*/
	public static final String WEB_SERVICE_CODE = "3";
	/**阻断服务接收客户端阻断结果TCP通讯*/
	public static final String BLOCK_TCP_SERVICE_CODE = "4";
	/**更新策略服务 功能号*/
	public static final String UPDATE_POLICY_SERVICE_CODE = "1000";
	
	public static final String CHANGE_SERVICE_AREA_CODE="1001";
	
	public static final String AGENCY_SCAN="3100";
	
	/**请求成功*/
	public static final String REQUEST_SUCCESS = "0";
	/**请求失败*/
	public static final String REQUEST_FAIL = "1";
	/**阻断服务监听端口号*/
	public static final int PORT =Integer.valueOf(prop.getPropertyFromCemsDatByKey("path.config.properties", "service.port")); 
	/**在线服务主功能号*/
	public static final String ONLINE_SERVICE_MAXCODE = "00010300";
	/**数据处理服务主功能号*/
	public static final String DATAPROCESS_SERVICE_MAXCODE = "00010900";
	/**数据处理服务入mysql子功能号*/
	public static final String DATAPROCESS_SERVICE_MYSQL_MINCODE = "1";
	/**阻断服务主功能号*/
	public static final String BLOCK_SERVICE_MAXCODE = "00010400";
	/**与客户端udp通信子功能号*/
	public static final String CLIENT_UDP_MINCODE = "1";
	/**上报未阻断告警信息子功能号*/
	public static final String REPORT_NO_BLOCK_MINCODE = "1";
	/**在线服务提供获取设备状态子功能号*/
	public static final String ONLINE_SERVICE_DEVICE_STATUS_MINCODE = "6";
	/**在线服务获取攻击主机子功能号*/
	public static final String ONLINE_SERVICE_GET_HOST_MINCODE = "7";
	/**Timer xml文件路径
	阻断任务缓存文件名*/
	public static final String SENDED_BLOCK_TASK_CACHE_FILE = prop.getPropertyFromCemsDat("path.sendedBlockTaskCacheFile");
	
	public static final boolean ISENCRYPT = false;
	
	public static boolean ISZIP = false;
	/**阻断类型*/
	//public static String blockType = prop.getPropertyFromCemsDatByKey("path.config.properties", "blockType");
	public static String cacheAddress="192.168.0.133";
	
	public static int cachePort=8700;

	/**是否开机(0-未开机,1-开机)*/
	public static final String DEVICE_OPEN="1";
	
	public static final String DEVICE_NOT_OPEN="0";
	
	/**保护状态(0-未保护,1-保护)*/
	public static final String DEVICE_PROTECTED="1";
	
	public static final String DEVICE_NOT_PROTECTED="0";
	
	public static final String BLOCK_EHCACHE="blockService";
	
	public static final String SCAN_RESULT_LIST="scanResult";
	
	public static final String BLOCK_HOST_LIST="blockHost";
		
	public static final String BLOCK_TARGET_LIST="blockTarget";
	
	public static final String BLOCK_FAIL_LIST="blockFail";
	
	public static final String BLOCK_STATE="1";
	
	public static final String BLOCK_FAIL="1";
	
	public static final String BLOCK_SUC="0";
	
}
