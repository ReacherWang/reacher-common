/**
 * 
 */
package org.reacher.common.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author reacher
 *
 */
public class RURIFilter implements Filter {
	
	private static final Log LOG = LogFactory.getLog(RURIFilter.class);
	
	RURIFilterUri[] filterUris = null;
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		if(null == this.filterUris) {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		for(int i = 0; i < this.filterUris.length; ++i) {
			Pattern pattern = Pattern.compile(RURIFilterRules.getRegex(filterUris[i]));
	    	Matcher matcher = pattern.matcher(request.getRequestURI());
	    	if(matcher.matches()) {
	    		request.getRequestDispatcher("404.jsp").forward(servletRequest, servletResponse);
	    	}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String uri = filterConfig.getInitParameter("uri");
		if(null != uri) {
			try {
				Class<?> clazz = Class.forName(uri);
				this.filterUris = (RURIFilterUri[])clazz.getEnumConstants();
			} catch (ClassNotFoundException e) {
				LOG.error(e);
			}
		}
	}
	
	@Override
	public void destroy() {
		this.filterUris = null;
	}
	
}
