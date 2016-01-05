/**   
* @Title: BlockResult.java 
* @Package com.vrv.cems.service.block.bean 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年12月8日 下午3:09:35 
* @version V1.0   
*/
package com.vrv.cems.service.block.bean;

import java.io.Serializable;
import java.util.List;

/** 
 * @ClassName: BlockResult 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年12月8日 下午3:09:35 
 *  
 */
public class BlockResult implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -4839544714371397805L;
	private String ip;
	private String mac;
	private String blockHostNum;
	private String reportTime;
	List<BlockUDP> blockInfo;
	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	public BlockResult() {
		super();
	}
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param ip
	* @param mac
	* @param blockHostNum
	* @param reportTime
	* @param blockInfo 
	*/
	public BlockResult(String ip, String mac, String blockHostNum,
			String reportTime, List<BlockUDP> blockInfo) {
		super();
		this.ip = ip;
		this.mac = mac;
		this.blockHostNum = blockHostNum;
		this.reportTime = reportTime;
		this.blockInfo = blockInfo;
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
	 * @return blockHostNum 
	 */
	public String getBlockHostNum() {
		return blockHostNum;
	}
	/** 
	 * @param blockHostNum 要设置的 blockHostNum 
	 */
	public void setBlockHostNum(String blockHostNum) {
		this.blockHostNum = blockHostNum;
	}
	/** 
	 * @return reportTime 
	 */
	public String getReportTime() {
		return reportTime;
	}
	/** 
	 * @param reportTime 要设置的 reportTime 
	 */
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	/** 
	 * @return blockInfo 
	 */
	public List<BlockUDP> getBlockInfo() {
		return blockInfo;
	}
	/** 
	 * @param blockInfo 要设置的 blockInfo 
	 */
	public void setBlockInfo(List<BlockUDP> blockInfo) {
		this.blockInfo = blockInfo;
	}
	/** 
	 * @return serialversionuid 
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/*
	* Title: toString
	*Description: 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "BlockResult [ip=" + ip + ", mac=" + mac + ", blockHostNum="
				+ blockHostNum + ", reportTime=" + reportTime + ", blockInfo="
				+ blockInfo + "]";
	}

	
}
