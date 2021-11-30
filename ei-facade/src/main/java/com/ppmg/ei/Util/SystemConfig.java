package com.ppmg.ei.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SystemConfig {
	private static final Logger log = LogManager.getLogger(SystemConfig.class);
	
	private static SystemConfig instance; // 单例
	
	public Properties props = null;
	
	public static synchronized SystemConfig getInstance() {
		if (instance == null) {
			instance = new SystemConfig();
		}
		return instance;
	}

	public SystemConfig() {
		try {
			props = new Properties();
			InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties");
			props.load(in);
		} catch (IOException e) {
			log.error("加载配置文件失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回int型的属性
	 * 
	 * @param key
	 *
	 * @return int
	 * */
	public int getInt(String key) {
		int val = 0;
		try {
			if (StringUtils.isNotEmpty(props.getProperty(key))) {
				String p = props.getProperty(key);
				val = Integer.parseInt(p);
			}
		} catch (Exception e) {
			log.error("查找 Int 属性:" + key + "失败,将使用默认值", e);
		}
		return val;
	}
	
	/**
	 * 返回long型的属性
	 * 
	 * @param key
	 *
	 * @return int
	 * */
	public long getLong(String key) {
		long val = 0L;
		try {
			if (StringUtils.isNotEmpty(props.getProperty(key))) {
				String p = props.getProperty(key);
				val = Integer.parseInt(p);
			}
		} catch (Exception e) {
			log.error("查找 Long 属性:" + key + "失败,将使用默认值", e);
		}
		return val;
	}
	
	/**
	 * 返回String型的属性
	 * 
	 * @param key 属性的键(名字)
	 *
	 * @return int
	 * */
	public String getString(String key) {
		String val = null;
		if (StringUtils.isNotEmpty(props.getProperty(key))) {
			val = props.getProperty(key);
		} else {
			log.error("查找 String 属性:" + key + "失败");
		}
		return val;
	}
	
}
