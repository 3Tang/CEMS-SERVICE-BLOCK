package com.vrv.cems.service.block.task;

import java.util.LinkedList;

import com.vrv.cems.service.block.bean.BlockTaskBean;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class TaskQueue {
	private static LinkedList<BlockTaskBean> blockTaskBeans = new LinkedList<BlockTaskBean>();
	public synchronized static void addTask(BlockTaskBean taskBean){
		blockTaskBeans.add(taskBean);
	}
	public static void clearAllTask(){
		blockTaskBeans.clear();
	}
	public synchronized static BlockTaskBean poll(){
		return blockTaskBeans.poll();
	}
	public synchronized static boolean remove(BlockTaskBean taskBean){
		return blockTaskBeans.remove(taskBean);
	}
	public static boolean isEmpty(){
		return blockTaskBeans.isEmpty();
	}
	public static LinkedList<BlockTaskBean> getAll()
	{
		return blockTaskBeans;
	}
}
