package com.vrv.cems.service.block.cache; 

import java.util.HashMap;  
import java.util.Map;  
import com.vrv.cems.service.block.bean.SendBlockBean;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：daiyijun<br/>
 *		    E-mail ：daiyijun@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年3月18日 下午2:04:05 
 */
public class BlockBeanCache {
	/**BlockBean 集命*/
	private static Map<String,SendBlockBean> BLOCKBEANS = new HashMap<String,SendBlockBean>();
	public synchronized static void put(String taskId, SendBlockBean sendBlockBean){
		BLOCKBEANS.put(taskId,sendBlockBean);
	}
	public synchronized static SendBlockBean get(String taskId){
		return BLOCKBEANS.get( taskId );
	}
}
 