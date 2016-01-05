package com.vrv.cems.service.block; 
 

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

import com.sys.common.util.CommentedProperties;
import com.vrv.cems.service.base.server.Service;
import com.vrv.cems.service.base.server.factory.ServiceFactory;
import com.vrv.cems.service.block.bean.ServiceParamBean;
import com.vrv.cems.service.block.impl.BlockServiceImpl;
import com.vrv.cems.service.block.quartz.QuartzConfig;
import com.vrv.cems.service.block.server.MainServer;
import com.vrv.cems.service.block.utils.PolicyConfigService;
import com.vrv.cems.service.block.utils.SystemConstants;
import com.vrv.cems.service.configure.quartzJob.QuartzConfigure;
import com.vrv.cems.service.register.impl.RegisterServiceImpl;

/** 
 *   <B>说       明</B>:阻断服务(CEMS-SERVICE-BLOCK)主程序
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月22日 下午2:58:31 
 */
public class ServiceMain implements WrapperListener {
	 private static Log logger = LogFactory.getLog(ServiceMain.class);
	QuartzConfigure quartzConfigure = null;
	/**
	 * 键盘控制事件.
	 * 例如：Ctrl + c 停止服务
	 */
    public static ServiceParamBean serviceParamBean;
    private static CommentedProperties prop = new CommentedProperties();
    static {
		PropertyConfigurator.configure(com.vrv.cems.service.base.SystemConstants.PATH_LOG4J_PROPERTIES);
		try {
			
			serviceParamBean= PolicyConfigService.getInstance().getLocalPolicyParam(SystemConstants.PATH_POLICY_XML);
			//修改日志项
			PolicyConfigService.logBusinessProcess(serviceParamBean);
			//修改本地服务systemconstant属性
			//PolicyConfigService.constantsProcess(serviceParamBean);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
/*    @Test
    public void test()
    {
    	try {
			serviceParamBean= PolicyConfigService.getInstance().getLocalPolicyParam(SystemConstants.PATH_POLICY_XML);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//修改日志项
    }*/
    Service service = ServiceFactory.DEFAULT.getService();
    
	@Override
	public void controlEvent(int arg0) {
		// TODO Auto-generated method stub
		WrapperManager.stop(arg0);
	}

	
	  @Override
	  public Integer start(String[] arg0) {
	        try {
	    			logger.info("阻断服务启动中...");
	    			new Thread(new Runnable() {
	    				public void run() {
	    					try{
	    					service.start(new BlockServiceImpl());
	    					}catch (Exception e) {
	    						logger.error("阻断服务启动启动失败", e);
	    					}
	    				}
	    			}).start();
	    		logger.info("阻断服务启动完成...");
	    		
	    		// 2.注册服务
	    		service.register();

	    		// 3.启动服务策略定时器
	    		QuartzConfig.getInstance().init();
	       
	    		return null;
	    		
	     } catch (Exception e) {
	            logger.error("启动阻断服务失败", e);
	        }
	        
			
	        return null;
	    }

	
	
	/**
	 * 启动服务
	 */
/*	@Override
	public Integer start(String[] arg0) {
		try {
	   		Thread thread = new Thread(new Runnable() {
				public void run() {
					new MainServer();
				}
			});
	   		thread.start();
	   		//添加UDP测试 
	   		Thread thread1 = new Thread(new Runnable() {
				public void run() {
					TestUDPService service = new TestUDPService();
					try {
						service.clientUDPTest();
					} catch (Exception e) {
						 
						e.printStackTrace();
					}
				}
			});
	   		thread1.start();
	   		RegisterServiceImpl.registerService();
	   		quartzConfigure = new QuartzConfigure();
			quartzConfigure.init();
		} catch (Exception e) {
		}
		return null;

	}*/
	
	
	

	/**
	 * 停止服务
	 */
	@Override
	public int stop(int arg0) {
		QuartzConfig.getInstance().destroy();
		return 0;
	}
	
	/**
	 * 主入口
	 * @param args
	 */
	public static void main(String[] args) {
		WrapperManager.start(new ServiceMain(), args);
	}

}
 