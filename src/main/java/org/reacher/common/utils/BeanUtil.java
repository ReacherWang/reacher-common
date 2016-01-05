/**
 * 
 */
package org.reacher.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author reacher
 * 
 */
public final class BeanUtil {

	private BeanUtil() {
	}
	
	public static <T> T getInstance(Class<T> clazz, Object... args) {
		T instance = null;
		try {
			Constructor<T> constructor = clazz.getDeclaredConstructor(getClasses(args));
			constructor.setAccessible(true);
			instance = constructor.newInstance(args);
		} catch (Exception e) {
		}
		return instance;
	}
	
	public static Class<?>[] getClasses(Object... objects) {
		Class<?>[] classes = new Class<?>[objects == null ? 0 : objects.length];
		for (int i = 0; i < classes.length; i++) {
			classes[i] = objects[i].getClass();
		}
		return classes;
	}
	
	public static boolean isObject(Class<?> clazz) {
		return clazz.equals(Object.class);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getProperties(Object object, Class<T> clazz) {
		if (object == null) {
			return null;
		}
		if (clazz == null) {
			return null;
		}
		List<Field> fields = getFields(object.getClass());
		if (CollectionUtil.isEmpty(fields)) {
			return null;
		}
		List<T> properties = new ArrayList<T>();
		for (Field field : fields) {
			if (clazz.isAssignableFrom(field.getType())) {
				Object value = getFieldValue(object, field);
				if (value == null) {
					continue;
				}
				properties.add((T) value);
			}
		}
		return properties;
	}
	
	public static List<Field> getFields(Class<?> clazz, String... fieldNames) {
		return getFields(clazz, CollectionUtil.asList(fieldNames));
	}
	
	public static List<Field> getFields(Class<?> clazz, List<String> fieldNames) {
		return getFields(clazz, true, fieldNames);
	}
	
	public static List<Field> getFields(Class<?> clazz, boolean includeSuperClass, String... fieldNames) {
		return getFields(clazz, includeSuperClass, CollectionUtil.asList(fieldNames));
	}
	
	public static List<Field> getFields(Class<?> clazz, boolean includeSuperClass, List<String> fieldNames) {
		List<Field> fields = new ArrayList<Field>();
		if (!clazz.equals(Object.class)) {
			if (CollectionUtil.isEmpty(fieldNames)) {
				fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			} else {
				for (String fieldName : fieldNames) {
					Field field = null;
					try {
						field = clazz.getDeclaredField(fieldName);
					} catch (Exception e) {
					}
					if (field != null) {
						fields.add(field);
					}
				}
			}
			if (includeSuperClass) {
				clazz = clazz.getSuperclass();
				fields.addAll(getFields(clazz, includeSuperClass, fieldNames));
			}
		}
		return fields;
	}
	
	public static List<String> getFieldNames(Class<?> clazz, boolean includeSuperClass) {
		List<Field> fields = getFields(clazz, includeSuperClass);
		if (CollectionUtil.isEmpty(fields)) {
			return null;
		}
		List<String> names = new ArrayList<String>();
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers())
					|| Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			names.add(field.getName());
		}
		return names;
	}

	public static List<Field> getSpecialTypeFields(Class<?> clazz, Class<?> typeClass, boolean includeSuperClass) {
		List<Field> fields = new ArrayList<Field>();
		for (Field field : getFields(clazz, includeSuperClass)) {
			if (typeClass.isAssignableFrom(field.getType())) {
				fields.add(field);
			}
		}
		return fields;
	}
	
	public static List<Field> getSpecialAnnotationFields(Class<?> clazz, Class<? extends Annotation> annotationClass, boolean includeSuperClass) {
		List<Field> fields = new ArrayList<Field>();
		for (Field field : getFields(clazz, includeSuperClass)) {
			if (field.isAnnotationPresent(annotationClass)) {
				fields.add(field);
			}
		}
		return fields;
	}
	
	public static Field getField(Class<?> clazz, String name) {
		if (clazz == null) {
			return null;
		}
		Field field = null;
		try {
			field = clazz.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
		} catch (SecurityException e) {
		}
		if (field == null) {
			clazz = clazz.getSuperclass();
			if (!clazz.equals(Object.class)) {
				return getField(clazz, name);
			}
		}
		return field;
	}
	
	public static Object getFieldValue(Object object, String fieldName) {
		if (object == null) {
			return null;
		}
		if (StringUtil.isEmpty(fieldName)) {
			return null;
		}
		Field field = getField(object.getClass(), fieldName);
		if (field == null) {
			return null;
		}
		return getFieldValue(object, field);
	}
	
	public static Object getFieldValue(Object object, Field field) {
		field.setAccessible(true);
		try {
			return field.get(object);
		} catch (Exception e) {
		}
		return null;
	}
	
	public static Map<String, String> getFieldValueMap(Object object) {
		if (object == null) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		List<Field> fields = getFields(object.getClass());
		if (CollectionUtil.isEmpty(fields)) {
			return map;
		}
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers())
					|| Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			Object value = getFieldValue(object, field);
			map.put(field.getName(), value == null ? null : value.toString());
		}
		return map;
	}
	
	public static Map<String, Object> getFieldValueMap(Object object, String... fieldNames) {
		return getFieldValueMap(object, CollectionUtil.asList(fieldNames));
	}
	
	public static Map<String, Object> getFieldValueMap(Object object, List<String> fieldNames) {
		if (object == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Field> fields = getFields(object.getClass(), fieldNames);
		if (CollectionUtil.isEmpty(fields)) {
			return map;
		}
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers())
					|| Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			Object value = getFieldValue(object, field);
			map.put(field.getName(), value);
		}
		return map;
	}
	
	public static List<Map<String, Object>> getFieldValueMap(Collection<?> collection, String... fieldNames) {
		return getFieldValueMap(collection, CollectionUtil.asList(fieldNames));
	}
	
	public static <T> List<Map<String, Object>> getFieldValueMap(Collection<T> collection, List<String> fieldNames) {
		if (CollectionUtil.isEmpty(collection)) {
			return null;
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (T e : collection) {
			Map<String, Object> map = getFieldValueMap(e, fieldNames);
			if (map == null) {
				continue;
			}
			list.add(map);
		}
		return list;
	}
	
	public static void setField(Object object, String name, Object value) {
		Field field = getField(object.getClass(), name);
		setField(object, field, value);
	}
	
	public static void setField(Object object, Field field, Object value) {
		if (object == null) {
			return;
		}
		if (field == null) {
			return;
		}
		if (value == null) {
			return;
		}
		try {
			field.setAccessible(true);
			field.set(object, value);
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
	}
	
	public static void setFieldValue(Object object, Map<String, Object> map) {
		if (object == null) {
			return;
		}
		if (CollectionUtil.isEmpty(map)) {
			return;
		}
		List<Field> fields = getFields(object.getClass());
		if (CollectionUtil.isEmpty(fields)) {
			return;
		}
		for (Field field : fields) {
			if (map.get(field.getName()) == null) {
				continue;
			}
			setField(object, field, convertFieldValue(field, map.get(field.getName())));
		}
	}
	


	public static Object convertFieldValue(Field field, Object value) {
		if (field == null || value == null) {
			return null;
		}
		Class<?> clazz = field.getType();
		if (ClassUtil.equals(clazz, value.getClass())) {
			return value;
		}
		if (clazz == String.class) {
			return value.toString();
		} else if (clazz == int.class || clazz == Integer.class) {
			return Integer.parseInt(value.toString());
		} else if (clazz == long.class || clazz == Long.class) {
			return Long.parseLong(value.toString());
		} else if (clazz == float.class || clazz == Float.class) {
			return Float.parseFloat(value.toString());
		} else if (clazz == double.class || clazz == Double.class) {
			return Double.parseDouble(value.toString());
		} else if (clazz == boolean.class || clazz == Boolean.class) {
			return Boolean.parseBoolean(value.toString());
		}
		return null;
	}
}
