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
public class InputBean {
	private String maxCode;
	private String minCode;
	private String blockHostNum;
	private List jdata;
	public String getMaxCode() {
		return maxCode;
	}
	public void setMaxCode(String maxCode) {
		this.maxCode = maxCode;
	}
	public String getMinCode() {
		return minCode;
	}
	public void setMinCode(String minCode) {
		this.minCode = minCode;
	}
	
	
	public String getBlockHostNum() {
		return blockHostNum;
	}
	public void setBlockHostNum(String blockHostNum) {
		this.blockHostNum = blockHostNum;
	}
	public List getJdata() {
		return jdata;
	}
	public void setJdata(List jdata) {
		this.jdata = jdata;
	}
	
}
