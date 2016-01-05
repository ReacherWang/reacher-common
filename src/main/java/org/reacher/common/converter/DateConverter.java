/**
 * 
 */
package org.reacher.common.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * @author reacher
 *
 */
public class DateConverter extends StrutsTypeConverter {
	
	private static final Log LOG = LogFactory.getLog(DateConverter.class);
	
	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Override
	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map map, String[] values, Class toClass) {
		if(null == values || 0 > values.length) {
			return null;
		}
		String dateString = values[0];
		if(null == dateString) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(PATTERN);
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			date = null;
			LOG.error(e);
		}
		return date;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String convertToString(Map map, Object object) {
		if(null == object) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(PATTERN);
		return format.format((Date)object);
	}

}
