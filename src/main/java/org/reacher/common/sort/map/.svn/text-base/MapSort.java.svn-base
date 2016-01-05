/**
 * 
 */
package org.reacher.common.sort.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.reacher.common.sort.Compare;
import org.reacher.common.sort.QuickSort;

/**
 * @author reacher
 *
 */
public class MapSort<K, V> extends QuickSort<MapSort<K, V>.MapEntry>{
	
	private Compare<K> key;
	
	private Compare<V> value;
	
	public MapSort(Compare<K> key, Compare<V> value) {
		this.key = key;
		this.value = value;
	}
	
	public Map<K, V> sortByKey(Map<K, V> map) {
		if(null == map || 0 == map.size()) {
			return null;
		}
		return this.sort(map, new MapEntryCompareByKey());
	}
	
	public Map<K, V> sortByValue(Map<K, V> map) {
		if(null == map || 0 == map.size()) {
			return null;
		}
		return this.sort(map, new MapEntryCompareByValue());
	}
	
	private Map<K, V> sort(Map<K, V> map, Compare<MapEntry> compare) {
		Iterator<Map.Entry<K, V>> entries = map.entrySet().iterator();
		List<MapEntry> temps = new ArrayList<MapEntry>();
		while(entries.hasNext()) {
			Map.Entry<K, V> entry = entries.next();
			MapEntry temp = new MapEntry(entry.getKey(), entry.getValue());
			temps.add(temp);
		}
		temps = this.quickSort(temps, compare);
		if(null == temps || 0 == temps.size()) {
			return null;
		}
		Map<K, V> linkedMap = new LinkedHashMap<K, V>();
		for(MapEntry temp: temps) {
			linkedMap.put(temp.getKey(), temp.getValue());
		}
		return linkedMap;
	}
	
	public class MapEntry {
		private K key;
		private V value;
		
		public MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return this.key;
		}
		
		public V getValue() {
			return this.value;
		}
	}
	
	private class MapEntryCompareByKey implements Compare<MapEntry> {

		@Override
		public boolean compare(MapEntry value1, MapEntry value2) {
			return MapSort.this.key.compare(value1.getKey(), value2.getKey());
		}

	}
	
	private class MapEntryCompareByValue implements Compare<MapEntry> {

		@Override
		public boolean compare(MapEntry value1, MapEntry value2) {
			return MapSort.this.value.compare(value1.getValue(), value2.getValue());
		}

	}

}
