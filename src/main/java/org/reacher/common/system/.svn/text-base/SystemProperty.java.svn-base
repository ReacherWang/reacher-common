/**
 * 
 */
package org.reacher.common.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author reacher
 *
 */
public final class SystemProperty {
	
	private static final Log LOG = LogFactory.getLog(SystemProperty.class);
	
	private Properties systemProperties;
	
	private SystemProperty() {
		this.systemProperties = new Properties();
		InputStream inputStream = SystemProperty.class.getResourceAsStream("/system.properties");
		try {
			this.systemProperties.load(inputStream);
		} catch (IOException e) {
			LOG.error(e);
		} finally {
			if(null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
		}
	}
	
	private static class SystemPropertyHolder {
		private static SystemProperty systemProperty = new SystemProperty();
	}
	
	public static SystemProperty instance() {
		return SystemPropertyHolder.systemProperty;
	}
	
	public String getProperty(String key) {
		if (key == null) {
			return null;
		}
		return this.systemProperties.getProperty(key);
	}
	
	public String getProperty(String key, String defaultValue) {
		String value = this.systemProperties.getProperty(key);
		return value == null ? defaultValue : value;
	}
	
}
