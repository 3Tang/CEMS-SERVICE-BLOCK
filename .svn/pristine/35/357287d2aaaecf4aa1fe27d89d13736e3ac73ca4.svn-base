package com.vrv.cems.service.block.utils; 

import java.io.File; 



import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node; 
import org.dom4j.io.SAXReader;

/** 
 *   <B>说       明</B>:解析策略文件模板类
 *
 * @author  作  者  名：daiyijun<br/>
 *		    E-mail ：daiyijun@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月14日 下午2:28:12 
 */
public class PolicyXMLParse{
	protected static Logger LOGGER = Logger.getLogger(PolicyXMLParse.class); 
	public PolicyXMLParse() {  
	}    
	public static String getPolicyFilePath(){ 
		return PolicyXMLParse.class.getClassLoader().getResource( "policy.xml" ).getPath();
	} 
	/**
	 * 获取根文档
	 * @return
	 * @throws DocumentException
	 */
	private Document getDocument() throws DocumentException{
		SAXReader saxReader = new SAXReader();
		System.out.println( getPolicyFilePath() );
		return saxReader.read( new File( getPolicyFilePath() ) );
	}
	/**
	 * 将XML文件转化为相关实体Bean
	 * 框架方法，不负责具体转化业务实现
	 * @return
	 */
/*	public void changeSystemConstantsByXML(){
		Document document = null ;
		try {
			document = getDocument(); 
			Element rootElement = document.getRootElement();
			Node selectNode = rootElement.selectSingleNode("/root/params/paramBean[@key='isBlock']/@value");
			SystemConstants.isBlock = StringUtils.trim( selectNode.getText() );
			selectNode = rootElement.selectSingleNode("/root/params/paramBean[@key='blockRuleType']/@value");
			SystemConstants.blockRuleType = StringUtils.trim( selectNode.getText() );
			selectNode = rootElement.selectSingleNode("/root/params/paramBean[@key='blockIntervalTime']/@value");
			SystemConstants.blockIntervalTime = StringUtils.trim( selectNode.getText() );
			selectNode = rootElement.selectSingleNode("/root/params/paramBean[@key='blockNumber']/@value");
			SystemConstants.blockNumber = StringUtils.trim( selectNode.getText() );
			selectNode = rootElement.selectSingleNode("/root/params/paramBean[@key='timeoutUpd']/@value");
			SystemConstants.timeoutUpd = StringUtils.trim( selectNode.getText() );
			selectNode = rootElement.selectSingleNode("/root/params/paramBean[@key='blockHostNum']/@value");
			SystemConstants.blockHostNum = StringUtils.trim( selectNode.getText() ); 
		} catch (Exception e) {
				LOGGER.error( e );
				throw new RuntimeException( e );
		} finally{
			if( document != null ){
				document.clearContent(); 
			}
		}
	}*/  
	
}
 