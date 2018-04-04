package com.JDK.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestHashset {
	
	@Test
	public void add(){
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("a");
		hashSet.add("a");
		hashSet.add("b");
		hashSet.add("b");
		hashSet.add("d");
		hashSet.add("e");
		System.out.println(hashSet);
		System.out.println(hashSet);
		hashSet.add("43");
		hashSet.add("c");
		System.out.println(hashSet);

	}
	@Test
	public void remove(){
//		List<String> list1 = null;
//		System.out.println(list1.size());
		List<String> list = new ArrayList<String> ();
		list.add("我");
		list.add("你");
		List<String> li1 = new ArrayList<String> ();
		li1.add("8");
		li1.add("8");
		li1.add("9");
		li1.add("9");
		li1.add("0");
		li1.add("9");
		
		list.addAll(li1);
		System.out.println(list);
		//方法一：
		Set<String> hs = new HashSet<String>(li1); //此时已经去掉重复的数据保存在hashset中
		System.out.println(hs);
		//方法2:
	}

	/***
	 * 测试改变hashcode,set删除不了原对象
	 */
	@Test
	public void test(){

		Bean bean = new Bean();
		bean.id = 10;
		bean.age = 20;

		HashSet<Bean> beans = new HashSet<>();
		beans.add(bean);
		System.out.println(beans.size());
		beans.remove(bean);
		System.out.println(beans.size());

		beans.add(bean);
		bean.age = 30;
		beans.remove(bean);
		System.out.println(beans.size());
	}

	static class Bean {
		public int id ;
		public int age ;

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Bean bean = (Bean) o;

			if (id != bean.id) return false;
			return age == bean.age;
		}

		@Override
		public int hashCode() {
			int result = id;
			result = 31 * result + age;
			return result;
		}
	}


}
