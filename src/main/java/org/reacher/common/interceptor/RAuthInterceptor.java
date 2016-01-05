/**
 * 
 */
package org.reacher.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.reacher.base.action.BaseActionSupport;
import org.reacher.base.action.UserAware;
import org.reacher.base.bean.view.RUserView;
import org.reacher.common.annotation.RAuthentication;
import org.reacher.common.system.RSessionKey;
import org.reacher.common.utils.StringUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author reacher
 *
 */
public class RAuthInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		ActionContext actionContext = invocation.getInvocationContext();
		Method method = action.getClass().getMethod(invocation.getProxy().getMethod());
		RAuthentication authentication = method.getAnnotation(RAuthentication.class);
		if(null == authentication) {
			return invocation.invoke();
		}
		Map<String, Object> session = actionContext.getSession();
		RUserView user = (RUserView)session.get(RSessionKey.LOGIN_USER);
		if(null == user) {
			HttpServletRequest request = ServletActionContext.getRequest();
			String requestedAction = request.getServletPath();
			String parameters = request.getQueryString();
			session.put(RSessionKey.REQUESTED_ACTION, requestedAction + (StringUtil.isEmpty(parameters) ? "" : "?" + parameters));
			return BaseActionSupport.LOGIN;
		}
		if (action instanceof UserAware) {
			((UserAware) action).setUser(user);
		}
		return invocation.invoke();
	}

}
