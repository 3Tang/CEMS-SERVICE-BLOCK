package com.vrv.cems.service.block.utils;

/**
 * <B>说 明</B>:字节处理工具类
 * 
 * @author 作 者 名：zhanghongjie<br/>
 *         E-mail ：zhanghongjie@163.com
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2014年12月2日 上午9:20:08
 */
public class ByteUtil {
	
	/**
	 * �ַ��ֽ�ת��
	 * 
	 * @param ch
	 * @return
	 */
	public static void putChar(byte[] bb, char ch, int index) {
		int temp = (int) ch;
		// byte[] b = new byte[2];
		for (int i = 0; i < 2; i++) {
			bb[index + i] = new Integer(temp & 0xff).byteValue(); // �����λ���������λ
			temp = temp >> 8; // ������8λ
		}
	}

	
	/**
	 * ת��shortΪbyte
	 * 
	 * @param b
	 * @param s
	 *            ��Ҫת����short
	 * @param index
	 */
	public static void putShort(byte b[], short s, int index) {
		b[index + 1] = (byte) (s >> 8);
		b[index + 0] = (byte) (s >> 0);
	}
	/**
	 * ת��intΪbyte����
	 * 
	 * @param bb
	 * @param x
	 * @param index
	 */
	public static void putInt(byte[] bb, int x, int index) {
		bb[index + 3] = (byte) (x >> 24);
		bb[index + 2] = (byte) (x >> 16);
		bb[index + 1] = (byte) (x >> 8);
		bb[index + 0] = (byte) (x >> 0);
	}

	
	/**
	 * floatת��byte
	 * 
	 * @param bb
	 * @param x
	 * @param index
	 */
	public static void putFloat(byte[] bb, float x, int index) {
		// byte[] b = new byte[4];
		int l = Float.floatToIntBits(x);
		for (int i = 0; i < 4; i++) {
			bb[index + i] = new Integer(l).byteValue();
			l = l >> 8;
		}
	}
	

	/**
	 * ת��long��Ϊbyte����
	 * 
	 * @param bb
	 * @param x
	 * @param index
	 */
	public static void putLong(byte[] bb, long x, int index) {
		bb[index + 7] = (byte) (x >> 56);
		bb[index + 6] = (byte) (x >> 48);
		bb[index + 5] = (byte) (x >> 40);
		bb[index + 4] = (byte) (x >> 32);
		bb[index + 3] = (byte) (x >> 24);
		bb[index + 2] = (byte) (x >> 16);
		bb[index + 1] = (byte) (x >> 8);
		bb[index + 0] = (byte) (x >> 0);
	}

	
	/**
	 * ͨ��byte����ȡ��short
	 * 
	 * @param b
	 * @param index
	 *            �ڼ�λ��ʼȡ
	 * @return
	 */
	public static short getShort(byte[] b, int index) {
		return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
	}


	/**
	 * ͨ��byte����ȡ��int
	 * 
	 * @param bb
	 * @param index
	 *            �ڼ�λ��ʼ
	 * @return
	 */
	public static int getInt(byte[] bb, int index) {
		return (int) ((((bb[index + 3] & 0xff) << 24)
				| ((bb[index + 2] & 0xff) << 16)
				| ((bb[index + 1] & 0xff) << 8) | ((bb[index + 0] & 0xff) << 0)));
	}

	/**
	 * ͨ��byte����ȡ��long
	 * 
	 * @param bb
	 * @param index
	 * @return
	 */
	public static long getLong(byte[] bb, int index) {
		return ((((long) bb[index + 7] & 0xff) << 56)
				| (((long) bb[index + 6] & 0xff) << 48)
				| (((long) bb[index + 5] & 0xff) << 40)
				| (((long) bb[index + 4] & 0xff) << 32)
				| (((long) bb[index + 3] & 0xff) << 24)
				| (((long) bb[index + 2] & 0xff) << 16)
				| (((long) bb[index + 1] & 0xff) << 8) | (((long) bb[index + 0] & 0xff) << 0));
	}


	/**
	 * �ֽڵ��ַ�ת��
	 * 
	 * @param b
	 * @return
	 */
	public static char getChar(byte[] b, int index) {
		int s = 0;
		if (b[index + 1] > 0)
			s += b[index + 1];
		else
			s += 256 + b[index + 0];
		s *= 256;
		if (b[index + 0] > 0)
			s += b[index + 1];
		else
			s += 256 + b[index + 0];
		char ch = (char) s;
		return ch;
	}



	/**
	 * ͨ��byte����ȡ��float
	 * 
	 * @param bb
	 * @param index
	 * @return
	 */
	public static float getFloat(byte[] b, int index) {
		int l;
		l = b[index + 0];
		l &= 0xff;
		l |= ((long) b[index + 1] << 8);
		l &= 0xffff;
		l |= ((long) b[index + 2] << 16);
		l &= 0xffffff;
		l |= ((long) b[index + 3] << 24);
		return Float.intBitsToFloat(l);
	}

	/**
	 * ͨ��byte����ȡ��Double
	 * 
	 * @param bb
	 * @param index
	 * @return
	 */
	public static double getDouble(byte[] b) {
		long l;
		l = b[0];
		l &= 0xff;
		l |= ((long) b[1] << 8);
		l &= 0xffff;
		l |= ((long) b[2] << 16);
		l &= 0xffffff;
		l |= ((long) b[3] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[4] << 32);
		l &= 0xffffffffffl;
		l |= ((long) b[5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[6] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) b[7] << 56);
		return Double.longBitsToDouble(l);
	}

	
	public static String CppToJavaString(byte[] data, int index) {

		int i = index;

		for (; i < data.length; i++) {
			if (data[i] == 0)
				break;

		}

		if (i == data.length && data[i - 1] != 0) {

			return null;
		}

		i = i - index;

		byte newbyte[] = new byte[i];

		System.arraycopy(data, index, newbyte, 0, i);

		return new String(newbyte);
	}

	/**
	 * C++�ַ�ת��java �ֽ�
	 * 
	 * @index λ��
	 * 
	 * @param data
	 *            java�ַ���ֽ�
	 * @return �Ѿ�ת���õ�c++�ַ���ֽ�����
	 */
	public static byte[] CppToJava(byte[] data, int index) {

		int i = index;

		for (; i < data.length; i++) {
			if (data[i] == 0)
				break;

		}

		if (i == data.length && data[i - 1] != 0) {

			return null;
		}

		i = i - index;

		byte newbyte[] = new byte[i ];

		System.arraycopy(data, index, newbyte, 0, i);

		return newbyte;
	}

	
	public static byte[] FromByteGetByte(byte[] src, int index) {

		byte desc[] = new byte[src.length - index];

		System.arraycopy(src, index, desc, 0, src.length - index);

		
		return desc;

	}
}