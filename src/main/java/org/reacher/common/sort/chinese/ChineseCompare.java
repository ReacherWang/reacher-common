/**
 * 
 */
package org.reacher.common.sort.chinese;

import org.reacher.common.sort.Compare;

/**
 * @author reacher
 *
 */
public class ChineseCompare<E> implements Compare<ChineseNode<E>> {
	
	@Override
	public boolean compare(ChineseNode<E> value1, ChineseNode<E> value2) {
		String valueTemp1[] = value1.getSpells().split(" ");
		String valueTemp2[] = value2.getSpells().split(" ");
		int i = 0;
		for(i = 0; i < valueTemp1.length && i < valueTemp2.length; ++i){
			if(0 < valueTemp1[i].compareTo(valueTemp2[i])){
				return true;
			}
			if(0 > valueTemp1[i].compareTo(valueTemp2[i])){
				return false;
			}
		}
		if(i < valueTemp1.length){
			return true;
		}
		return false;
	}

}
