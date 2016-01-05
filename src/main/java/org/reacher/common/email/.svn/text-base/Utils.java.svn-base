package org.reacher.common.email;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class Utils {

	@SafeVarargs
	public static <T> boolean add(Collection<T> collection, T... ts) {
		if (collection == null) {
			return false;
		}
		if(null == ts || 0 == ts.length) {
			return false;
		}
		for (T t : ts) {
			collection.add(t);
		}
		return true;
	}
	
	@SafeVarargs
	public static <T> List<T> asList(T... array) {
		List<T> list = new ArrayList<T>();
		for (T e : array) {
			list.add(e);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(List<T> collection) {
		if(null == collection) {
			return null;
		}
		if(0 >= collection.size()) {
			return null;
		}
		int size = collection.size();
		T[] array = (T[]) Array.newInstance(collection.get(0).getClass(), size);
		return collection.toArray(array);
		
	}
	
}
