package com.vrv.cems.service.block.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * <B>说 明</B>:ehcache 单元管理单元类
 * 
 * @author 作 者 名：chenjun<br/>
 *         E-mail ：chenjun@vrvmail.com.cn
 * @version 版 本 号：V1.0.20140725<br/>
 *          创建时间：2014-7-28 下午01:04:01
 */
public class EhCacheUtil {
	public static CacheManager singletonCacheManager;
	public static final Logger loger = Logger.getLogger(EhCacheUtil.class);
	static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	static ReadLock r = lock.readLock();
	static WriteLock w = lock.writeLock();

	static String CacheName = SystemConstants.BLOCK_EHCACHE;
	/*
	 * 初始化CacheManager对象
	 */
	static {
		System.setProperty("net.sf.ehcache.enableShutdownHook","true");
		File file = new File(SystemConstants.PATH_EHCACHE_XML);
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			singletonCacheManager = CacheManager.create(is);
		} catch (Exception e) {
			loger.error("创建cache失败", e);
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 获取CacheManager
	 * 
	 * @return
	 */
	/*
	 * public static synchronized CacheManager getSingletonCacheManager() {
	 * return CacheManager.getCacheManager(CacheName); }
	 */

	/*
	 * 通过cacheName获取Cache对象
	 */
	public static Cache getCacheInstance() {
		Cache cache = singletonCacheManager.getCache(CacheName);
		return cache;
	}

	public static void set(String key, Object value) {
		Cache cache = getCacheInstance();
		w.lock();
		try {
			if (cache == null) {
				cache = getCacheInstance();
			}
			cache.put(new Element(key, value));
		} finally {
			w.unlock();
			cache.flush();
		}
	}

	public static Object get(String key) {
		Cache cache = getCacheInstance();
		r.lock();
		try {
			if (cache != null) {
				if (cache.get(key) != null)
					if (cache.get(key).getObjectValue() != null) {
						return cache.get(key).getObjectValue();
					}
			}
		} finally {
			r.unlock();
		}
		return null;
	}

	public static boolean remove(String key) {
		Cache cache = getCacheInstance();
		w.lock();
		try {
			if (cache != null) {
				return cache.remove(key);
			}
		} finally {
			w.unlock();
		}
		return false;
	}

	public static boolean isExist(String key) {
		Cache cache = getCacheInstance();
		r.lock();
		try {
			Element element = cache.getQuiet(key);
			if (element != null) {
				return true;
			}
		} finally {
			r.unlock();
		}
		return false;
	}

	public static Map<String, String> getAll() {
		Cache cache = getCacheInstance();
		@SuppressWarnings("unchecked")
		List<String> keys = cache.getKeys();
		Map<String, String> heartbeatTimes = new HashMap<String, String>();
		for (String key : keys)
			heartbeatTimes.put(key, (String) cache.get(key).getObjectValue());
		return heartbeatTimes;
	}

	public static void flush() {
		Cache cache = getCacheInstance();

		if (cache != null) {
			cache.flush();
		}

	}

	/*
	 * 卸载 CacheManager,关闭 Cache
	 */
	public static void closeSingletonManager() {
		if (singletonCacheManager != null) {
			singletonCacheManager.shutdown();
		}

	}
}
