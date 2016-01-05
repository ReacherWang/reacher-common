/**
 * 
 */
package org.reacher.common.pagination;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author reacher
 *
 */
public final class RDialectFactory {
	
	private static Map<String, RDialect> DIALECTS = new HashMap<String, RDialect>();
	
	public static RDialect getDialect(Class<?> clazz) throws Exception {
		RDialect dialect = DIALECTS.get(clazz.getSimpleName());
		if (dialect == null) {
			dialect = newInstance(clazz);
			if(null != dialect) {
				DIALECTS.put(clazz.getSimpleName(), dialect);
			}
		}
		return dialect;
	}
	
	private static RDialect newInstance(Class<?> clazz) throws Exception {
		if(null == clazz) {
			return null;
		}
		Constructor<?> constructor = clazz.getConstructor();
		constructor.setAccessible(true);
		Object object = constructor.newInstance();
		RDialect dialect = null;
		if(object instanceof RDialect) {
			dialect = (RDialect)object;
		}
		return dialect;
		
	}
}
