/**   
* @Title: ProtectedDevice.java 
* @Package com.vrv.cems.service.block.bean 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年11月16日 下午7:01:23 
* @version V1.0   
*/
package com.vrv.cems.service.block.bean;

import java.io.Serializable;

/** 
 * @ClassName: ProtectedDevice 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年11月16日 下午7:01:23 
 *  
 */
public class ProtectedDevice implements Serializable{
	/*"ip":"192.168.32.100",
    "mac":"00-01-02-03-04-05",
    "protectState":"0/1"*/
	private String ip;
	private String mac;
	private String protectState="0";
	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	public ProtectedDevice() {
		super();
	}
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param ip
	* @param mac
	* @param protectState 
	*/
	public ProtectedDevice(String ip, String mac, String protectState) {
		super();
		this.ip = ip;
		this.mac = mac;
		this.protectState = protectState;
	}
	/** 
	 * @return ip 
	 */
	public String getIp() {
		return ip;
	}
	/** 
	 * @param ip 要设置的 ip 
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/** 
	 * @return mac 
	 */
	public String getMac() {
		return mac;
	}
	/** 
	 * @param mac 要设置的 mac 
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/** 
	 * @return protectState 
	 */
	public String getProtectState() {
		return protectState;
	}
	/** 
	 * @param protectState 要设置的 protectState 
	 */
	public void setProtectState(String protectState) {
		this.protectState = protectState;
	}
	/*
	* Title: toString
	*Description: 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "ProtectedDevice [ip=" + ip + ", mac=" + mac + ", protectState="
				+ protectState + "]";
	}
	
	
}
