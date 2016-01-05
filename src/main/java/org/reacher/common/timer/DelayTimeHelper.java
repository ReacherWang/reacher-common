/**
 * 
 */
package org.reacher.common.timer;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author reacher
 *
 */
public class DelayTimeHelper {
	
	public static final long DAY = 24 * 60 * 60 * 1000;
	public static final long WEEK = 7 * 24 * 60 * 60 * 1000;
	
	private DelayTimeHelper() {
		
	}
	
	private static class DelayTimeHelperHolder {
		private static DelayTimeHelper delayTimeHelper = new DelayTimeHelper();
	}
	
	public static DelayTimeHelper getInstance() {
		return DelayTimeHelperHolder.delayTimeHelper;
	}
	
	public long getDelayTimeNoRepeat(Calendar currentDate, RTimerTime time){
		long currentTime = currentDate.getTimeInMillis();
		
		currentDate.setTimeInMillis(time.getDay());
		
		this.prepareExecuteTime(currentDate, time);
		
		long executeTime = currentDate.getTimeInMillis();
		
		return executeTime - currentTime;
	}
	
	public long getDelayTimeByDay(Calendar currentDate, RTimerTime time){
		long currentTime = currentDate.getTimeInMillis();
		
		this.prepareExecuteTime(currentDate, time);
		
		long executeTime = currentDate.getTimeInMillis();
		
		long initialDelay = executeTime - currentTime;
		
		return initialDelay > 0 ? initialDelay : initialDelay + DAY;
	}
	
	public long getDelayTimeByWeek(Calendar currentDate, RTimerTime time){
		long currentTime = currentDate.getTimeInMillis();
		
		int currentWeek = currentDate.get(Calendar.WEEK_OF_YEAR);
		
		int currentDay = currentDate.get(Calendar.DAY_OF_WEEK);
		if(currentDay > time.getDay()) {
			currentDate.set(Calendar.WEEK_OF_YEAR, currentWeek + 1);
		}else if(currentDay == time.getDay()) {
			if(this.delayTimeCommon(currentDate, time)){
				currentDate.set(Calendar.WEEK_OF_YEAR, currentWeek + 1);
			}
		}
		currentDate.set(Calendar.DAY_OF_WEEK, time.getDay());
		
		this.prepareExecuteTime(currentDate, time);
		
		return currentDate.getTimeInMillis() - currentTime;
	}
	
	//java月份是从0- 11,月份设置时要减1.
	public long getDelayTimeByMonth(Calendar currentDate, RTimerTime time){
		long currentTime = currentDate.getTimeInMillis();
		int currentMonth = currentDate.get(Calendar.MONTH);
		int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
		
		int maxDayOfMonth = this.getMaxDayOfMonth(currentMonth);
		int executeDay = time.getDay();
		
		if(maxDayOfMonth < executeDay){
			executeDay = maxDayOfMonth;
		}
		if(currentDay > executeDay) {
			currentDate.set(Calendar.MONTH, currentMonth + 1);
			int maxDay = this.getMaxDayOfMonth(currentMonth + 1);
			executeDay = time.getDay() < maxDay ? time.getDay() : maxDay;
		} else if(currentDay == executeDay) {
			if(this.delayTimeCommon(currentDate, time)) {
				currentDate.set(Calendar.MONTH, currentMonth + 1);
				int maxDay = this.getMaxDayOfMonth(currentMonth + 1);
				executeDay = time.getDay() < maxDay ? time.getDay() : maxDay;
			}
		}
		currentDate.set(Calendar.DAY_OF_MONTH, executeDay);
		
		this.prepareExecuteTime(currentDate, time);
		
		return currentDate.getTimeInMillis() - currentTime;
	}
	
	private boolean delayTimeCommon(Calendar currentDate, RTimerTime time){
		
		int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
		int currentMinute = currentDate.get(Calendar.MINUTE);
		int currentSecond = currentDate.get(Calendar.SECOND);
		
		if(currentHour < time.getHour()) {
			return false;
		}
		if(currentHour > time.getHour()) {
			return true;
		}
		if(currentMinute < time.getMinute()) {
			return false;
		}
		if(currentMinute > time.getMinute()) {
			return true;
		}
		if(currentSecond < time.getSecond()) {
			return false;
		}
		return true;
	}
	
	private void prepareExecuteTime(Calendar currentDate, RTimerTime time) {
		currentDate.set(Calendar.HOUR_OF_DAY, time.getHour());
		currentDate.set(Calendar.MINUTE, time.getMinute());
		currentDate.set(Calendar.SECOND, time.getSecond());
	}
	
	public int getMaxDayOfMonth(int month) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.set(Calendar.MONTH, month);
		int day = calendar.getActualMaximum(Calendar.DATE);
		return day;
	}
	
	public boolean isMonth(RTimerTime time) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		int maxDayOfMonth = this.getMaxDayOfMonth(month);
		int executeDay = maxDayOfMonth < time.getDay() ? maxDayOfMonth : time.getDay();
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH) - 1;
		
		return executeDay == currentDay;
	}
}
