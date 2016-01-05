/**
 * 
 */
package org.reacher.common.sort.utils;

import org.reacher.common.sort.Compare;

/**
 * @author reacher
 *
 */
public class IntegerCompare implements Compare<Integer> {

	@Override
	public boolean compare(Integer value1, Integer value2) {
		if(null == value1 || null == value2) {
			return false;
		}
		if(value1 < value2) {
			return false;
		}
		return true;
	}

}
