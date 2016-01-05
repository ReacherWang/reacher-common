/**
 * 
 */
package org.reacher.base.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reacher.base.bean.entity.BaseEntity;

/**
 * @author reacher
 *
 */
public final class DaoFactory {
	
	private static final Log LOG = LogFactory.getLog(DaoFactory.class);
	
	private static final ReentrantLock lock = new ReentrantLock();
	
	private static final Map<Class<? extends BaseDao<BaseEntity>>, BaseDao<BaseEntity>> daoMap = new ConcurrentHashMap<Class<? extends BaseDao<BaseEntity>>, BaseDao<BaseEntity>>();

	private DaoFactory() {
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public static final <D extends BaseDao> D getDao(Class<? extends BaseDao> clazz) {
		D dao = (D)daoMap.get(clazz);
		if(null == dao) {
			try {
				lock.lock();
				dao = (D)daoMap.get(clazz);
				if(null == dao) {
					dao = (D)clazz.newInstance();
				}
				daoMap.put((Class<? extends BaseDao<BaseEntity>>)clazz, dao);
			} catch(Exception e) {
				LOG.error(e);
			} finally {
				lock.unlock();
			}
		}
		return dao;
	}
}
