/**
 * 
 */
package org.reacher.common.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author reacher
 * 
 */
public final class ArrayUtil {

	private ArrayUtil() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] newInstance(Class<T> componentType, int length) {
		if (componentType == null) {
			return null;
		}
		return (T[]) Array.newInstance(componentType, length);
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getComponentType(T[] array) {
		if (array == null) {
			return null;
		}
		return (Class<T>) array.getClass().getComponentType();
	}

	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length == 0;
	}

	public static <T> boolean isNotEmpty(T[] array) {
		return !isEmpty(array);
	}

	public static <T> boolean equals(T[] array1, T[] array2) {
		if (array1 == array2) {
			return true;
		}
		if (array1 == null || array2 == null) {
			return false;
		}
		if (array1.length != array2.length) {
			return false;
		}
		int length = array1.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.notEquals(array1[i], array2[i])) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean equalsIgnoreOrder(T[] array1, T[] array2) {
		if (array1 == array2) {
			return true;
		}
		if (array1 == null || array2 == null) {
			return false;
		}
		if (array1.length != array2.length) {
			return false;
		}
		List<T> list = CollectionUtil.asList(array1);
		int length = array1.length;
		for (int i = length - 1; i >= 0; i--) {
			if (contains(array2, array1[i])) {
				list.remove(i);
			} else {
				return false;
			}
		}
		return list.size() == 0;
	}

	public static <T> boolean contains(T[] array, T element) {
		if (isEmpty(array)) {
			return false;
		}
		for (T e : array) {
			if (ObjectUtil.equals(e, element)) {
				return true;
			}
		}
		return false;
	}

	@SafeVarargs
	public static <T> T[] combine(T[]... arrays) {
		List<T> list = new ArrayList<T>();
		T[] temp = null;
		for (T[] array : arrays) {
			if (isEmpty(array)) {
				continue;
			}
			if (temp == null) {
				temp = array;
			}
			for (T e : array) {
				list.add(e);
			}
		}
		if (temp == null) {
			return null;
		}
		T[] newArray = newInstance(getComponentType(temp), 0);
		return list.toArray(newArray);
	}

	public static byte[] insert(byte[] array, byte value, int index) {
		if (array == null || index < 0) {
			return null;
		}
		if (index > array.length) {
			index = array.length;
		}
		byte[] newArray = new byte[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, index);
		newArray[index] = value;
		if (index < array.length) {
			System.arraycopy(array, index, newArray, index + 1, array.length - index);
		}
		return newArray;
	}

	public static short[] insert(short[] array, short value, int index) {
		if (array == null || index < 0) {
			return null;
		}
		if (index > array.length) {
			index = array.length;
		}
		short[] newArray = new short[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, index);
		newArray[index] = value;
		if (index < array.length) {
			System.arraycopy(array, index, newArray, index + 1, array.length - index);
		}
		return newArray;
	}

	public static int[] insert(int[] array, int value, int index) {
		if (array == null || index < 0) {
			return null;
		}
		if (index > array.length) {
			index = array.length;
		}
		int[] newArray = new int[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, index);
		newArray[index] = value;
		if (index < array.length) {
			System.arraycopy(array, index, newArray, index + 1, array.length - index);
		}
		return newArray;
	}

	public static long[] insert(long[] array, long value, int index) {
		if (array == null || index < 0) {
			return null;
		}
		if (index > array.length) {
			index = array.length;
		}
		long[] newArray = new long[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, index);
		newArray[index] = value;
		if (index < array.length) {
			System.arraycopy(array, index, newArray, index + 1, array.length - index);
		}
		return newArray;
	}

	public static float[] insert(float[] array, float value, int index) {
		if (array == null || index < 0) {
			return null;
		}
		if (index > array.length) {
			index = array.length;
		}
		float[] newArray = new float[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, index);
		newArray[index] = value;
		if (index < array.length) {
			System.arraycopy(array, index, newArray, index + 1, array.length - index);
		}
		return newArray;
	}

	public static double[] insert(double[] array, double value, int index) {
		if (array == null || index < 0) {
			return null;
		}
		if (index > array.length) {
			index = array.length;
		}
		double[] newArray = new double[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, index);
		newArray[index] = value;
		if (index < array.length) {
			System.arraycopy(array, index, newArray, index + 1, array.length - index);
		}
		return newArray;
	}

	public static char[] insert(char[] array, char value, int index) {
		if (array == null || index < 0) {
			return null;
		}
		if (index > array.length) {
			index = array.length;
		}
		char[] newArray = new char[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, index);
		newArray[index] = value;
		if (index < array.length) {
			System.arraycopy(array, index, newArray, index + 1, array.length - index);
		}
		return newArray;
	}

	public static boolean[] insert(boolean[] array, boolean value, int index) {
		if (array == null || index < 0) {
			return null;
		}
		if (index > array.length) {
			index = array.length;
		}
		boolean[] newArray = new boolean[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, index);
		newArray[index] = value;
		if (index < array.length) {
			System.arraycopy(array, index, newArray, index + 1, array.length - index);
		}
		return newArray;
	}

	public static <T> T[] insert(T[] array, T value, int index) {
		if (array == null || value == null || index < 0) {
			return null;
		}
		if (index > array.length) {
			index = array.length;
		}
		T[] newArray = newInstance(getComponentType(array), array.length + 1);
		System.arraycopy(array, 0, newArray, 0, index);
		newArray[index] = value;
		if (index < array.length) {
			System.arraycopy(array, index, newArray, index + 1, array.length - index);
		}
		return newArray;
	}

	public static byte[] append(byte[] array, byte value) {
		return insert(array, value, array == null ? 0 : array.length);
	}

	public static short[] append(short[] array, short value) {
		return insert(array, value, array == null ? 0 : array.length);
	}

	public static int[] append(int[] array, int value) {
		return insert(array, value, array == null ? 0 : array.length);
	}

	public static long[] append(long[] array, long value) {
		return insert(array, value, array == null ? 0 : array.length);
	}

	public static float[] append(float[] array, float value) {
		return insert(array, value, array == null ? 0 : array.length);
	}

	public static double[] append(double[] array, double value) {
		return insert(array, value, array == null ? 0 : array.length);
	}

	public static char[] append(char[] array, char value) {
		return insert(array, value, array == null ? 0 : array.length);
	}

	public static boolean[] append(boolean[] array, boolean value) {
		return insert(array, value, array == null ? 0 : array.length);
	}

	public static <T> T[] append(T[] array, T value) {
		return insert(array, value, array == null ? 0 : array.length);
	}

	public static int indexOf(byte[] array, byte value) {
		int length = array == null ? 0 : array.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.equals(array[i], value)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(short[] array, short value) {
		int length = array == null ? 0 : array.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.equals(array[i], value)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(int[] array, int value) {
		int length = array == null ? 0 : array.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.equals(array[i], value)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(long[] array, long value) {
		int length = array == null ? 0 : array.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.equals(array[i], value)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(float[] array, float value) {
		int length = array == null ? 0 : array.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.equals(array[i], value)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(double[] array, double value) {
		int length = array == null ? 0 : array.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.equals(array[i], value)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(char[] array, char value) {
		int length = array == null ? 0 : array.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.equals(array[i], value)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(boolean[] array, boolean value) {
		int length = array == null ? 0 : array.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.equals(array[i], value)) {
				return i;
			}
		}
		return -1;
	}

	public static <T> int indexOf(T[] array, T value) {
		int length = array == null ? 0 : array.length;
		for (int i = 0; i < length; i++) {
			if (ObjectUtil.equals(array[i], value)) {
				return i;
			}
		}
		return -1;
	}
	
}
