/**
 * 
 */
package org.reacher.common.converter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reacher.base.bean.Bean;
import org.reacher.base.bean.entity.BaseEntity;
import org.reacher.base.bean.view.BaseView;
import org.reacher.common.annotation.RConversion;
import org.reacher.common.annotation.RConversionRule;
import org.reacher.common.utils.BeanUtil;
import org.reacher.common.utils.ClassUtil;
import org.reacher.common.utils.CollectionUtil;

/**
 * @author reacher
 * 
 */
public class BeanConverter {
	
	private BeanConverter() {
		
	}
	
	private static class BeanConverterHolder {
		private static final BeanConverter beanConverter = new BeanConverter();
	}
	
	public static BeanConverter getInstance() {
		return BeanConverterHolder.beanConverter;
	}

	private static final Log LOG = LogFactory.getLog(BeanConverter.class);

	@SuppressWarnings("unchecked")
	public <V extends BaseView, E extends BaseEntity> E convertToEntity(V view) {
		if (view == null) {
			return null;
		}
		Class<V> viewClass = (Class<V>) view.getClass();
		RConversion conversion = viewClass.getAnnotation(RConversion.class);
		if (conversion == null) {
			return null;
		}
		Class<E> toClass = (Class<E>) conversion.to();
		E entity = BeanUtil.getInstance(toClass);
		List<Field> beanFields = BeanUtil.getFields(viewClass);
		for (Field field : beanFields) {
			if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			RConversionRule conversionRule = field.getAnnotation(RConversionRule.class);
			String fieldName = field.getName();
			if (conversionRule != null) {
				fieldName = conversionRule.toFieldName();
			}
			Object value = null;
			try {
				field.setAccessible(true);
				value = field.get(view);
				if (value instanceof Bean) {
					value = convertToEntity((V) value);
				} else if (value instanceof List) {
					value = convertToEntities((List<V>) value);
				}
			} catch (Exception e) {
				LOG.error(e);
			}
			if (value != null) {
				BeanUtil.setField(entity, fieldName, value);
			}
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public <V extends BaseView, E extends BaseEntity> List<E> convertToEntities(List<V> views) {
		List<E> entities = new ArrayList<E>();
		if (CollectionUtil.isNotEmpty(views)) {
			for (V view : views) {
				entities.add((E)convertToEntity(view));
			}
		}
		return entities;
	}

	@SuppressWarnings("unchecked")
	public <V extends BaseView, E extends BaseEntity> V convertFromEntity(E entity, Class<V> viewClass) {
		if (entity == null || viewClass == null) {
			return null;
		}
		V view = BeanUtil.getInstance(viewClass);
		Class<E> entityClass = (Class<E>) entity.getClass();
		List<Field> fields = BeanUtil.getFields(viewClass);
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			RConversionRule conversionRule = field.getAnnotation(RConversionRule.class);
			String fieldName = field.getName();
			if (conversionRule != null) {
				fieldName = conversionRule.toFieldName();
			}
			Field entityField = BeanUtil.getField(entityClass, fieldName);
			if (entityField == null) {
				continue;
			}
			Object value = null;
			try {
				entityField.setAccessible(true);
				value = entityField.get(entity);
				if (value == null) {
					continue;
				}
				if (value instanceof BaseEntity) {
					value = convertFromEntity((BaseEntity) value, (Class<? extends V>) field.getType());
				} else if (value instanceof List) {
					value = convertFromEntities((List<BaseEntity>) value, (Class<V>) ClassUtil.getParameterizedTypeClass(field, 0));
				} else {
					value = BeanUtil.convertFieldValue(field, value);
				}
			} catch (Exception e) {
				LOG.error(e);
			}
			if (value != null) {
				BeanUtil.setField(view, field.getName(), value);
			}
		}
		return view;
	}

	public <V extends BaseView, E extends BaseEntity> List<V> convertFromEntities(List<E> entities, Class<V> viewClass) {
		List<V> views = new ArrayList<V>();
		if (CollectionUtil.isNotEmpty(entities)) {
			for (E entity : entities) {
				views.add(convertFromEntity(entity, viewClass));
			}
		}
		return views;
	}

}
