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
public class RParameter {
	
	private JSONArray parameters;
	
	public RParameter() {
		this.parameters = new JSONArray();
	}
	
	public void addParameter(String key, String value) {
		if(null == this.parameters) {
			this.parameters = new JSONArray();
		}
		JSONObject parameter = new JSONObject();
		parameter.put("key", key);
		parameter.put("value", value);
		this.parameters.put(parameter);
	}
	
	public JSONArray getParameter() {
		return this.parameters;
	}
}
