/**
 * 
 */
package org.reacher.common.timer;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author reacher
 *
 */
public class RTimerService {
	
	private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
	
	private RTimerService() {
		
	}
	
	private static class RTimerServiceHolder {
		private static RTimerService timerService = new RTimerService();
	}
	
	public static RTimerService getInstance() {
		return RTimerServiceHolder.timerService;
	}
	
	public ScheduledFuture<?> execute(Runnable command, RTimerTime time){
		ScheduledFuture<?> scheduleFuture = null;
    	Calendar calendar = Calendar.getInstance();
		
    	if (RTimerType.NoRepeat == time.getType()) {
    		scheduleFuture = executeNoRepeat(command, calendar, time);
    	} else if (RTimerType.Daily == time.getType()) {
    		scheduleFuture = executeDaily(command, calendar, time); 
    	} else if (RTimerType.Weekly == time.getType()) {
    		scheduleFuture = executeWeekly(command, calendar, time);
    	} else if (RTimerType.Monthly == time.getType()) {
    		scheduleFuture = executeMonthly(command, calendar, time);
    	} else if (RTimerType.CUSTOM == time.getType()) {
    		scheduleFuture = executeCustom(command, time);
    	}
    	return scheduleFuture;
    }
    
    private ScheduledFuture<?> executeNoRepeat(Runnable command, Calendar calendar, RTimerTime time){
    	long delayTime  = DelayTimeHelper.getInstance().getDelayTimeNoRepeat(calendar, time);
    	if(0 > delayTime){
    		return null;
    	}
    	ScheduledFuture<?> scheduleFuture = scheduler.schedule(command, delayTime, TimeUnit.MILLISECONDS);
    	return scheduleFuture;
    }
    
    private ScheduledFuture<?> executeDaily(Runnable command, Calendar calendar, RTimerTime time){
        long delayTime  = DelayTimeHelper.getInstance().getDelayTimeByDay(calendar, time);
        if(0 > delayTime){
    		return null;
    	}
        ScheduledFuture<?> scheduleFuture = scheduler.scheduleAtFixedRate(command, delayTime, DelayTimeHelper.DAY, TimeUnit.MILLISECONDS); 
        return scheduleFuture;
    }
    
    private ScheduledFuture<?> executeWeekly(Runnable command, Calendar calendar, RTimerTime time){
    	long delayTime  = DelayTimeHelper.getInstance().getDelayTimeByWeek(calendar, time);
    	if(0 > delayTime){
    		return null;
    	}
        ScheduledFuture<?> scheduleFuture = scheduler.scheduleAtFixedRate(command, delayTime, DelayTimeHelper.WEEK, TimeUnit.MILLISECONDS); 
        return scheduleFuture;
    }
    
    private ScheduledFuture<?> executeMonthly(Runnable command, Calendar calendar, RTimerTime time){
    	long delayTime  = DelayTimeHelper.getInstance().getDelayTimeByMonth(calendar, time);
    	if(0 > delayTime){
    		return null;
    	}
    	ScheduledFuture<?> scheduleFuture = scheduler.scheduleAtFixedRate(command, delayTime, DelayTimeHelper.DAY, TimeUnit.MILLISECONDS); 
        return scheduleFuture;
    }
    
    private ScheduledFuture<?> executeCustom(Runnable command, RTimerTime time){
    	long delayTime  = time.getTime();
    	if(0 > delayTime){
    		return null;
    	}
    	ScheduledFuture<?> scheduleFuture = scheduler.scheduleAtFixedRate(command, delayTime, delayTime, TimeUnit.MILLISECONDS); 
        return scheduleFuture;
    }
    
}
