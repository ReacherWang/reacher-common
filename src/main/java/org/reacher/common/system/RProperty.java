/**
 * 
 */
package org.reacher.common.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author reacher
 *
 */
public final class RProperty {
	
	private static Properties properties;
	
	public static void init(String path) throws IOException {
		properties = new Properties();
		InputStream inputStream = RProperty.class.getResourceAsStream(path);
		properties.load(inputStream);
		if(null != inputStream) {
			inputStream.close();
		}
	}
	
	public static String getProperty(String key) {
		if (key == null) {
			return null;
		}
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key, String defaultValue) {
		String value = properties.getProperty(key);
		return value == null ? defaultValue : value;
	}
	
}
