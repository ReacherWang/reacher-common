/**
 * 
 */
package org.reacher.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author reacher
 *
 */
public class RCharsetFilter implements Filter {
	
	private String charset;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.charset = filterConfig.getInitParameter("charset");
		if(null == this.charset) {
			this.charset = "utf-8";
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requset = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		requset.setCharacterEncoding(this.charset);
		response.setCharacterEncoding(this.charset);
		chain.doFilter(requset, response);
	}

	@Override
	public void destroy() {
		this.charset = null;
	}

}
