package com.vrv.cems.service.block.bean;

public class ScanProcessJdata {      	  
	private String devOnlyId;
	private String areaId;
	private String ip;
	private String mac;
	private String hostName;
	private String groupName;
	private String isOpened;
	private String orgId;
	private String isFireWall;
	
	public ScanProcessJdata() {
		super();
	}

	public ScanProcessJdata(String devOnlyId, String areaId, String ip,
			String mac, String hostName, String groupName, String isOpened,
			String orgId, String isFireWall) {
		super();
		this.devOnlyId = devOnlyId;
		this.areaId = areaId;
		this.ip = ip;
		this.mac = mac;
		this.hostName = hostName;
		this.groupName = groupName;
		this.isOpened = isOpened;
		this.orgId = orgId;
		this.isFireWall = isFireWall;
	}

	public String getDevOnlyId() {
		return devOnlyId;
	}

	public void setDevOnlyId(String devOnlyId) {
		this.devOnlyId = devOnlyId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIsOpened() {
		return isOpened;
	}

	public void setIsOpened(String isOpened) {
		this.isOpened = isOpened;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getIsFireWall() {
		return isFireWall;
	}

	public void setIsFireWall(String isFireWall) {
		this.isFireWall = isFireWall;
	}

	
}
