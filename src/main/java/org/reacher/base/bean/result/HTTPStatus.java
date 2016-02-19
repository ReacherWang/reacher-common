/**
 * 
 */
package org.reacher.base.bean.result;

/**
 * @author reacher
 *
 */
public enum HTTPStatus {

	Ok(200, "请求已成功"), 
	BadRequest(400, "请求参数有误"), 
	NotFound(404, "请求失败，请求所希望得到的资源未被在服务器上发现。"), 
	ServerError(500, "服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。");
	
	private int code;
	private String message;
	
	private HTTPStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public static HTTPStatus findByCode(int code) {
		for(HTTPStatus status: HTTPStatus.values()) {
			if(status.getCode() == code) {
				return status;
			}
		}
		return null;
	}
}
