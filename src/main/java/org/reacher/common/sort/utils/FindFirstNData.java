package org.reacher.common.sort.utils;

import java.util.List;

import org.reacher.common.sort.Compare;
import org.reacher.common.sort.HeapSort;

public abstract class FindFirstNData<E> extends HeapSort<E>{

	public boolean findFirstNData(List<E> list, int n, Compare<E> compare) {
		if(!this.heapCreate(list, n, compare)) {
			return false;
		}
		for(int i = n; i < list.size(); ++i) {
			if(!compare.compare(list.get(0), list.get(i))) {
				continue;
			}
			this.swap(list, 0, i);
			if(!this.heapAdjust(list, 0, n, compare)) {
				return false;
			}
		}
		return this.heapSort(list, n, compare);
	}
}