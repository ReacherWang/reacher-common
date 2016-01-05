/**
 * 
 */
package org.reacher.common.sort.utils;

import org.reacher.common.sort.Compare;

/**
 * @author reacher
 *
 */
public class StringCompare implements Compare<String> {

	@Override
	public boolean compare(String value1, String value2) {
		if(null == value1 || null == value2) {
			return false;
		}
		if(0 > value1.compareTo(value2)) {
			return false;
		}
		return true;
	}

}
