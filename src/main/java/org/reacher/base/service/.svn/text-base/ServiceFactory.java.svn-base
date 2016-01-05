/**
 * 
 */
package org.reacher.base.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reacher.common.annotation.RService;

/**
 * @author reacher
 *
 */
public final class ServiceFactory {
	
	private static final Log LOG = LogFactory.getLog(ServiceFactory.class);
	
	private static final ReentrantLock lock = new ReentrantLock();
	
	@SuppressWarnings("rawtypes")
	private static final Map<Class<? extends IService>, IService> serviceMap = new ConcurrentHashMap<Class<? extends IService>, IService>();
	
	private ServiceFactory() {
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final <S extends IService> S getService(Class<S> clazz) {
		S service = (S) serviceMap.get(clazz);
		if(null == service) {
			try{
				lock.lock();
				service = (S) serviceMap.get(clazz);
				if(null == service) {
					RService serviceAnnotation = clazz.getAnnotation(RService.class);
					service = (S)serviceAnnotation.service().newInstance();
				}
				serviceMap.put(clazz, service);
			} catch(Exception e) {
				LOG.error(e);
			} finally {
				lock.unlock();
			}
		}
		return service;
	}

}
