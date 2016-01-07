/**
 * 
 */
package org.reacher.base.bean.result;

import org.json.JSONObject;
import org.reacher.base.bean.Bean;

/**
 * @author reacher
 *
 */
public abstract class BaseResult implements Bean {

	private static final long serialVersionUID = 1L;
	
	private boolean success = true;
	
	private String message = null;
	
	public abstract void prepare();
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setErrorMessage(String message) {
		this.message = message;
		this.success = false;
	}
	
	public static String result(int code, String... message) {
		JSONObject result = new JSONObject();
		switch(code) {
		case 200:
			result.accumulate("success", true);
			result.accumulate("status", code);
			result.accumulate("message", "请求已完成!");
			break;
		case 400:
			result.accumulate("success", false);
			result.accumulate("status", code);
			result.accumulate("message", "错误请求!");
			break;
		case 404:
			result.accumulate("success", false);
			result.accumulate("status", code);
			result.accumulate("message", "找不到!");
			break;
		case 500:
			result.accumulate("success", false);
			result.accumulate("status", code);
			result.accumulate("message", "内部错误!");
			break;
		}
		if(null != message && 0 < message.length) {
			result.accumulate("message", message.toString());
		}
		return result.toString();
	}
	
}
