/**
 * 
 */
package org.reacher.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author reacher
 * 
 */
public final class CollectionUtil {

	private CollectionUtil() {
	}

	public static <T> boolean isEmpty(Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}

	public static <T> boolean isNotEmpty(Collection<T> collection) {
		return !isEmpty(collection);
	}

	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.isEmpty();
	}

	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return !isEmpty(map);
	}
	
	public static <T> boolean contains(Collection<T> collection, T element) {
		if (collection == null) {
			return false;
		}
		return collection.contains(element);
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
		if (isEmpty(collection)) {
			return null;
		}
		int size = collection.size();
		T[] array = (T[]) ArrayUtil.newInstance(collection.get(0).getClass(), size);
		return collection.toArray(array);
	}

	public static <T> boolean addAll(Collection<T> c1, Collection<T> c2) {
		if (c1 == null) {
			return false;
		}
		if (c2 == null) {
			return false;
		}
		return c1.addAll(c2);
	}
	
	@SafeVarargs
	public static <T> boolean add(Collection<T> collection, T... ts) {
		if (collection == null) {
			return false;
		}
		if (ArrayUtil.isEmpty(ts)) {
			return false;
		}
		for (T t : ts) {
			collection.add(t);
		}
		return true;
	}

	public static <T> T getTheOnlyOne(List<T> collection) {
		if (isEmpty(collection)) {
			return null;
		} else if (collection.size() > 1) {
			return null;
		}
		return collection.get(0);
	}

	public static class Difference<T> {

		private List<T> same = null;
		private List<T> added = null;
		private List<T> removed = null;

		public Difference(List<T> same, List<T> added, List<T> removed) {
			this.same = same;
			this.added = added;
			this.removed = removed;
		}

		public List<T> getSame() {
			return same;
		}

		public List<T> getAdded() {
			return added;
		}

		public List<T> getRemoved() {
			return removed;
		}
	}

	public static <T> Difference<T> getDifference(List<T> newOnes, List<T> oldOnes) {
		List<T> same = new ArrayList<T>();
		List<T> added = new ArrayList<T>();
		List<T> removed = new ArrayList<T>();
		if (isEmpty(newOnes)) {
			if (isNotEmpty(oldOnes)) {
				removed.addAll(oldOnes);
			}
		} else if (isEmpty(oldOnes)) {
			if (isNotEmpty(newOnes)) {
				added.addAll(newOnes);
			}
		} else {
			newOnes = new ArrayList<T>(newOnes);
			oldOnes = new ArrayList<T>(oldOnes);
			boolean found;
			for (int i = newOnes.size() - 1; i >= 0; i--) {
				found = false;
				T newOne = newOnes.get(i);
				for (int j = oldOnes.size() - 1; j >= 0; j--) {
					T oldOne = oldOnes.get(j);
					if (newOne.equals(oldOne)) {
						found = true;
						oldOnes.remove(j);
						break;
					}
				}
				if (found) {
					same.add(newOne);
					newOnes.remove(i);
				}
			}
			added.addAll(newOnes);
			removed.addAll(oldOnes);
		}
		return new Difference<T>(same, added, removed);
	}
	
}
