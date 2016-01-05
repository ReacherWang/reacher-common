/**
 * 
 */
package org.reacher.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import org.reacher.base.bean.view.RUserView;
import org.reacher.common.annotation.RPermissions;
import org.reacher.common.system.RSessionKey;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author reacher
 *
 */
public class RPermissionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	private static final String NOP_ERMISSION = "noPermission";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		Object action = invocation.getAction();
		Method method = action.getClass().getMethod(invocation.getProxy().getMethod());
		RPermissions permissions = method.getAnnotation(RPermissions.class);
		if(null == permissions) {
			return invocation.invoke();
		}
		if(0 == permissions.permissions().length) {
			return invocation.invoke();
		}
		Map<String, Object> session = actionContext.getSession();
		RUserView user = (RUserView)session.get(RSessionKey.LOGIN_USER);
		if(null == user) {
			return invocation.invoke();
		}
		for(Integer permissionId: permissions.permissions()) {
			if(user.getPermission().hasPermission(permissionId)) {
				continue;
			}
			return NOP_ERMISSION;
		}
		return invocation.invoke();
	}

}
