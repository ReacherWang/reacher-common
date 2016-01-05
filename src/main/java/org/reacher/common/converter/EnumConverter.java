/**
 * 
 */
package org.reacher.common.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.reacher.common.enumeration.RVisualEnum;

/**
 * @author reacher
 *
 */
public class EnumConverter extends StrutsTypeConverter {
	
	@Override
	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map map, String[] values, Class toClass) {
		if(null == values || 0 >= values.length) {
			return null;
		}
		if(null == toClass) {
			return null;
		}
		RVisualEnum[] enums = (RVisualEnum[]) toClass.getEnumConstants();
		if(null == enums || 0 > enums.length) {
			return null;
		}
		int id = Integer.parseInt(values[0]);
		Object obj = null;
		for(int i = 0; i < enums.length; ++i) {
			if(id != enums[i].getId()) {
				continue;
			}
			obj = enums[i];
			break;
		}
		return obj;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String convertToString(Map map, Object object) {
		if(null == object) {
			return null;
		}
		if(!(object instanceof RVisualEnum)) {
			return null;
		}
		return String.valueOf(((RVisualEnum)object).getId());
	}

}
