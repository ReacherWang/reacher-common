/**
 * 
 */
package org.reacher.base.bean.result;

import java.util.List;

import org.reacher.base.bean.view.BaseView;

/**
 * @author reacher
 *
 */
public final class Result<T extends BaseView> {

	private boolean success;
	private HTTPStatus status;
	private String message;
	private T data;
	private List<T> datas;
	
	private Result(boolean success, HTTPStatus status, String message) {
		this.success = success;
		this.status = status;
		this.message = message;
	}
	
	private Result(boolean success, HTTPStatus status, T data, String message) {
		this(success, status, message);
		this.data = data;
	}
	
	private Result(boolean success, HTTPStatus status, List<T> datas, String message) {
		this(success, status, message);
		this.datas = datas;
	}
	
	public static <D extends BaseView> Result<D> create(HTTPStatus status, String... messages) {
		boolean success = false;
		if(HTTPStatus.Ok == status) {
			success = true;
		}
		String message = "";
		if(null == messages || 0 >= messages.length) {
			message = status.getMessage();
		} else {
			message = messages[0];
		}
		return new Result<D>(success, status, message);
	}
	
	public static <D extends BaseView> Result<D> create(HTTPStatus status, D data, String... messages) {
		boolean success = false;
		if(HTTPStatus.Ok == status) {
			success = true;
		}
		String message = "";
		if(null == messages || 0 >= messages.length) {
			message = status.getMessage();
		} else {
			message = messages[0];
		}
		return new Result<D>(success, status, data, message);
	}
	
	public static <D extends BaseView> Result<D> create(HTTPStatus status, List<D> datas, String... messages) {
		boolean success = false;
		if(HTTPStatus.Ok == status) {
			success = true;
		}
		String message = "";
		if(null == messages || 0 >= messages.length) {
			message = messages[0];
		} else {
			message = messages[0];
		}
		return new Result<D>(success, status, datas, message);
	}
	
	public boolean isSuccess() {
		return success;
	}

	public HTTPStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public T getData() {
		return data;
	}
	
	public List<T> getDatas() {
		return datas;
	}
	
}
