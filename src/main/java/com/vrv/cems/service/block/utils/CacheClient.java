package com.vrv.cems.service.block.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.vrv.cems.service.base.interfaces.CacheService;
import com.vrv.cems.service.base.interfaces.CacheService.Client;


public class CacheClient {
	private static Log logger = LogFactory.getLog(CacheClient.class);
	private static final int clientTimeout = 30000;
	private static TTransport T_TRANSPORT;
	private static CacheService.Client CLIENT;

	private static void initClient(String address, Integer port)
			throws TException {
		T_TRANSPORT = new TFramedTransport(new TSocket(address,
				port.intValue(), 30000));
		TBinaryProtocol protocol = new TBinaryProtocol(T_TRANSPORT);
		CLIENT = new CacheService.Client(protocol);
	}
	public static void TTransportOpen() throws TTransportException {
		T_TRANSPORT.open();
	}

	public static CacheService.Client generateClient() throws TException {
		initClient(SystemConstants.cacheAddress, SystemConstants.cachePort);
		return CLIENT;
	}

	public static void TTransportClose() throws TTransportException {
		T_TRANSPORT.close();
	}
}
