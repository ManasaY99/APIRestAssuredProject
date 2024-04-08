package com.apiframework.apiframework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static ConfigManager manager;
	public static final Properties prop= new Properties();
	//create a private constructor
	private ConfigManager() throws IOException
	{
		InputStream inputStream= ConfigManager.class.getResourceAsStream("/apiframework/resources/config.properties");
		//load the file
		prop.load(inputStream);	
	}
	
	public static ConfigManager getInstance()
	{
		if(manager==null)
		{
			synchronized(ConfigManager.class)
			{
				try {
					manager=new ConfigManager();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		return manager;
		
		
	}
	
	public String getString(String Key) {
		
		
		return System.getProperty(Key, prop.getProperty(Key));
		
	}

}
