package com.zwd.boot.utils;

import java.io.*;
import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * properties资源文件解析工具
 */
public class PropertiesUtil {

	private static Properties props = null;
	private static URI uri;
	private static String fileName = "/xxx.properties";
	
	private static InputStream in = null;

	static {
		try {
			props = new Properties();
			InputStream fis = PropertiesUtil.class.getResourceAsStream(fileName);
			props.load(fis);
			uri = PropertiesUtil.class.getResource(fileName).toURI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取某个属性
	 */
	public static String getProperty(String key) {
		try {
			props.load(PropertiesUtil.class.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty(key);
	}

	/**
	 * 获取所有属性，返回一个map,不常用 可以试试props.putAll(t)
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getAllProperty() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration enu = props.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = props.getProperty(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 在控制台上打印出所有属性，调试时用。
	 */
	public static void printProperties() {
		props.list(System.out);
	}

	/**
	 * 写入properties信息
	 */
	public static void writeProperties(String key, String value) {
		try {
			OutputStream fos = new FileOutputStream(new File(uri));
			props.setProperty(key, value);
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "『comments』Update key：" + key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取默认key的value
	 * */
	public static String getValue(String key){
		String value = null;
		props = new Properties();
		in = PropertiesUtil.class.getResourceAsStream(fileName);
		try {
			props.load(in);
		} catch (IOException e) {
//			e.printStackTrace();
		}
		value = (String) props.get(key);
		return value;
	}

}
