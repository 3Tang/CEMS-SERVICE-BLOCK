package com.vrv.cems.service.block.server;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.vrv.cems.service.base.interfaces.CommonService;
import com.vrv.cems.service.block.bean.DeviceBean;
import com.vrv.cems.service.block.cache.SendedBlockTaskCache;
import com.vrv.cems.service.block.impl.BlockServiceImpl;
import com.vrv.cems.service.block.task.TaskConfig;
import com.vrv.cems.service.block.utils.SerializableUtil;
import com.vrv.cems.service.block.utils.SystemConstants;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class MainServer {
	public MainServer(){
		start();
	}
	static final Logger logger = LogManager.getLogger(MainServer.class);
	@SuppressWarnings("unchecked")
	public void start() {
		//加载本地文件到缓存
		Map<DeviceBean, List<DeviceBean>> map = (Map<DeviceBean, List<DeviceBean>>) SerializableUtil.fileToObject(SystemConstants.SENDED_BLOCK_TASK_CACHE_FILE);
		if(map != null)	SendedBlockTaskCache.putAll(map);
		//开启定时任务
		TaskConfig.config();
		//开启服务
		TProcessor processor = new CommonService.Processor<CommonService.Iface>(new BlockServiceImpl()); 
		asynService(processor);
	}
	
	
	//单线程未使用安全证书的同步服务
	public static void simpleNoSSLSynService(TProcessor processor){
		
		TServerTransport serverTransport;
		try {
			serverTransport = new TServerSocket(SystemConstants.PORT);
			TServer server = new TSimpleServer(new TServer.Args(serverTransport).protocolFactory(new TBinaryProtocol.Factory())
					.processor(processor));
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	    
	}
	//单线程使用安全证书的同步服务
	public static void simpleSSLSynService(TProcessor processor){
		TServerTransport serverTransport;
		try {
			serverTransport = TSSLTransportFactory.getServerSocket(SystemConstants.PORT);
			TServer server = new TSimpleServer(new TServer.Args(serverTransport).protocolFactory(new TCompactProtocol.Factory())
					.transportFactory(new TFramedTransport.Factory()).processor(processor));
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
	//多线程未使用安全证书的同步服务
	public static void multiNoSSLSynService(TProcessor processor){
		TServerTransport serverTransport;
		try {
			serverTransport = new TServerSocket(SystemConstants.PORT);
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).maxWorkerThreads(10).protocolFactory(new TCompactProtocol.Factory())
					.transportFactory(new TFramedTransport.Factory()).processor(processor));
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
	//多线程使用安全证书的同步服务
	public static void multiSSLSynService(TProcessor processor){
		TServerTransport serverTransport;
		try {
			serverTransport = TSSLTransportFactory.getServerSocket(SystemConstants.PORT);
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).maxWorkerThreads(10).protocolFactory(new TCompactProtocol.Factory())
					.transportFactory(new TFramedTransport.Factory()).processor(processor));
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	    
	}
	//异步服务
	public static void asynService(TProcessor processor){
		try {
			TThreadedSelectorServer.Args params = new TThreadedSelectorServer.Args(new TNonblockingServerSocket(SystemConstants.PORT));
			params.processor(processor);
			params.protocolFactory(new TBinaryProtocol.Factory());
			params.selectorThreads(2);
			params.workerThreads(2*2);
			TServer server = new TThreadedSelectorServer(params);
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}
