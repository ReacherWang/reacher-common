/**
 * 
 */
package org.reacher.common.timer.enums;

/**
 * @author reacher
 *
 */
public enum Weekly {

	SUNDAY(1, "周日"), MONDAY(2, "周一"), TUESDAY(3, "周二"), WEDNESDAY(4, "周三"), THURSDAY(5, "周四"), FRIDAY(6,
			"周五"), SATURDAY(7, "周六");

	private int id;
	private String name;

	private Weekly(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

}
