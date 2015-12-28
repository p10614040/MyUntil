package com.iceblock.myuntil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 字符串处理工具
 * 
 * @author wang.leifeng
 * @date 2011-4-18
 */
public class StringUtil {
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String UTF_8 = "UTF-8";
	public static final String GB2312 = "GB2312";
	public static final String GBK = "GBK";
	/**
	 * 数字和英文字母大小写字符
	 */
	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };

	/**
	 * 将数字格式化为长度为length,前面不足用0补齐的字符串
	 * 
	 * @param value
	 *            值
	 * @param length
	 *            长度
	 * @return
	 */
	public static String numberAddZeroFormat(Number value, int length) {
		return prefixZoreFill(String.valueOf(value), '0', length);
	}

	/**
	 * 获得0-9的随机数
	 * 
	 * @param length
	 *            随机长度
	 * @return String
	 */
	public static String getRandomNumber(int length) {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}

	/**
	 * 获得0-9的随机数 长度默认为10
	 * 
	 * @return String
	 */
	public static String getRandomNumber() {
		return getRandomNumber(10);
	}

	/**
	 * 获得0-9,a-z,A-Z范围的随机数
	 * 
	 * @param length
	 *            随机数长度
	 * @return String
	 */
	public static String getRandomChar(int length) {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		return buffer.toString();
	}

	/**
	 * 获取默认长度为10的随机字符串
	 * 
	 * @return String
	 */
	public static String getRandomChar() {
		return getRandomChar(10);
	}

	public static String enCoding(String fromCharset, String toCharset,
			String input) throws UnsupportedEncodingException {
		if (isEmpty(input)) {
			return "";
		} else {
			return new String(input.getBytes(fromCharset), toCharset);
		}
	}

	/**
	 * 判断字符是否为空
	 * 
	 * @param input
	 *            某字符串
	 * @return 包含则返回true，否则返回false
	 */
	public static boolean isEmpty(String input) {
		return input == null || input.length() == 0;
	}

	/**
	 * 判断字符串数组中是否包含某字符串元素
	 * 
	 * @param substring
	 *            某字符串
	 * @param source
	 *            源字符串数组
	 * @return 包含则返回true，否则返回false
	 */
	public static boolean isInArray(String substring, String[] source) {
		if (source == null || source.length == 0) {
			return false;
		}
		for (String aSource : source) {
			if (aSource.equals(substring)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 填充字符补齐长度
	 * 
	 * @param sourceStr
	 * @param char 填充的字符
	 * @param len
	 * @return
	 */
	public static String prefixZoreFill(String sourceStr, char c, int len) {
		int prefix = len - sourceStr.length();
		if (prefix <= 0)
			return sourceStr;
		for (int i = 0; i < prefix; i++) {
			sourceStr = c + sourceStr;
		}
		return sourceStr;
	}
	
	/**
	 * 深复制一个对象
	 * @param obj
	 * @return
	 */
	public static Object deepCopy(Object obj){
		if(obj==null) return null;
		Object object = null;
		try{
			//将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝   
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();   
	        ObjectOutputStream oos = new ObjectOutputStream(bos);   
	        oos.writeObject(obj);   
	        //将流序列化成对象   
	        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());   
	        ObjectInputStream ois = new ObjectInputStream(bis);   
	        return ois.readObject();   
		}catch (Exception e) {
		}
		return object;
	}
}
