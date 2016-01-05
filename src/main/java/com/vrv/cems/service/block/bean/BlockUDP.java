/**   
* @Title: BlockUDP.java 
* @Package com.vrv.cems.service.block.bean 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年12月30日 下午4:53:11 
* @version V1.0   
*/
package com.vrv.cems.service.block.bean;

/** 
 * @ClassName: BlockUDP 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年12月30日 下午4:53:11 
 *  
 */
public class BlockUDP {
	private String devOnlyId;
	private String blockTime;
	private String lastBlockTime;
	private String blockIp;
	private String blockRuleType;
	private String blockResult;
	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	public BlockUDP() {
		super();
	}
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param devOnlyId
	* @param blockTime
	* @param lastBlockTime
	* @param blockIp
	* @param blockRuleType
	* @param blockResult 
	*/
	public BlockUDP(String devOnlyId, String blockTime, String lastBlockTime,
			String blockIp, String blockRuleType, String blockResult) {
		super();
		this.devOnlyId = devOnlyId;
		this.blockTime = blockTime;
		this.lastBlockTime = lastBlockTime;
		this.blockIp = blockIp;
		this.blockRuleType = blockRuleType;
		this.blockResult = blockResult;
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
	 * @return blockTime 
	 */
	public String getBlockTime() {
		return blockTime;
	}
	/** 
	 * @param blockTime 要设置的 blockTime 
	 */
	public void setBlockTime(String blockTime) {
		this.blockTime = blockTime;
	}
	/** 
	 * @return lastBlockTime 
	 */
	public String getLastBlockTime() {
		return lastBlockTime;
	}
	/** 
	 * @param lastBlockTime 要设置的 lastBlockTime 
	 */
	public void setLastBlockTime(String lastBlockTime) {
		this.lastBlockTime = lastBlockTime;
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
	 * @return blockRuleType 
	 */
	public String getBlockRuleType() {
		return blockRuleType;
	}
	/** 
	 * @param blockRuleType 要设置的 blockRuleType 
	 */
	public void setBlockRuleType(String blockRuleType) {
		this.blockRuleType = blockRuleType;
	}
	/** 
	 * @return blockResult 
	 */
	public String getBlockResult() {
		return blockResult;
	}
	/** 
	 * @param blockResult 要设置的 blockResult 
	 */
	public void setBlockResult(String blockResult) {
		this.blockResult = blockResult;
	}
	/*
	* Title: toString
	*Description: 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "BlockUDP [devOnlyId=" + devOnlyId + ", blockTime=" + blockTime
				+ ", lastBlockTime=" + lastBlockTime + ", blockIp=" + blockIp
				+ ", blockRuleType=" + blockRuleType + ", blockResult="
				+ blockResult + "]";
	}
	
	
	
}
