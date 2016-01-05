/**
 * 
 */
package org.reacher.common.timer;

import java.util.concurrent.ScheduledFuture;


/**
 * @author reacher
 *
 */
public class RTimer {
	
	private final RTimerPool timerPool = new RTimerPool();
	
	private RTimer() {
		
	}
	
	private static class RTimerHolder {
		private static RTimer timer = new RTimer();
	}
	
	public static RTimer getInstance() {
		return RTimerHolder.timer;
	}
	
	public boolean start(Object key, RTimerThread timerThread) {
		if(null == timerThread) {
			return false;
		}
		ScheduledFuture<?> scheduleFuture = RTimerService.getInstance().execute(timerThread, timerThread.getTime());
		if(null == scheduleFuture) {
			return true;
		}
		return this.timerPool.insert(key, scheduleFuture);
	}
	
	public boolean disable(Object key) {
		if(null == key) {
			return false;
		}
		return this.timerPool.remove(key);
	}

}
