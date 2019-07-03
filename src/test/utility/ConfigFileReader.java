/**
 * 
 */
package test.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * Read Configuration for this project
 * @author PD
 *
 */
public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath = System.getProperty("user.dir")+"\\src\\configs\\Configuration.properties";
	
	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			}catch(FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			}
		}
	
	public String getValue(String value){
		    String url = properties.getProperty(value);
			if(url!=null)return url;
			else throw new RuntimeException("URL not found in Configuration.properties file");
		
	}
	
	
	public String getApplicataionUrl() {
		return getValue("url");
//		String url = properties.getProperty("url");
//		if(url!=null)return url;
//		else throw new RuntimeException("URL not found in Configuration.properties file");
	}
	
	
	public String geteBotUrl() {
		return getValue("eBoturl");
//		String eBot = properties.getProperty("eBoturl");
//		if(eBot!=null)return eBot;
//		else throw new RuntimeException("URL not found in Configuration.properties file");
	}
	}


