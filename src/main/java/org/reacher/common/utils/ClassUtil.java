/**
 * 
 */
package org.reacher.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author reacher
 * 
 */
public final class ClassUtil {

	private ClassUtil() {
	}

	public static Class<?> getParameterizedTypeClass(Class<?> hostClass, int parameterizedIndex) {
		if (hostClass == null) {
			return null;
		}
		Type[] actualTypeArguments = ((ParameterizedType) hostClass.getGenericSuperclass()).getActualTypeArguments();
		if (ArrayUtil.isEmpty(actualTypeArguments)) {
			return null;
		}
		Type type = actualTypeArguments[parameterizedIndex];
		Class<?> parameterizedTypeClass = (Class<?>) (type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type);
		return parameterizedTypeClass;
	}

	public static Class<?> getParameterizedTypeClass(Field field, int parameterizedIndex) {
		if (field == null) {
			return null;
		}
		ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
		Type[] types = parameterizedType.getActualTypeArguments();
		if (ArrayUtil.isEmpty(types)) {
			return null;
		}
		Type type = types[parameterizedIndex];
		Class<?> parameterizedTypeClass = (Class<?>) (type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type);
		return parameterizedTypeClass;
	}
	
	public static boolean equals(Class<?> class1, Class<?> class2) {
		if (class1 == null || class2 == null) {
			return false;
		}
		return class1.equals(class2);
	}
}
