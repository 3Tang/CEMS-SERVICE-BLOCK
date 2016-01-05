package com.vrv.cems.service.block.bean; 
/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月16日 下午1:15:33 
 */
public class BlockFailBean {
	private String deviceIp;
	private String devOnlyId;
	private String mac;
	private long logTime;
	private String description;
	private String routeIp;
	private String type;
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}
	public String getDevOnlyId() {
		return devOnlyId;
	}
	public void setDevOnlyId(String devOnlyId) {
		this.devOnlyId = devOnlyId;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRouteIp() {
		return routeIp;
	}
	public void setRouteIp(String routeIp) {
		this.routeIp = routeIp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getLogTime() {
		return logTime;
	}
	public void setLogTime(long logTime) {
		this.logTime = logTime;
	}
}
 