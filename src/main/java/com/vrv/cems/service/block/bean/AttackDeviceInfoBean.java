package com.vrv.cems.service.block.bean;

import java.util.List;

public class AttackDeviceInfoBean {

	private String ip;
	private String mac;
	private List<BlockHostBean> blockHost;

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

	/*public List<DeviceBean> getJdata() {
		return jdata;
	}

	public void setJdata(List<DeviceBean> jdata) {
		this.jdata = jdata;
	}*/

	public List<BlockHostBean> getBlockHost() {
		return blockHost;
	}

	public void setBlockHost(List<BlockHostBean> blockHost) {
		this.blockHost = blockHost;
	}



}
