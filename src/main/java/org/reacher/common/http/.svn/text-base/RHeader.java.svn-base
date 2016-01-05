/**
 * 
 */
package org.reacher.common.http;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author reacher
 *
 */
public class RHeader {
	
	private JSONArray headers;
	
	public RHeader() {
		this.headers = new JSONArray();
	}
	
	public void addHeader(String key, String value) {
		if(null == this.headers) {
			this.headers = new JSONArray();
		}
		JSONObject header = new JSONObject();
		header.put("key", key);
		header.put("value", value);
		this.headers.put(header);
	}
	
	public JSONArray getHeader() {
		return this.headers;
	}
}
