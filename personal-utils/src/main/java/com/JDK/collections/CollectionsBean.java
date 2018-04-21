package com.JDK.collections;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;


@Slf4j
public class CollectionsBean {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		Collections.addAll(list, "007011", "007012", "007013","007012","007012","007014");
		//todo 返回指定 collection 中等于指定对象的元素数
		int frequency = Collections.frequency(list, "007012");
		log.info("【CollectionsBean-->frequency】 入参是 [{}]",frequency);
		// todo 使用指定元素替换指定列表中的所有元素
//		Collections.fill(list,"00");
		log.info("【CollectionsBean-->main】 入参是 [{}]",list);
		String max = Collections.max(list);
		log.info("【CollectionsBean-->max】 入参是 [{}]",max);
		//todo 反转指定列表中元素的顺序
		Collections.reverse(list);
		log.info("【CollectionsBean-->reverse】 入参是 [{}]",list);
		//todo 使用默认随机源对指定列表进行置换
		Collections.shuffle(list);
		log.info("【CollectionsBean-->shuffle】 入参是 [{}]",list);
		//todo  根据元素的自然顺序 对指定列表按升序进行排序
		Collections.sort(list);
		log.info("【CollectionsBean-->sort】 入参是 [{}]",list);
		//todo 在指定列表的指定位置处交换元素。
		Collections.swap(list,0,1);
		log.info("【CollectionsBean-->main】 入参是 [{}]",list);

	}

	@Test
	public void finalCollect(){
		//todo 返回空的列表（不可变的）
		List<Long> objects = Collections.emptyList();
		System.out.println(objects == null);
		System.out.println(objects.size());
		Map<Object, Object> objectObjectMap = Collections.emptyMap();
		Set<Object> objects1 = Collections.emptySet();
		SortedMap<Object, Object> objectObjectSortedMap = Collections.emptySortedMap();

	}

	@Test
	public void contains(){
		String serviceCode = "007011";
		List<String> gjjList = new ArrayList<String>();
		Collections.addAll(gjjList, "007011", "007012", "007013");
		System.out.println(gjjList.contains(serviceCode));
		System.out.println(gjjList.contains("55"));

	}

	@Test
	public void reverse() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(8);
		Collections.reverse(linkedList);
		System.out.println(linkedList);

		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("A");
		arrayList.add("E");
		arrayList.add("Q");
		Collections.reverse(arrayList);
		System.out.println(arrayList);

		new Person();
	}


	@Test
	public void frequency(){
		ArrayList<Integer> list = new ArrayList<>();
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(6);
		list.add(6);
		//frequency
		System.out.println("num of 6 = " + Collections.frequency(list, 6));
		Collections.fill(list,7);
		System.out.println(list);
	}

	@Test
	public void collectionsCalculation(){
		String string [] = new String []{"2"};

		List<Integer> didiUserIdList = new ArrayList<Integer>();
		didiUserIdList.toArray(new Integer []{});
		for (int i = 1; i < 6; i++) {
			didiUserIdList.add(i);
		}
		List<Integer> localUserIdList = new ArrayList<Integer>();
		for (int i = 3; i < 7; i++) {
			localUserIdList.add(i);
		}
		//集合操作
		System.out.println(CollectionUtils.union(didiUserIdList, localUserIdList));//并集
		System.out.println(CollectionUtils.intersection(localUserIdList, didiUserIdList));//交集
		System.out.println(CollectionUtils.disjunction(localUserIdList, didiUserIdList));//补集
		System.out.println(CollectionUtils.subtract(localUserIdList, didiUserIdList));//差集
	}

	@Test
	public void clear(){
		clearList();
		clearMap();
	}

	private void clearMap(){
		Map<String,String> map = null;
//		System.out.println(map.keySet());
		map = new HashMap<String, String>();
		map.put("1", "2");
		map.put("name", "zuoge");
		System.out.println(map);//{1=2, name=zuoge}
		map.clear();
		System.out.println(map);
	}
	private void clearList(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		System.out.println(list);
		list.clear();
		System.out.println(list);
		list.add(5);
		list.add(6);
		System.out.println(list);
	}

	@Test
	public void iterateMap() {
		Map<String,String> map = new HashMap<String, String>();
		//method 1
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		if(iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key+":"+value);
		}
		//method 2
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			System.out.println(map.get(key));
		}
		//method 3
		Object[] array = map.keySet().toArray();
		for (Object obj : array) {
			System.out.println(obj.toString());
			System.err.println(map.get((String)obj));
		}

	}

	@Test
	public void putAllMap() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("1", "a");
		map1.put("2", "b");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("3", "c");
		map2.putAll(map1);
		System.out.println(map2);
	}

	@Test
	public void addAllList(){
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(4);
		list2.add(5);
		list2.add(6);
		list1.addAll(list2);
		System.out.println(list1);
	}
	@Test
	public void collectionsAddAll(){
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		Collections.addAll(list1, 11,5,4,3,2,1);
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		Collections.addAll(list2, 9,8,7,6,5,4,3,2,1);
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(list1);
		list.addAll(list2);
		System.out.println(list);
		HashSet<Integer> hashSet = new HashSet<Integer>(list);
		System.out.println(hashSet);
	}

	@Test
	public void subList(){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 100; i++) {
			Collections.addAll(list, i);
		}
		System.out.println(list);
		System.out.println(list.subList(0, 10));//第一轮
		System.out.println(list.subList(10, 10+10));//第二轮

		for (int i = 0; i < 10; i++) {
			System.out.println(list.subList(i*10, i*10+10));
		}
	}

	@Test
	public void removeAllNum(){
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);

		list2.add(1);
		if(list1.containsAll(list2)){
			list1.removeAll(list2);
			System.out.println(list1);
		}
	}

	@Test
	public void removeAll(){
		List<Person> listAll = new ArrayList<Person>();
		List<Person> list = new ArrayList<Person>();
		Person person1 = new Person(1,"zuo");
		Person person2 = new Person(2,"zhong");
		Person person3 = new Person(3,"you");
		listAll.add(person1);
		listAll.add(person2);
		listAll.add(person3);

		System.out.println(listAll);
		list.add(person3);
		listAll.removeAll(list);
		System.out.println(listAll);
	}

	private <T> List<T> createArrayList(List<T> list,T... t){
		for (T e : t) {
			list.add(e);
		}
		return list;
	}

	@Test
	public void addAll(){
		List<Object> list = new ArrayList<>();
		createArrayList(list,"2","3");
		createArrayList(list,4,5);
		System.out.println(list);
	}

	/**
	 * <p>list --> stream --> 处理完后再转回 list </p>
	 * Created on: 2018-04-19 21:33
	 * @author renzhi.zuo
	 */
	@Test
	public void streamList() {
		List<Integer> list = new ArrayList<>();
		Collections.addAll(list,1,2,3,0,4,0,9,0);
		List<Integer> collect = list.stream()
				//todo 满足过滤条件的保留
				.filter(t -> t != 0)
				//todo 处理完后直接由stream流转回list
				.collect(Collectors.toList());
		System.out.println(collect);
	}
	@Test
	public void streamList2() {
		List<Integer> list = new ArrayList<>();
		list.forEach(t->{
			t.intValue();
		});

		List<Integer> newList = new ArrayList<>();
		Collections.addAll(list,1,2,3,0,4,0,9,0);
		list.stream()
				//todo 满足过滤条件的保留
				.filter(t -> t != 0)
				.collect(Collectors.toList())
				.forEach(t->{
					Collections.addAll(newList,t);
				});
		System.out.println(newList);
	}



}
