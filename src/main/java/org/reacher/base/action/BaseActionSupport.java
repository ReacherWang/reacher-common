package org.reacher.base.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.reacher.base.bean.result.BaseResult;
import org.reacher.base.bean.view.RUserView;
import org.reacher.common.system.RSessionKey;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author reacher
 *
 */
@ParentPackage("default")
@Namespace("/")
@Results({@Result(name = BaseActionSupport.JSON, type = BaseActionSupport.JSON, params = {"root", "result", "includeProperties", "success, message, data, data.*"}),
	@Result(name = BaseActionSupport.STREAM, type = BaseActionSupport.STREAM, params = {"contentType", "contentType", "inputName", "inputStream"}),
	@Result(name = BaseActionSupport.FILE, type = BaseActionSupport.STREAM, params = {"contentType", "contentType", "contentDisposition", "attachment;fileName=\"${fileName}\"", "inputName", "inputStream"})})
public abstract class BaseActionSupport extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, SessionAware {

	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private Map<String, Object> session;
	
	protected BaseResult result;
	
	/**
	 * result name and type
	 */
	public static final String JSON = "json";
	public static final String STREAM = "stream";
	public static final String FILE = "file";
	public static final String CHAIN = "chain";
	public static final String REDIRECT = "redirect";
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public BaseResult getResult() {
		return result;
	}

	public void setResult(BaseResult result) {
		this.result = result;
	}
	
	public void setSession(String key, Object value) {
		this.session.put(key, value);
	}
	
	public Object getSession(String key) {
		return this.session.get(key);
	}
	
	public boolean hasPermission(int permissionId) {
		RUserView user = (RUserView)this.getSession(RSessionKey.LOGIN_USER);
		if(null == user) {
			return false;
		}
		return user.getPermission().hasPermission(permissionId);
	}
}
