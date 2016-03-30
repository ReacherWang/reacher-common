/**
 * 
 */
package org.reacher.common.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reacher.base.action.BaseActionSupport;
import org.reacher.base.bean.result.CommonResult;
import org.reacher.base.bean.result.JSONResult;
import org.reacher.common.utils.MybatisUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author reacher
 *
 */
public class RInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	private static final Log LOG = LogFactory.getLog(RInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Pattern pattern = Pattern.compile("^ajax.+$");
		Matcher matcher = pattern.matcher(invocation.getProxy().getMethod());
		Object action = invocation.getAction();
		if(matcher.matches()) {
			((BaseActionSupport) action).setResult(new JSONResult());
		} else {
			((BaseActionSupport) action).setResult(new CommonResult());
		}
		String result = null;
		try {
			invocation.addPreResultListener(new RPreResultInterceptor());
			result = invocation.invoke();
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			MybatisUtil.clearCurrentThreadSqlSession();
		}
		return result;
	}

}
