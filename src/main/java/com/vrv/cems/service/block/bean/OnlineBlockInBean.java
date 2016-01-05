package com.vrv.cems.service.block.bean;

import java.util.List;

public class OnlineBlockInBean {
	
	private String blockHostNum;
	private List<DeviceBean> jdata;
	
	public OnlineBlockInBean() {
		super();
	}
	public OnlineBlockInBean(String blockHostNum, List<DeviceBean> jdata) {
		super();
		this.blockHostNum = blockHostNum;
		this.jdata = jdata;
	}
	public String getBlockHostNum() {
		return blockHostNum;
	}
	public void setBlockHostNum(String blockHostNum) {
		this.blockHostNum = blockHostNum;
	}
	public List<DeviceBean> getJdata() {
		return jdata;
	}
	public void setJdata(List<DeviceBean> jdata) {
		this.jdata = jdata;
	}
	

}
