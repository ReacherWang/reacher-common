package org.reacher.common.sort;

import java.util.List;

/**
 * @author reacher
 *
 */
public abstract class HeapSort<E> {
	
	public boolean heapSort(List<E> list, Compare<E> compare){//排序
		return heapSort(list, list.size(), compare);
	}
	
	public boolean heapSort(List<E> list, int n, Compare<E> compare){
		if(null == list || 0 == list.size()){
			return false;
		}
		if(!heapCreate(list, n, compare)){
			return false;
		}
		for(int i = n; i > 0; --i){
			swap(list, 0, i - 1);
			heapAdjust(list, 0, i - 1, compare);
		}
		return true;
	}
	
	public boolean heapCreate(List<E> list, int length, Compare<E> compare){ //创建小根堆
		if(null == list || 0 == list.size()){
			return false;
		}
		for(int i = (length / 2 - 1); i >= 0; --i){
			if(!heapAdjust(list, i, length, compare)){
				return false;
			}
		}
		return true;
	}
	
	public boolean heapAdjust(List<E> list, int middle, int length, Compare<E> compare){//调整堆，使其满足小根堆的条件
		if(null == list || 0 == list.size()){
			return false;
		}
		E temp = list.get(middle);
		for(int i = (2 * middle + 1); i < length; i *= 2){
			if(i < (length - 1) && !compare.compare(list.get(i), list.get(i + 1))){
				++i;
			}
			if(compare.compare(temp,list.get(i))){
				break;
			}
			list.set(middle, list.get(i));
			middle = i;
		}
		list.set(middle, temp);
		return true;
	}
	
	public void swap(List<E> list, int i, int j){//数据交换
		E temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
}
