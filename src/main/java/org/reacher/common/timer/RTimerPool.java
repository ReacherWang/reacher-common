/**
 * 
 */
package org.reacher.common.timer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author reacher
 *
 */
public final class RTimerPool {
	
	private final ReadWriteLock lock = new ReentrantReadWriteLock(false);
	
	private Map<Object, ScheduledFuture<?>> pool = null;
	
	public boolean init() {
		try{
			this.lock.writeLock().lock();
    		
    		if(null == this.pool){
    			this.pool = new HashMap<Object, ScheduledFuture<?>>();
    		}
		}finally{
			this.lock.writeLock().unlock();
		}
		return true;
	}
	
	public boolean insert (Object key, ScheduledFuture<?> value){
		if(!init()){
			return false;
		}
		try{
			this.lock.writeLock().lock();
			
			ScheduledFuture<?> temp = this.pool.remove(key);
			if(null != temp){
				temp.cancel(true);
			}
			this.pool.put(key, value);
		}finally{
			this.lock.writeLock().unlock();
		}
		return true;
	}
	
	public boolean remove(Object key){
		try{
			this.lock.writeLock().lock();
			
			if(null == this.pool){
				return true;
			}
			ScheduledFuture<?> value = this.pool.remove(key);
			if(null != value){
				value.cancel(true);
			}
		}finally{
			this.lock.writeLock().unlock();
		}
		return true;
	}
	
	public boolean clear() {
		try{
			this.lock.writeLock().lock();
    		
    		if(null != this.pool){
    			this.pool.clear();
    		}
		}finally{
			this.lock.writeLock().unlock();
		}
		return true;
	}
	
}
