package com.vrv.cems.service.block.bean;

import java.util.Arrays;

import com.sys.common.util.security.HexUtils;
import com.vrv.cems.service.block.utils.ByteConvertUtils;
import com.vrv.cems.service.block.utils.ByteUtil;

 
/**
 * <B>说 明</B>:客户端通讯UDP头bean 对应文档的_P2P_UDP_HEAD
 * 
 * @author 作 者 名：zhanghongjie<br/>
 *         E-mail ：zhanghongjie@163.com
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2014年12月3日 上午10:08:39
 */
public class UDPHead {
	
/*	DWORD dwFlag;			/e_			
	DWORD dwVersion;		//0x01010101
	DWORD dwSize;			//数据包大小
	DWORD dwCrc;			//数据包的crc，数据包中包含自增msgcode，防止篡改发包

	BYTE szSessionId[16];	//GUID
	DWORD dwMsgCode;		//0x00000001自增，防止伪造发包

	DWORD dwMaxCode;		//数据包业务maxCode，0x00010100 0x00010400 0x00011200
	DWORD dwMinCode;		//数据包业务minCode，0x00000004 0x00000001

	WORD wHeadSize;			//包头大小
	WORD wType;				//数据包是zip格式的
	WORD wCount;			//3
	WORD wIndex;			//1,2,3
*/	
	String dwFlag;//edp
	int dwVersion;//
	int dwSize;//数据包大小
	String dwCrc;//数据包的crc
	String szSessionId;//客户端的sessionId
	int dwMsgCode;// 0x00000001
	/*String dwMaxCode;*/// 0x00011200
	String dwMaxCode;
	int dwMinCode;// 0x00000001
	int wHeadSize;//包头大小
	int wType;//0压缩-1不压缩
	int wCount;// 1
	int wIndex;// 1,2,3
	/*byte extended[] = new byte[20];// 预留空间
*/	// 12+32+16+20 =72+12=80
	public final static int HEAD_SIZE = 52;

	
	byte[] mCBuffer;
	
	


	public int getDwVersion() {
		return dwVersion;
	}

	public void setDwVersion(int dwVersion) {
		this.dwVersion = dwVersion;
	}

	public int getDwSize() {
		return dwSize;
	}

	public void setDwSize(int dwSize) {
		this.dwSize = dwSize;
	}



	public String getSzSessionId() {
		return szSessionId;
	}

	public void setSzSessionId(String szSessionId) {
		this.szSessionId = szSessionId;
	}

	public int getDwMsgCode() {
		return dwMsgCode;
	}

	public void setDwMsgCode(int dwMsgCode) {
		this.dwMsgCode = dwMsgCode;
	}



	public int getDwMinCode() {
		return dwMinCode;
	}

	public void setDwMinCode(int dwMinCode) {
		this.dwMinCode = dwMinCode;
	}

	public int getwHeadSize() {
		return wHeadSize;
	}


	public String getDwMaxCode() {
		return dwMaxCode;
	}

	public void setDwMaxCode(String dwMaxCode) {
		this.dwMaxCode = dwMaxCode;
	}

	public int getwType() {
		return wType;
	}

	public void setwType(int wType) {
		this.wType = wType;
	}

	public int getwCount() {
		return wCount;
	}

	public void setwCount(int wCount) {
		this.wCount = wCount;
	}

	public int getwIndex() {
		return wIndex;
	}

	public void setwIndex(int wIndex) {
		this.wIndex = wIndex;
	}

	public void setwHeadSize(int wHeadSize) {
		this.wHeadSize = wHeadSize;
	}

	public String getDwFlag() {
		return dwFlag;
	}

	public void setDwFlag(String dwFlag) {
		this.dwFlag = dwFlag;
	}

	public String getDwCrc() {
		return dwCrc;
	}

	public void setDwCrc(String dwCrc) {
		this.dwCrc = dwCrc;
	}

	
	public byte[] getmCBuffer() {
		return mCBuffer;
	}

	public void setmCBuffer(byte[] mCBuffer) {
		this.mCBuffer = mCBuffer;
	}


	public void setwIndex(short wIndex) {
		this.wIndex = wIndex;
	}

	public void setwHeadSize(short wHeadSize) {
		this.wHeadSize = wHeadSize;
	}

	
	
	@Override
	public String toString() {
		return "UDPHead [dwFlag=" + dwFlag + ", dwVersion=" + dwVersion
				+ ", dwSize=" + dwSize + ", dwCrc=" + dwCrc + ", szSessionId="
				+ szSessionId + ", dwMsgCode=" + dwMsgCode + ", dwMaxCode="
				+ dwMaxCode + ", dwMinCode=" + dwMinCode + ", wHeadSize="
				+ wHeadSize + ", wType=" + wType + ", dwCount=" + wCount
				+ ", dwIndex=" + wIndex + "]";
	}

	/**
	 * 获取C的字节数组
	 * 
	 * @return 字节数组本对象
	 */
	public byte[] getCBuffer() {
		if (mCBuffer == null) {
			mCBuffer = new byte[HEAD_SIZE];
	
			
			//flag
			byte[] flagByte = this.dwFlag.getBytes();
			System.arraycopy(flagByte, 0, mCBuffer, 0, 4);
			//version
			byte versionByte[] = ByteConvertUtils.intToByteArray(this.getDwVersion());
			System.arraycopy(versionByte, 0, mCBuffer, 4, 4);
			//size
			byte sizeByte[] = ByteConvertUtils.intToByteArray(this.getDwSize());
			System.arraycopy(sizeByte, 0, mCBuffer, 8, 4);
			//crc
			byte crcByte[] = ByteConvertUtils.hexStringToByteArray(this.getDwCrc());
//			byte crcByte[] = this.getDwCrc().getBytes();
			System.arraycopy(crcByte, 0, mCBuffer, 12, 4);
			//sessionId
			byte sessionIdByte[] = ByteConvertUtils.hexStringToByteArray(this.getSzSessionId());
			//[102, 48, 51, 97, 97, 97, 54, 102, 97, 51, 51, 99, 52, 100, 57, 49, 98, 55, 55, 50, 99, 101, 53, 50, 99, 48, 49, 100, 101, 97, 52, 55]
			System.arraycopy(sessionIdByte, 0, mCBuffer, 16, 16);
			//msgCode
			byte msgCodeByte[] = ByteConvertUtils.intToByteArray(this.getDwMsgCode());
			System.arraycopy(msgCodeByte, 0, mCBuffer, 32, 4);
			//maxCode
			byte maxCodeByte[] = ByteConvertUtils.hexStringToByteArray(this.getDwMaxCode());
			System.arraycopy(maxCodeByte, 0, mCBuffer, 36, 4);
			//minCode
			byte minCodeByte[] = ByteConvertUtils.intToByteArray(this.getDwMinCode());
			System.arraycopy(minCodeByte, 0, mCBuffer, 40, 4);
			//headSize
			byte headSizeByte[] = ByteConvertUtils.intToByteArray(this.getwHeadSize());
			System.arraycopy(headSizeByte, 0, mCBuffer, 44, 2);
			//type
			byte typeByte[] = ByteConvertUtils.intToByteArray(this.getwType());
			System.arraycopy(typeByte, 0, mCBuffer, 46, 2);
			//count
			byte countByte[] = ByteConvertUtils.intToByteArray(this.getwCount());
			System.arraycopy(countByte, 0, mCBuffer, 48, 2);
			//index
			byte indexByte[] = ByteConvertUtils.intToByteArray(this.getwIndex());
			System.arraycopy(indexByte, 0, mCBuffer, 50, 2);

		}

		return mCBuffer;
	}

	public UDPHead(){		
	}
	
	
	


	

	public UDPHead(String maxCode,String minCode,String szSessionId) {
		this.dwVersion = 1;
		this.dwMaxCode = maxCode;
		this.dwMinCode = Integer.parseInt(minCode, 16);
		this.dwMsgCode = 1;
		this.szSessionId = szSessionId;
		this.dwSize = 0;
		this.wCount = 1;
		this.wIndex = 1;
	}

	static public UDPHead parseCByteArray(byte data[], int index){
		/*	DWORD dwFlag;			/e_			
		DWORD dwVersion;		//0x01010101
		DWORD dwSize;			//数据包大小
		DWORD dwCrc;			//数据包的crc，数据包中包含自增msgcode，防止篡改发包

		BYTE szSessionId[16];	//GUID
		DWORD dwMsgCode;		//0x00000001自增，防止伪造发包

		DWORD dwMaxCode;		//数据包业务maxCode，0x00010100 0x00010400 0x00011200
		DWORD dwMinCode;		//数据包业务minCode，0x00000004 0x00000001

		WORD wHeadSize;			//包头大小
		WORD wType;				//数据包是zip格式的
		WORD wCount;			//3
		WORD wIndex;			//1,2,3
	*/	
		UDPHead a =new UDPHead();
		a.dwFlag=ByteUtil.CppToJavaString(data, index);
		index+=4;
		a.dwVersion=ByteUtil.getInt(data, index);
		index+=4;
		a.dwSize=ByteUtil.getInt(data, index);
		index+=4;
		a.dwCrc=ByteUtil.CppToJavaString(data, index);
		index+=4;
		a.szSessionId=ByteUtil.CppToJavaString(data, index);
		index+=16;
		a.dwMsgCode=ByteUtil.getInt(data, index);
		index+=4;
		a.dwMaxCode=ByteUtil.CppToJavaString(data, index);
		index+=4;
		a.dwMinCode=ByteUtil.getInt(data, index);
		index+=4;
		a.wHeadSize=ByteUtil.getInt(data, index);
		index+=2;
		a.wType=ByteUtil.getInt(data, index);
		index+=2;
		a.wCount=ByteUtil.getInt(data, index);
		index+=2;
		a.wIndex=ByteUtil.getInt(data, index);
		index+=2;
		
		return a ;
	}
	
	/**
	 * 解析udp头
	 */
	public static UDPHead parseHead(byte[] content){
		/*	DWORD dwFlag;			/e_			
		DWORD dwVersion;		//0x01010101
		DWORD dwSize;			//数据包大小
		DWORD dwCrc;			//数据包的crc，数据包中包含自增msgcode，防止篡改发包

		BYTE szSessionId[16];	//GUID
		DWORD dwMsgCode;		//0x00000001自增，防止伪造发包

		DWORD dwMaxCode;		//数据包业务maxCode，0x00010100 0x00010400 0x00011200
		DWORD dwMinCode;		//数据包业务minCode，0x00000004 0x00000001

		WORD wHeadSize;			//包头大小
		WORD wType;				//数据包是zip格式的
		WORD wCount;			//3
		WORD wIndex;			//1,2,3
	*/	
		UDPHead a =new UDPHead();
		byte[] flagByte = Arrays.copyOfRange(content, 0, 4);
		a.dwFlag = ByteConvertUtils.getString(flagByte);
		//version
		byte[] versionByte = Arrays.copyOfRange(content, 4, 8);
		a.dwVersion = ByteConvertUtils.getInt(versionByte);
		//size
		byte[] sizeByte = Arrays.copyOfRange(content, 8, 12);
		a.dwSize = ByteConvertUtils.getInt(sizeByte);
		//crc
		byte[] crcByte = Arrays.copyOfRange(content, 12, 16);
		a.dwCrc = HexUtils.toHexString(crcByte);
		//sessionId
		byte[] sessionIdByte = Arrays.copyOfRange(content, 16, 32);
		a.szSessionId = HexUtils.toHexString(sessionIdByte);
		//msgCode
		byte[] msgCodeByte = Arrays.copyOfRange(content, 32, 36);
		a.dwMsgCode = ByteConvertUtils.getInt(msgCodeByte);
		//maxCode
		byte[] maxCodeByte = Arrays.copyOfRange(content, 36, 40);
		a.dwMaxCode =  HexUtils.toHexString(maxCodeByte);
		//minCode
		byte[] minCodeByte = Arrays.copyOfRange(content, 40, 44);
		a.dwMinCode = ByteConvertUtils.getInt(minCodeByte);
		//headSize
		byte[] headSizeByte = Arrays.copyOfRange(content, 44, 46);
		a.wHeadSize = ByteConvertUtils.getShort(headSizeByte);
		//type
		byte[] typeByte = Arrays.copyOfRange(content, 46, 48);
		a.wType = ByteConvertUtils.getShort(typeByte);
		//count
		byte[] countByte = Arrays.copyOfRange(content, 48, 50);
		a.wCount = ByteConvertUtils.getShort(countByte);
		//index
		byte[] indexByte = Arrays.copyOfRange(content, 50, 52);
		a.wIndex = ByteConvertUtils.getShort(indexByte);
		return a ;
	}
}
