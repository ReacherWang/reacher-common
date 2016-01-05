/**
 * 
 */
package org.reacher.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.reacher.base.bean.entity.BaseEntity;
import org.reacher.base.mapper.BaseMapper;

/**
 * @author reacher
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RMapper {
	
	public Class<? extends BaseMapper<? extends BaseEntity>> mapper();
	
}
