package org.reacher.common.timer;




/**
 * @author reacher
 *
 */
public abstract class RTimerThread implements Runnable {
	
	private RTimerTime time;
	
	public RTimerThread(RTimerTime time) {
		this.time = time;
	}
	
	public abstract void start();
	
	@Override
	public void run() {
		if(RTimerType.Monthly == this.time.getType()) {
			if(!DelayTimeHelper.getInstance().isMonth(this.time)) {
				return;
			}
		}
		this.start();
	}
	
	public RTimerTime getTime() {
		return this.time;
	}
	
}
