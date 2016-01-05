package com.vrv.cems.service.block.utils.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vrv.cems.service.block.utils.ReadConfigFileUtil;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class TestReadConfigFileUtil {
	@Test
	public void getValueTest(){
		assertEquals(ReadConfigFileUtil.getValue("blockType"), "1");
	}
	@Test
	public void setValueTest(){
		ReadConfigFileUtil.setValue("blockType", "2");
	}
	@Test
	public void saveTest(){
		ReadConfigFileUtil.setValue("blockType", "1");
		ReadConfigFileUtil.save();
	}
}
