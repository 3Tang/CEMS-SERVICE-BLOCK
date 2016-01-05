package com.vrv.cems.service.block.bean;

import java.util.List;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class BlockTaskBean {
	//阻断目标
	private DeviceBean blockTargetDevice;
	//阻断源 能够 阻断 阻断目标 的设备
	private List<DeviceBean> blockSrcDevices;
	
	public BlockTaskBean() {
		super();
	}
	public BlockTaskBean(DeviceBean blockTargetDevice) {
		super();
		this.blockTargetDevice = blockTargetDevice;
	}
	public DeviceBean getBlockTargetDevice() {
		return blockTargetDevice;
	}
	public void setBlockTargetDevice(DeviceBean blockTargetDevice) {
		this.blockTargetDevice = blockTargetDevice;
	}
	public List<DeviceBean> getBlockSrcDevices() {
		return blockSrcDevices;
	}
	public void setBlockSrcDevices(List<DeviceBean> blockSrcDevices) {
		this.blockSrcDevices = blockSrcDevices;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((blockTargetDevice == null) ? 0 : blockTargetDevice
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockTaskBean other = (BlockTaskBean) obj;
		if (blockTargetDevice == null) {
			if (other.blockTargetDevice != null)
				return false;
		} else if (!blockTargetDevice.equals(other.blockTargetDevice))
			return false;
		return true;
	}
}
