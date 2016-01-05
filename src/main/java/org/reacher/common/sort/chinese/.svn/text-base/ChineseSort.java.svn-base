package org.reacher.common.sort.chinese;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.reacher.common.sort.QuickSort;

/**
 * @author reacher
 *
 */
public abstract class ChineseSort<E> extends QuickSort<ChineseNode<E>>{
	
	private static final char CHARKEY = 'a';
	private static final char INTKEY = '0';
	private static final int CHARCOUNT = 26;
	private static final int INTCOUNT = 10;
	
	public abstract List<E> sort(List<E> list);
	
	public List<E> cSort(List<E> list, List<String> spells){
		ChineseCompare<E> compare = new ChineseCompare<E>();
		List<ChineseNode<E>> nodes = this.createNodeList(list, spells);
		Map<Character, List<ChineseNode<E>>> dict = createDict(nodes);
		return rSort(dict, compare);
	}
	
	private List<E> rSort(Map<Character, List<ChineseNode<E>>> dict, ChineseCompare<E> compare) {
		List<E> temps = new ArrayList<E>();
		char intKey = INTKEY;
		for(int j = 0; j < INTCOUNT; ++j){
			List<ChineseNode<E>> nodes = dict.get(intKey++);
			nodes = this.quickSort(nodes, compare);
			for(int i = 0; i < nodes.size(); ++i){
				E e = nodes.get(i).getChineses();
				temps.add(e);
			}
		}
		char charKey = CHARKEY;
		for(int i = 0; i < CHARCOUNT; ++i){
			List<ChineseNode<E>> nodes = dict.get(charKey++);
			nodes = this.quickSort(nodes, compare);
			for(int j = 0; j < nodes.size(); ++j){
				E e = nodes.get(j).getChineses();
				temps.add(e);
			}
		}
		return temps;
	}
	
	private Map<Character, List<ChineseNode<E>>> createDict(List<ChineseNode<E>> nodes){
		Map<Character, List<ChineseNode<E>>> dict = new HashMap<Character, List<ChineseNode<E>>>();
		char intKey = INTKEY;
		for(int j = 0; j < INTCOUNT; ++j){
			dict.put(intKey++, new ArrayList<ChineseNode<E>>());
		}
		char charKey = CHARKEY;
		for(int i = 0; i < CHARCOUNT; ++i){
			dict.put(charKey++, new ArrayList<ChineseNode<E>>());
		}
		for(ChineseNode<E> node: nodes){
			char ch = node.getSpells().toLowerCase().charAt(0);
			List<ChineseNode<E>> temp = dict.get(ch);
			if(null == temp) {
				continue;
			}
			dict.get(ch).add(node);
		}
		return dict;
	}

	private List<ChineseNode<E>> createNodeList(List<E> list, List<String> spells){
		List<ChineseNode<E>> nodes = new ArrayList<ChineseNode<E>>();
		for(int i = 0; i < spells.size(); ++i){
			E e = list.get(i);
			String spell = spells.get(i);
			ChineseNode<E> node = new ChineseNode<E>(e, spell);
			nodes.add(node);
		}
		return nodes;
	}
}
