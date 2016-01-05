package com.vrv.cems.service.block.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

import com.vrv.cems.service.block.CommomThreadPool;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class TestCommomThreadPool {
	@Test
	public void joinThreadPoolTest(){
		List<Runnable> rs = new ArrayList<Runnable>();
		FutureTask<String> future = null;
		for(int i = 0; i < 20; i++){
			future = new FutureTask<String>(new Callable<String>() {
		         public String call() {
		           int a = 0;
		           for(int i = 0; i < 10000 ; i++) {
		        	   a = i * 2;
		           }
		           return new String().valueOf(a);
		       }});
			rs.add(future);
		}
		CommomThreadPool.joinThreadPool(rs);
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
