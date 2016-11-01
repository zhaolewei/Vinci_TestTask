package com.zlw.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * List集合帮助类
 * 
 * @author 赵LW
 */
public class ListHelper {

	/**
	 * 去除List中重复的部分 缺点： 不保证顺序
	 * 
	 * @param list
	 */
	public static void removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
	}

	/**
	 * 去除List中重复的部分 保证顺序
	 * 
	 * @param list
	 */
	public static void removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
	}

	/**
	 * List去除重复
	 * 
	 * @param <T>
	 * @param list
	 */
	public static <T> void removeDuplicate2(List<T> list) {
		List<T> result = new ArrayList<T>();

		for (T s : list) {
			if (Collections.frequency(result, s) < 1)
				result.add(s);
		}
		list.clear();
		list.addAll(result);
		result = null;
	}

	/**
	 * List<Bean> 排序 Bean 可为任意JavaBean对象,必须实现参数的Get/Set方法
	 * 
	 * @param targetList
	 * @param sortField
	 * @param sortMode
	 *            排序方式 "desc"-倒序； 其他为正序
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> void sort(List<T> targetList, final String sortField, final String sortMode) {

		Collections.sort(targetList, new Comparator() {
			public int compare(Object obj1, Object obj2) {
				int retVal = 0;
				try {
					// 首字母转大写
					String newStr = sortField.substring(0, 1).toUpperCase() + sortField.replaceFirst("\\w", "");
					String methodStr = "get" + newStr;

					Method method1 = ((T) obj1).getClass().getMethod(methodStr, null);
					Method method2 = ((T) obj2).getClass().getMethod(methodStr, null);
					if (sortMode != null && "desc".equals(sortMode)) {
						retVal = method2.invoke(((T) obj2), null).toString()
								.compareTo(method1.invoke(((T) obj1), null).toString()); // 倒序
					} else {
						retVal = method1.invoke(((T) obj1), null).toString()
								.compareTo(method2.invoke(((T) obj2), null).toString()); // 正序
					}
				} catch (Exception e) {
					throw new RuntimeException();
				}
				return retVal;
			}
		});
	}
//
//	public static void main(String[] args) {
//
//		User user1 = new User(1, "aaa");
//		User user2 = new User(2, "bbb");
//		User user3 = new User(3, "ccc");
//
//		List<User> data = new ArrayList<>();
//		data.add(user2);
//		data.add(user1);
//		data.add(user1);
//		data.add(user3);
//		data.add(user1);
//		data.add(user3);
//		data.add(user2);
//		data.add(user1);
//		data.add(user2);
//		data.add(user3);
//		System.out.println(data);
//		removeDuplicate2(data);
//		System.out.println(data);
//	}

}
