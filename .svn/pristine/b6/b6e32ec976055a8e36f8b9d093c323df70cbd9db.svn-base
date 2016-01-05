package com.vrv.cems.service.block.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：dongyifei<br/>
 *		    E-mail ：dongyifei@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月15日 下午7:28:51 
 */
public class SerializableUtil {
	public static boolean objectToFile(Object object,String fileName){
		boolean  rs = false;
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(new FileOutputStream(fileName,false));
			os.writeObject(object);
	        rs = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(os != null)	os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
        return rs;
	}
	public static Object fileToObject(String fileName){
		Object rs = null;
		ObjectInputStream is = null;
		try {
			File f = new File( fileName );
			if( (!f.exists()) || f.isDirectory() ){
				return null ;
			}
			System.out.println( f.length() );
			if( f.length() <= 0 ){
				return null ;
			}
			is = new ObjectInputStream(new FileInputStream( f )); 
			rs = is.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null)	is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		return rs;
	}
}
