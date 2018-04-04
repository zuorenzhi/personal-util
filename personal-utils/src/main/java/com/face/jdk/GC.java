package com.face.jdk;

import com.domain.Person;

import java.util.HashSet;

/**
 * see http://bbs.itheima.com/thread-118146-1-1.html
 * <p>Description: [用HashSet集合演示内存泄漏]</p>
 * Created on 2017年4月16日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class GC {

	/**
	 * 用HashSet集合演示内存泄漏！！
	 */
	public static void main(String[] args) {
		
		HashSet<Person> hs = new HashSet<Person>();
		Person p = new Person("张三", 25);
		hs.add(new Person("小杨", 22));
		hs.add(p);
		hs.add(new Person("李四", 23));
		hs.add(new Person("王五", 26));
		
		System.out.printf("pre--hash--%s%n",p.hashCode());
		// 演示：这里是显示添加进去元素的内容
		System.out.println(hs);
		// [name:小杨age:22, name:王五age:26, name:张三age:25, name:李四age:23]
		p.age = 27;
		System.out.printf("suf--hash--%s%n",p.hashCode());
		System.out.println(hs);
		hs.remove(p);
		// 在集合中的张三年龄被改成了27 但是我们结果显示张三是无法被删除的
		System.out.println(hs);
		
		p.age = 25;
		hs.remove(p);
		System.out.println(hs);
		System.out.println(p);
		/*
		 * 原因：因为原先的放入集合中的Person对象的哈希值是通过其属性计算得到的，我们修改了其属性，
		 * 但它的哈希值并不是自动跟着变化，而是已经固定下来了，当我们要删除这个修改过的对象元素时，会发现我们计算的
		 * 哈希值是不会和它固定的哈希值相同的，因此原先的元素就怎么样也删除不了，所以我们说以哈希表为底层数据结构的
		 * 集合是禁止修哪些参与计算哈希值的属性 所谓内存泄漏不就是开辟的内存无法被释放 这个例子演示了内存泄漏 也讲解了以哈希表为数据结构的注意事项
		 */
		
		//补充： hashcode值变了之后，set列表的桶的index就变了，存在第三个桶，去删除其他的桶，
		//最终对3桶没有影响
	}

	static class Person {
		String name;// 为了演示方便就不私有化了
		int age;

		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		// 以哈希表为底层结构的集合是通过复写一下两个方法来保证元素的唯一性
		@Override
		public int hashCode() {
			return name.hashCode() * age;
		}

		@Override
		public boolean equals(Object obj) {
			Person p = (Person) obj;
			return this.name.equals(p.name) && this.age == p.age;
		}


		public String toString() {
			return "name:" + name + "age:" + age;
		}
	}
}

