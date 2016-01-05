package org.reacher.common.sort;

import java.util.List;

/**
 * @author reacher
 *
 */
public abstract class QuickSort<E> {
	
	public List<E> quickSort(List<E> list, Compare<E> compare){
		return quickSort(list, list.size(), compare);
	}
	
	public List<E> quickSort(List<E> list, int length, Compare<E> compare){
		if(length > list.size()){
			length = list.size();
		}
		quickSort(list, 0, length - 1, compare);
		return list;
	}
	
	private int partition(List<E> list, int low, int high, Compare<E> compare){
		E key = list.get(low);
		while(low < high){
			while(low < high && !compare.compare(key, list.get(high))){
				--high;
			}
			list.set(low, list.get(high));
			while(low < high && compare.compare(key, list.get(low))){
				++low;
			}
			list.set(high, list.get(low));
		}
		list.set(low, key);
		return low;
	}
	
	private void quickSort(List<E> list, int low, int high, Compare<E> compare){
		if(low >= high){
			return;
		}
		int midder = partition(list, low, high, compare);
		quickSort(list, low, midder - 1, compare);
		quickSort(list, midder + 1, high, compare);
	}
	
}