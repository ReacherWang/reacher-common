/**
 * 
 */
package org.reacher.common.timer;


/**
 * @author reacher
 *
 */
public class RTimerTime {
	
	private RTimerType type;
	
	private int day;
	
	private int time;
	
	public RTimerTime(RTimerType type, int time) {
		this.type = type;
		this.time = time;
	}
	
	public RTimerTime(RTimerType type, int day, int time) {
		this.type = type;
		this.day = day;
		this.time = time;
	}

	public RTimerType getType() {
		return type;
	}

	public int getDay() {
		return day;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getHour() {
		return this.time / (60 * 60);
	}
	
	public int getMinute() {
		int hour = this.time / (60 * 60);
		int time = this.time - (hour * 60 * 60);
		return time / 60;
	}
	
	public int getSecond() {
		int hour = this.time / (60 * 60);
		int time = this.time - (hour * 60 * 60);
		int minute = time / 60;
		return time - minute * 60;
	}

}
