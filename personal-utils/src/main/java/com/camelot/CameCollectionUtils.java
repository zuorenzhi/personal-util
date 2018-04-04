package com.camelot;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.CollationKey;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

/**
 * 
 * <p>Description: [ 集合工具类]</p>
 * Created on 2016-3-14
 * @author  <a href="mailto: wuchaoqiang@camelotchina.com">武超强</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class CameCollectionUtils {

	public final static String SORT_DESC = "desc";
	public final static String SORT_ASC = "asc";

	/**
	 * <p>Discription:[List集合排序类(可按中文排序)]</p>
	 * Created on 2016-3-14
	 * @param list 目标集合
	 * @param property 排序字段名
	 * @param sortType 正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
	 * @param isCN 是否按中文排序
	 * @author:武超强
	 */
	public static <T> void sortMethodList(List<T> list, final String property, final String sortType, final boolean isCN) {
		Collections.sort(list, new Comparator<T>() {
			public int compare(T a, T b) {
				int ret = 0;
				Field field = ReflectionUtils.findField(a.getClass(), property);
				String getterMethodName = "get" + StringUtils.capitalize(property);
				Method method = ReflectionUtils.findMethod(a.getClass(), getterMethodName);
				Object value_a = ReflectionUtils.invokeMethod(method, a);
				Object value_b = ReflectionUtils.invokeMethod(method, b);
				if (field.getType() == String.class) {
					if (isCN) {
						Collator collator = Collator.getInstance(java.util.Locale.CHINA);
						CollationKey key1 = collator.getCollationKey(value_a.toString());
						CollationKey key2 = collator.getCollationKey(value_b.toString());
						if (sortType != null && sortType.equals(SORT_DESC))
							ret = key2.compareTo(key1);
						else
							ret = key1.compareTo(key2);
					} else {
						if (sortType != null && sortType.equals(SORT_DESC))
							ret = value_b.toString().compareTo(value_a.toString());
						else
							ret = value_a.toString().compareTo(value_b.toString());
					}
				} else if (field.getType() == Integer.class || field.getType() == Long.class || field.getType() == BigDecimal.class) {
					BigDecimal decA = new BigDecimal(value_a.toString());
					BigDecimal decB = new BigDecimal(value_b.toString());
					if (sortType != null && sortType.equals(SORT_DESC))
						ret = decB.compareTo(decA);
					else
						ret = decA.compareTo(decB);
				} else if (field.getType() == Date.class) {
					if (sortType != null && sortType.equals(SORT_DESC))
						ret = ((Date) value_b).compareTo((Date) value_a);
					else
						ret = ((Date) value_a).compareTo((Date) value_b);
				}
				return ret;
			}
		});
	}
	
	public static <T> void sortFieldList(List<T> list, final String property, final String sortType, final boolean isCN) {
		Collections.sort(list, new Comparator<T>() {
			public int compare(T a, T b) {
				int ret = 0;
				Field field = ReflectionUtils.findField(a.getClass(), property);
				Object value_a = ReflectionUtils.getField(field, a);
				Object value_b = ReflectionUtils.getField(field, b);
				if (field.getType() == String.class) {
					if (isCN) {
						Collator collator = Collator.getInstance(java.util.Locale.CHINA);
						if (StringUtils.isNotBlank(sortType) && sortType.equals(SORT_DESC)){
							//ret = collator.compare(value_a, value_b);//频繁的字符串比较,..Key的方式更快速
							ret = collator.getCollationKey(value_a.toString()).compareTo(collator.getCollationKey(value_b.toString()));
						}else{
							ret = collator.getCollationKey(value_b.toString()).compareTo(collator.getCollationKey(value_a.toString()));
						}
					} else {
						if (sortType != null && sortType.equals(SORT_DESC))
							ret = value_b.toString().compareTo(value_a.toString());
						else
							ret = value_a.toString().compareTo(value_b.toString());
					}
				} else if (field.getType() == Integer.class || field.getType() == Long.class || field.getType() == BigDecimal.class) {
					BigDecimal decA = new BigDecimal(value_a.toString());
					BigDecimal decB = new BigDecimal(value_b.toString());
					if (sortType != null && sortType.equals(SORT_DESC))
						ret = decB.compareTo(decA);
					else
						ret = decA.compareTo(decB);
				} else if (field.getType() == Date.class) {
					if (sortType != null && sortType.equals(SORT_DESC))
						ret = ((Date) value_b).compareTo((Date) value_a);
					else
						ret = ((Date) value_a).compareTo((Date) value_b);
				}
				return ret;
			}
		});
	}
	
	/**
	 * <p>Discription:[List集合排序类（默认不按照中文排序）]</p>
	 * Created on 2016-3-14
	 * @param list 目标集合
	 * @param property 排序字段名
	 * @param sortType 正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
	 * @author:武超强
	 */
	public static <T> void sortList(List<T> list, final String property, final String sortType) {
		sortMethodList(list, property, sortType, false);
	}

	/**
	 * <p>Discription:[对象数组排序(可按中文排序)]</p>
	 * Created on 2016-3-14
	 * @param array 对象数组
	 * @param property 排序字段名
	 * @param sortType  正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
	 * @param isCN  是否按中文排序
	 * @author:武超强
	 */
	public static <T> void sortObjectArray(T[] array, final String property, final String sortType, final boolean isCN) {
		Arrays.sort(array, new Comparator<T>() {
			private Collator collator = null;
			public int compare(T a, T b) {
				int ret = 0;
				Field field = ReflectionUtils.findField(a.getClass(), property);
				String getterMethodName = "get" + StringUtils.capitalize(property);
				Method method = ReflectionUtils.findMethod(a.getClass(), getterMethodName);
				Object value_a = ReflectionUtils.invokeMethod(method, a);
				Object value_b = ReflectionUtils.invokeMethod(method, b);
				if (field.getType() == String.class) {
					if (isCN) {
						collator = Collator.getInstance();
						CollationKey key1 = collator.getCollationKey(value_a.toString());
						CollationKey key2 = collator.getCollationKey(value_b.toString());
						if (sortType != null && sortType.equals(SORT_DESC))
							ret = key2.compareTo(key1);
						else
							ret = key1.compareTo(key2);
					} else {
						if (sortType != null && sortType.equals(SORT_DESC))
							ret = value_b.toString().compareTo(value_a.toString());
						else
							ret = value_a.toString().compareTo(value_b.toString());
					}
				} else if (field.getType() == Integer.class || field.getType() == Long.class || field.getType() == BigDecimal.class) {
					BigDecimal decA = new BigDecimal(value_a.toString());
					BigDecimal decB = new BigDecimal(value_b.toString());
					if (sortType != null && sortType.equals(SORT_DESC))
						ret = decB.compareTo(decA);
					else
						ret = decA.compareTo(decB);
				} else if (field.getType() == Date.class) {
					if (sortType != null && sortType.equals(SORT_DESC))
						ret = ((Date) value_b).compareTo((Date) value_a);
					else
						ret = ((Date) value_a).compareTo((Date) value_b);
				}
				return ret;
			}
		});
	}
	
	/**
	 * <p>Discription:[对象数组排序（默认不按照中文排序）]</p>
	 * Created on 2016-3-14
	 * @param array 对象数组
	 * @param property 排序字段名
	 * @param sortType  正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
	 * @author:武超强
	 */
	public static <T> void sortObjectArray(T[] array, final String property, final String sortType) {
		sortObjectArray(array, property, sortType, false);
	}

	/**
	 * <p>Discription:[字符串数组排序(可按中文排序)]</p>
	 * Created on 2016-3-14
	 * @param array 字符串数组
	 * @param sortType  正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
	 * @param isCN  是否按中文排序
	 * @author:武超强
	 */
	public static <T> void sortArray(T[] array, final String sortType,final boolean isCN) {
		if (sortType != null && sortType.equals(SORT_DESC)) {
			if (isCN) {
				Arrays.sort(array, Collections.reverseOrder(Collator.getInstance(java.util.Locale.CHINA)));
			} else {
				Arrays.sort(array, Collections.reverseOrder());
			}
		} else {
			if (isCN) {
				Arrays.sort(array, Collator.getInstance(java.util.Locale.CHINA));
			} else {
				Arrays.sort(array);
			}
		}
	}
	
	/**
	 * <p>Discription:[字符串数组排序（默认不按照中文排序）]</p>
	 * Created on 2016-3-14
	 * @param array 字符串数组
	 * @param sortType  正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
	 * @param isCN  是否按中文排序
	 * @author:武超强
	 */
	public static <T> void sortArray(T[] array, final String sortType) {
		sortArray(array, sortType, false);
	}

	/**
	 * <p>Discription:[获取list的toString(值以逗号分隔，无中括号)]</p>
	 * Created on 2016-5-6
	 * @param list
	 * @return
	 * @author:武超强
	 */
	public static <T> String getString(List<T> list) {
	 	Iterator<T> it = list.iterator();
        if (! it.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        for (;;) {
            T e = it.next();
            sb.append(e);
            if (! it.hasNext())
                return sb.toString();
            sb.append(',').append(' ');
        }
    }
	/**
	 * <p>Discription:[获取set的toString(值以逗号分隔，无中括号)]</p>
	 * Created on 2016-5-6
	 * @param set
	 * @return
	 * @author:武超强
	 */
	public static <T> String getString(Set<T> set) {
	 	Iterator<T> it = set.iterator();
        if (! it.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        for (;;) {
            T e = it.next();
            sb.append(e);
            if (! it.hasNext())
                return sb.toString();
            sb.append(',').append(' ');
        }
    }
	/**
	 * <p>Discription:[获取数组的toString(值以逗号分隔，无中括号)]</p>
	 * Created on 2016-5-6
	 * @param arr
	 * @return
	 * @author:武超强
	 */
	public static <T> String getString(T[] arr) {
		List<T> list = Arrays.asList(arr);
		return getString(list);
    }

	public static boolean isEmpty(Collection<?> col) {
		return CollectionUtils.isEmpty(col);
	}

}

