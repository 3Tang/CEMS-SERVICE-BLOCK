/**   
* @Title: BlockDevice.java 
* @Package com.vrv.cems.service.block.bean 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年12月7日 下午5:56:46 
* @version V1.0   
*/
package com.vrv.cems.service.block.bean;

import java.io.Serializable;
import java.util.List;

/** 
 * @ClassName: BlockDevice 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年12月7日 下午5:56:46 
 *  
 */
public class BlockDevice implements Serializable{
	private String devOnlyId;
	private String blockIp;
	private String routeIp;
	private String udpPort;
	private String sendTime;
	private List<BlockInfo> blockInfo;
	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	public BlockDevice() {
		super();
	}
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param devOnlyId
	* @param blockIp
	* @param routeIp
	* @param udpPort
	* @param sendTime
	* @param blockInfo 
	*/
	public BlockDevice(String devOnlyId, String blockIp, String routeIp,
			String udpPort, String sendTime, List<BlockInfo> blockInfo) {
		super();
		this.devOnlyId = devOnlyId;
		this.blockIp = blockIp;
		this.routeIp = routeIp;
		this.udpPort = udpPort;
		this.sendTime = sendTime;
		this.blockInfo = blockInfo;
	}
	/** 
	 * @return devOnlyId 
	 */
	public String getDevOnlyId() {
		return devOnlyId;
	}
	/** 
	 * @param devOnlyId 要设置的 devOnlyId 
	 */
	public void setDevOnlyId(String devOnlyId) {
		this.devOnlyId = devOnlyId;
	}
	/** 
	 * @return blockIp 
	 */
	public String getBlockIp() {
		return blockIp;
	}
	/** 
	 * @param blockIp 要设置的 blockIp 
	 */
	public void setBlockIp(String blockIp) {
		this.blockIp = blockIp;
	}
	/** 
	 * @return routeIp 
	 */
	public String getRouteIp() {
		return routeIp;
	}
	/** 
	 * @param routeIp 要设置的 routeIp 
	 */
	public void setRouteIp(String routeIp) {
		this.routeIp = routeIp;
	}
	/** 
	 * @return udpPort 
	 */
	public String getUdpPort() {
		return udpPort;
	}
	/** 
	 * @param udpPort 要设置的 udpPort 
	 */
	public void setUdpPort(String udpPort) {
		this.udpPort = udpPort;
	}
	/** 
	 * @return sendTime 
	 */
	public String getSendTime() {
		return sendTime;
	}
	/** 
	 * @param sendTime 要设置的 sendTime 
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	/** 
	 * @return blockInfo 
	 */
	public List<BlockInfo> getBlockInfo() {
		return blockInfo;
	}
	/** 
	 * @param blockInfo 要设置的 blockInfo 
	 */
	public void setBlockInfo(List<BlockInfo> blockInfo) {
		this.blockInfo = blockInfo;
	}

	
	
}
