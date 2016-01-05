package com.vrv.cems.service.block.bean; 
/** 
 *   <B>说       明</B>: 阻断服务接收客户端阻断结果TCP通讯 Jdata Bean
 *
 * @author  作  者  名：daiyijun<br/>
 *		    E-mail ：daiyijun@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年3月18日 上午11:41:49 
 */
public class ReciveBlockBean {
	/**任务ID*/
	private String taskId;
	/**多个IP以;分隔*/
	private String blockIP;
	/**设备唯一ID*/
	private String deviceOnylId;
	/**第几次阻断*/
	private String blockCount;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getBlockIP() {
		return blockIP;
	}
	public void setBlockIP(String blockIP) {
		this.blockIP = blockIP;
	}
	public String getDeviceOnylId() {
		return deviceOnylId;
	}
	public void setDeviceOnylId(String deviceOnylId) {
		this.deviceOnylId = deviceOnylId;
	}
	public String getBlockCount() {
		return blockCount;
	}
	public void setBlockCount(String blockCount) {
		this.blockCount = blockCount;
	} 
	
}
 
