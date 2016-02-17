/**
 * 
 */
package org.reacher.base.bean.result;

import org.reacher.base.bean.Bean;

/**
 * @author reacher
 *
 */
public abstract class BaseResult implements Bean {

	private static final long serialVersionUID = 1L;
	
	private int status = 0;
	
	private String message = null;
	
	private boolean success = true;
	
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
	
	public int getStatus() {
		return this.status;
	}

	public void setMessage(String message) {
		this.status = 200;
		this.message = message;
	}
	
	public void setErrorMessage(String message) {
		this.message = message;
		this.success = false;
	}
	
	public void setErrorMessage(int status, String message) {
		this.status = status;
		this.message = message;
		this.success = false;
	}
}
