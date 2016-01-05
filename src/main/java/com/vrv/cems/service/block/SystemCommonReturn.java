package com.vrv.cems.service.block;

import java.nio.ByteBuffer;
import java.util.List;

import com.vrv.cems.service.block.bean.OutBean;
import com.vrv.cems.service.block.utils.JsonUtils;

public class SystemCommonReturn {
	public static ByteBuffer commonReturn(String maxCode, String minCode,String result,String desc,List jdata){
		OutBean outBean = new OutBean();
		outBean.setMaxCode(maxCode);
		outBean.setMinCode(minCode);
		outBean.setResult(result);
		outBean.setDesc(desc);
		outBean.setJdata(jdata);
		String rStr = JsonUtils.getJsonStr(outBean);
		System.out.println("公共返回"+ rStr);
		return ByteBuffer.wrap(rStr.getBytes());
	}
}
