/**   
* @Title: DeviceKeyBean.java 
* @Package com.vrv.cems.service.block.bean 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年7月31日 下午4:09:30 
* @version V1.0   
*/
package com.vrv.cems.service.block.bean;

/** 
 * @ClassName: DeviceKeyBean 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年7月31日 下午4:09:30 
 *  
 */
public class DeviceKeyBean {
	/**
	 * 加密密钥类型
	 */
	private String keyType;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 设备唯一ID
	 */
	private String devOnlyId;
	/**
	 * 加密偏移向量
	 */
	private String offsetVector;
	
	
	
	public DeviceKeyBean() {
		super();
	}



	public DeviceKeyBean(String keyType, String password, String devOnlyId,
			String offsetVector) {
		super();
		this.keyType = keyType;
		this.password = password;
		this.devOnlyId = devOnlyId;
		this.offsetVector = offsetVector;
	}



	public String getKeyType() {
		return keyType;
	}



	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getDevOnlyId() {
		return devOnlyId;
	}



	public void setDevOnlyId(String devOnlyId) {
		this.devOnlyId = devOnlyId;
	}



	public String getOffsetVector() {
		return offsetVector;
	}



	public void setOffsetVector(String offsetVector) {
		this.offsetVector = offsetVector;
	}
	

}
