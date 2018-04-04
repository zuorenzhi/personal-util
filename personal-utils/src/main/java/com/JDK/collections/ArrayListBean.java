package com.JDK.collections;

import org.junit.Test;

import java.util.*;


public class ArrayListBean {

	@Test
	public void frequency() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("b");
		list.add("c");
		list.add("a");
		list.add("a");
		list.add("a");

		System.out.println("\n例子1 - 计算'a'出现的次数");
		System.out.println("a : " + Collections.frequency(list, "a"));

		System.out.println("\n例子2 - 计算所有对象出现的次数");
		Set uniqueSet = new HashSet(list);//set 去重
		for (Object temp : uniqueSet) {
			System.out.println(temp + ": " + Collections.frequency(list, temp));
		}

		System.out.println("\n例子3 -用Map来计算对象出现的次数");
		Map map = new HashMap();

		for (Object temp : list) {
			Integer count = (Integer)map.get(temp);
			map.put(temp, (count == null) ? 1 : count + 1);
		}
		printMap(map);

		System.out.println("\nMap排序-以key排序");
		Map treeMap = new TreeMap(map);
		printMap(treeMap);


	}

	public void printMap(Map map) {
		for (Object entry : map.entrySet()) {
			System.out.println("Key-value : " + ((Map.Entry)entry).getKey() + "- "
					+ ((Map.Entry)entry).getValue());
		}
	}

	@Test
	public void toArray(){
		List<String> list = new ArrayList<>(2);
		list.add("a");
		list.add("b");
		System.out.println(list);
		String[] array = new String [list.size()];
		array = list.toArray(array);
		System.out.println(array);
		System.out.println(Arrays.toString(array));
	}

	@Test
	public void toArrayInteger(){
		List<Integer> list = new ArrayList<>(2);
		list.add(8);
		list.add(99);
		System.out.println(list);
		Integer[] array = new Integer [list.size()];
		array = list.toArray(array);
		System.out.println(array);
		System.out.println(Arrays.toString(array));
	}
}
