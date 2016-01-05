/**
 * 
 */
package org.reacher.common.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author reacher
 *
 */
public final class RHttpClient {
	
	private RHttpClient() {
		
	}
	
	public static String post(String uri, RHeader header, RParameter parameter) {
		HttpPost http = null;
		try {
			List<NameValuePair> params = RHttpClient.preparePostUri(parameter);
			URL url = new URL(uri);
			http = new HttpPost(url.toString());
			http.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
		} catch (Exception e) {
			return null;
		}
		return RHttpClient.request(http, header);
	}
	
	public static String post(String uri, RHeader header, String data) {
		HttpPost http = null;
		try {
			URL url = new URL(uri);
			http = new HttpPost(url.toString());
			http.setEntity(new StringEntity(data, "utf-8"));
		} catch (Exception e) {
			return null;
		}
		return RHttpClient.request(http, header);
	}
	
	public static String get(String uri, RHeader header, RParameter parameter) {
		HttpGet http = null;
		try {
			uri = RHttpClient.prepareGetUri(uri, parameter);
			URL url = new URL(uri);
			http = new HttpGet(url.toString());
		} catch (Exception e) {
			return null;
		}
		return RHttpClient.request(http, header);
	}
	
	private static String request(HttpUriRequest request, RHeader header) {
		if(!prepareHeader(request, header)) {
			return null;
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			if(null == response) {
				return null;
			}
			 if (HttpURLConnection.HTTP_OK != response.getStatusLine().getStatusCode()) {
				 return null;
			 }
			return EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			return null;
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				return null;
			}
		}
	}
	
	private static boolean prepareHeader(HttpUriRequest request, RHeader header) {
		if(null == request) {
			return false;
		}
		if(null != header) {
			JSONArray temps = header.getHeader();
			for(int i = 0; i < temps.length(); ++i) {
				JSONObject temp = temps.getJSONObject(i);
				request.setHeader(temp.getString("key"), temp.getString("value"));
			}
		}
		return true;
	}
	
	private static List<NameValuePair> preparePostUri(RParameter parameter) throws UnsupportedEncodingException {
		List<NameValuePair> params = null;
		if(null != parameter) {
			params = new ArrayList<NameValuePair>();
			JSONArray temps = parameter.getParameter();
			for(int i = 0; i < temps.length(); ++i) {
				JSONObject temp = temps.getJSONObject(i);
				params.add(new BasicNameValuePair(temp.getString("key"), temp.getString("value")));
			}
		}
		return params;
	}
	
	private static String prepareGetUri(String uri, RParameter parameter) throws UnsupportedEncodingException {
		if(null == parameter) {
			return uri;
		}
		StringBuffer buffer = new StringBuffer(uri);
		JSONArray temps = parameter.getParameter();
		for(int i = 0; i < temps.length(); ++i) {
			JSONObject temp = temps.getJSONObject(i);
			if(0 == i) {
				buffer.append("?");
			} else {
				buffer.append("&");
			}
			buffer.append(temp.getString("key")).append("=").append(URLEncoder.encode(temp.getString("value"), "utf-8"));
		}
		return buffer.toString();
	}

}
