package com.vrv.cems.service.block.utils.test;

import org.junit.Test;

import com.vrv.cems.service.block.bean.DeviceBean;
import com.vrv.cems.service.block.bean.OutBean;
import com.vrv.cems.service.block.utils.JsonUtils;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class TestJsonUtils {
	@Test
	public void tss(){
		String ss = "{\"desc\":\"主功能号错误\",\"jdata\":[],\"maxCode\":\"00010300\",\"minCode\":\"6\",\"result\":\"1\"}";
		OutBean ob = JsonUtils.jsonToBean(ss, OutBean.class, DeviceBean.class);
		System.out.print(ob);
		OutBean ob1 = JsonUtils.jsonToBean(ss, OutBean.class,null);
		System.out.print(ob1);
	}
}
