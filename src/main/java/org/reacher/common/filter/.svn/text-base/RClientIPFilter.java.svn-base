/**
 * 
 */
package org.reacher.common.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.reacher.common.system.RSessionKey;

/**
 * @author reacher
 *
 */
public class RClientIPFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(RClientIPFilter.class);
	
	@Override
	public void destroy() {

	}
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpSession session = request.getSession();
		
		String ip = (String)session.getAttribute(RSessionKey.CLIENT_IP);
		if(null == ip || 0 >= ip.length()) {
			ip = this.getIp(request);
			session.setAttribute(RSessionKey.CLIENT_IP, (ip.split(","))[0]);
			logger.info("current user ip is: " + ip);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	public String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
				InetAddress inet = null;// 根据网卡取本机配置的IP
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					logger.error(e);
				}
				ip = inet.getHostAddress();
			}
		}
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
}
