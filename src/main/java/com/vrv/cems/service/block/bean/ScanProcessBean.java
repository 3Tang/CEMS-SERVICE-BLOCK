package com.vrv.cems.service.block.bean;

import java.io.Serializable;
import java.util.List;

public class ScanProcessBean implements Serializable {
	
	       /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 457755022100248754L;
		private String areaId;
		   private String regAreaId;
           private  String devOnlyId;
           private	String ip;
           private	String mac;
           private	String hostName;
           private  String groupName;
           private	String isOpened;
           private	String orgId;
           private  String regOrgId;
           private	String isFireWall;

           
		public ScanProcessBean() {
			super();
		}


		/** 
		* <p>Title: </p> 
		* <p>Description: </p> 
		* @param areaId
		* @param regAreaId
		* @param devOnlyId
		* @param ip
		* @param mac
		* @param hostName
		* @param groupName
		* @param isOpened
		* @param orgId
		* @param regOrgId
		* @param isFireWall 
		*/
		public ScanProcessBean(String areaId, String regAreaId,
				String devOnlyId, String ip, String mac, String hostName,
				String groupName, String isOpened, String orgId,
				String regOrgId, String isFireWall) {
			super();
			this.areaId = areaId;
			this.regAreaId = regAreaId;
			this.devOnlyId = devOnlyId;
			this.ip = ip;
			this.mac = mac;
			this.hostName = hostName;
			this.groupName = groupName;
			this.isOpened = isOpened;
			this.orgId = orgId;
			this.regOrgId = regOrgId;
			this.isFireWall = isFireWall;
		}


		/** 
		 * @return areaId 
		 */
		public String getAreaId() {
			return areaId;
		}


		/** 
		 * @param areaId 要设置的 areaId 
		 */
		public void setAreaId(String areaId) {
			this.areaId = areaId;
		}


		/** 
		 * @return regAreaId 
		 */
		public String getRegAreaId() {
			return regAreaId;
		}


		/** 
		 * @param regAreaId 要设置的 regAreaId 
		 */
		public void setRegAreaId(String regAreaId) {
			this.regAreaId = regAreaId;
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
		 * @return hostName 
		 */
		public String getHostName() {
			return hostName;
		}


		/** 
		 * @param hostName 要设置的 hostName 
		 */
		public void setHostName(String hostName) {
			this.hostName = hostName;
		}


		/** 
		 * @return groupName 
		 */
		public String getGroupName() {
			return groupName;
		}


		/** 
		 * @param groupName 要设置的 groupName 
		 */
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}


		/** 
		 * @return isOpened 
		 */
		public String getIsOpened() {
			return isOpened;
		}


		/** 
		 * @param isOpened 要设置的 isOpened 
		 */
		public void setIsOpened(String isOpened) {
			this.isOpened = isOpened;
		}


		/** 
		 * @return orgId 
		 */
		public String getOrgId() {
			return orgId;
		}


		/** 
		 * @param orgId 要设置的 orgId 
		 */
		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}


		/** 
		 * @return regOrgId 
		 */
		public String getRegOrgId() {
			return regOrgId;
		}


		/** 
		 * @param regOrgId 要设置的 regOrgId 
		 */
		public void setRegOrgId(String regOrgId) {
			this.regOrgId = regOrgId;
		}


		/** 
		 * @return isFireWall 
		 */
		public String getIsFireWall() {
			return isFireWall;
		}


		/** 
		 * @param isFireWall 要设置的 isFireWall 
		 */
		public void setIsFireWall(String isFireWall) {
			this.isFireWall = isFireWall;
		}


		/*
		* Title: toString
		*Description: 
		* @return 
		* @see java.lang.Object#toString() 
		*/
		@Override
		public String toString() {
			return "ScanProcessBean [areaId=" + areaId + ", regAreaId="
					+ regAreaId + ", devOnlyId=" + devOnlyId + ", ip=" + ip
					+ ", mac=" + mac + ", hostName=" + hostName
					+ ", groupName=" + groupName + ", isOpened=" + isOpened
					+ ", orgId=" + orgId + ", regOrgId=" + regOrgId
					+ ", isFireWall=" + isFireWall + "]";
		}

		
	
}
