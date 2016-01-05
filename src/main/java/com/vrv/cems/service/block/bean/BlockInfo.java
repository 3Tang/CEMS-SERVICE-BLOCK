/**   
* @Title: BlockInfo.java 
* @Package com.vrv.cems.service.block.bean 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年12月29日 下午2:41:59 
* @version V1.0   
*/
package com.vrv.cems.service.block.bean;

/** 
 * @ClassName: BlockInfo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年12月29日 下午2:41:59 
 *  
 */
public class BlockInfo {
	private String ip;
	private String mac;
	private String blockHostNum;
	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	public BlockInfo() {
		super();
	}
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param ip
	* @param mac
	* @param blockHostNum 
	*/
	public BlockInfo(String ip, String mac, String blockHostNum) {
		super();
		this.ip = ip;
		this.mac = mac;
		this.blockHostNum = blockHostNum;
	}
	/*
	* Title: toString
	*Description: 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "BlockInfo [ip=" + ip + ", mac=" + mac + ", blockHostNum="
				+ blockHostNum + "]";
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

	
	
	
	

}
