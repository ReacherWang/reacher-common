/**
 * 
 */
package org.reacher.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.reacher.base.bean.view.BaseView;
import org.reacher.base.service.BaseService;

/**
 * @author reacher
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RService {
	
	public Class<? extends BaseService<? extends BaseView, ? extends BaseView>> service();
	
}
