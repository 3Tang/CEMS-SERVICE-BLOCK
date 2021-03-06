package com.vrv.cems.service.block.utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sys.common.util.StringUtils;
import com.vrv.cems.service.base.bean.TimerBean;
import com.vrv.cems.service.block.bean.ServiceParamBean;
import com.vrv.cems.service.block.bean.ServiceParamBean.ParamBean;


public class XmlUtil {
	   static Log logger = LogFactory.getLog(XmlUtil.class);
	protected static final String THRIFT="thriftBean";
	protected static final String LOG="logBean";
	protected static final String SELECTORTHREADS="selectorThreads";
	protected static final String WORKERTHREADS="workerThreads";
	protected static final String LOGLEVEL="logLevel";
	protected static final String LOGPATH="logPath";
	protected static final String PARAMS="params";
	protected static final String PARAM="paramBean";
	protected static final String KEY="key";
	protected static final String VALUE="value";
	protected static final String TIMERS="timers";
	protected static final String TIMERBEAN="timerBean";
	protected static final String NAME ="name";
	protected static final String GROUP= "group";
	protected static final String TRIGGER="trigger";
	protected static final String CYCLE="cycle";

	
	   public static <E> E xmlToObject(InputStream stream,Class<E> clazz ){
	
				SAXReader reader = new SAXReader();
				try {
					Document document = reader.read(stream);
					Element elements = document.getRootElement();
					Field[] field=clazz.getDeclaredFields();
					Object rootObj=clazz.newInstance();
					for(Field fiel:field){
						Element element=elements.element(fiel.getName());
						if(element!=null){
							Class<?> cls =fiel.getType();
							boolean bool =cls.isAssignableFrom(List.class);
							if(bool){
								ParameterizedType pt =(ParameterizedType)fiel.getGenericType();
								cls =(Class<?>)pt.getActualTypeArguments()[0];
							}
							if(bool){
								List<Object> list=new ArrayList<Object>();
								List<Element> eleList=element.elements();
								for (Element element2 : eleList) {
									list.add(valueWrap(cls,element2));
								}
								String methodName="set"+upperFirst(fiel.getName());
								if(containsMethod(clazz,methodName)){
									clazz.getMethod(methodName, List.class).invoke(rootObj, list);
								}
							}else{
								Object obj =valueWrap(cls,element);
								String methodName="set"+upperFirst(fiel.getName());
								if(containsMethod(clazz,methodName)){
									clazz.getMethod(methodName, cls).invoke(rootObj, obj);
								}
							}
						}
					}
					return (E) rootObj;
				} catch (DocumentException e) {
					//logger.error("解析xml异常", e);
				} catch(Exception e){
					//logger.error("对象转换异常", e);
				} 
				return null;
				
			}
	   

	   private static Object valueWrap(final Class<?> cls,final Element element) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
			Field[] fie =cls.getDeclaredFields();
			Object obj =cls.newInstance();
			for(Field fi:fie){
				Element ele = element.element(fi.getName());
				String value="";
				if(ele==null){
					value=element.getText();
					if(StringUtils.isBlank(value)){
						value=element.attributeValue(fi.getName());
					}
				}else{
					value = ele.getText();
					if(StringUtils.isBlank(value)){
						value=ele.attributeValue(fi.getName());
					}
				}
				String methodName="set"+upperFirst(fi.getName());
				if(containsMethod(cls,methodName)){
					cls.getMethod(methodName, String.class).invoke(obj, value);
				}
			}
			return obj;
		}
	   


	/**
	 * 把字符串的第一个字符变为小写
	 * 
	 * @param value
	 *            输入字符串
	 * @return 返回第一个字符是小写的字符串
	 */
	public static String lowerFirst(String value) {
		String str = null;
		if (null != value) {
			if (value.length() == 1) {
				str = value.toLowerCase();
			} else {
				str = value.substring(0, 1).toLowerCase() + value.substring(1);
			}
		}
		return str;
	}

	/**
	 * 方法： 判断一个实体类是否存在某个方法名 <br>
	 * 
	 * 参数说明：
	 * 
	 * @param classz
	 *            :实体类
	 * @param methodName
	 *            ：方法名
	 */
	public static Boolean containsMethod(Class<?> classz, String methodName) {
		Boolean falg = false;
		Method[] methods = classz.getMethods();
		for (Method method : methods) {
			if (equals(methodName, method.getName())) {
				falg = true;
				break;
			}
		}
		return falg;
	}

	
	public static boolean equals(String s1, String s2) {
		if (s1 == s2)
			return true;
		if (null == s1)
			return false;
		return s1.equals(s2);
	}

	
	/**
	 * 把字符串的第一个字符变为大写
	 * 
	 * @param s
	 *            输入字符串
	 * @return 返回第一个字符是大写的字符串
	 */
	public static String upperFirst(String s) {
		String str = null;
		if (null != s) {
			if (s.length() == 1) {
				str = s.toUpperCase();
			} else {
				str = s.substring(0, 1).toUpperCase() + s.substring(1);
			}
		}
		return str;
	}
	
	public static  String Object2Xml(ServiceParamBean service)
	{
		if(service==null) return"" ;
		Document document = DocumentHelper.createDocument(); 
		try {
			Element rootElement =document.addElement("root");
			ServiceParamBean.ThriftBean thrift =service.getThriftBean();
			if(thrift!=null){
				String seletor =thrift.getSelectorThreads();
				String worker= thrift.getWorkerThreads();
				rootElement.addEntity(THRIFT,"<thriftBean selectorThreads=\""+seletor+"\" workerThreads=\""+worker+"\"/>");
			}
			ServiceParamBean.LogBean log =service.getLogBean();
			if(log!=null){
				String logLevel=log.getLogLevel();
				String logPath=log.getLogPath();
				rootElement.addEntity(LOG,"<logBean logLevel=\""+logLevel+"\" logPath=\""+logPath+"\" />");
			}
			List<ParamBean> params=service.getParams();
			if(params!=null){
				Element listP =rootElement.addElement(PARAMS);
				for (ParamBean paramBean : params) {
					listP.addEntity(PARAM,"<paramBean key=\""+paramBean.getKey()+"\" value=\""+paramBean.getValue()+"\"/>");
				}
			}
			List<TimerBean> timers =service.getTimers();
			if(timers!=null){
				Element listT= rootElement.addElement(TIMERS);
				for (TimerBean trimerBean : timers) {
					Element ele =listT.addElement(TIMERBEAN);
					ele.addElement(NAME).setText(trimerBean.getName());
					ele.addElement(GROUP).setText(trimerBean.getGroup());
					ele.addElement(TRIGGER).setText(trimerBean.getTrigger());
					ele.addElement(CYCLE).setText(trimerBean.getCycle());
				}
			}
			return document.asXML();
		} catch (Exception e) {
			logger.error("转换出错", e);
		}
		return "";

		
	}
}
