/**
 * 
 */
package org.reacher.common.timer;

import org.reacher.common.enumeration.RVisualEnum;

/**
 * @author reacher
 *
 */
public enum RTimerType implements RVisualEnum{

	NoRepeat(0, "不重复"), Daily(1, "每日"), Weekly(2, "每周"), Monthly(3, "每月"), CUSTOM(4, "自定义");
	
	private int id;
	private String name;
	
	private RTimerType(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
}
