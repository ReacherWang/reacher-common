/**
 * 
 */
package org.reacher.common.interceptor;

import org.reacher.base.action.BaseActionSupport;
import org.reacher.base.bean.result.BaseResult;
import org.reacher.base.bean.result.JSONResult;
import org.reacher.common.utils.MybatisUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * @author reacher
 *
 */
public class RPreResultInterceptor implements PreResultListener {

	@Override
	public void beforeResult(ActionInvocation invocation, String resultCode) {
		BaseResult result = ((BaseActionSupport) invocation.getAction()).getResult();
		if(result instanceof JSONResult) {
			if (resultCode.equals(BaseActionSupport.JSON)) {
				result.prepare();
			}
		}
		MybatisUtil.sqlcommit((result.isSuccess()));
	}
}
