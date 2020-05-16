/**
 * ClassName:MapObject
 * Author:Administrator
 * Date:2019/12/25 002514:06
 * Description:TODO
 */
package com.example.demo.itheima;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.web.hashmap.MenuDO;
import org.apache.poi.ss.formula.functions.T;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author: yuliang
 * @Date: 2019/12/25 0025 14:06
 */
public class MapObject {
	public static void main(String[] args) {
		ConcurrentHashMap<Object, Boolean> seen = new ConcurrentHashMap<>();
		Boolean fd = seen.putIfAbsent("fd", false);
		Boolean fd1 = seen.putIfAbsent("fd", true);
		Boolean fd2 = seen.putIfAbsent("fd", false);
		Boolean fd3 = seen.putIfAbsent("fd", true);
		Boolean fd4 = seen.putIfAbsent("fd", false);
		Boolean fd5 = seen.putIfAbsent("fd", true);
		Boolean fd6 = seen.putIfAbsent("fd", false);
		seen.putIfAbsent("fd", true);
		List<MenuDO> menuList = new ArrayList<MenuDO>() {
			{
				add(new MenuDO(11L, null, "安徽", "www.baidu.ocm"));
				add(new MenuDO(44L, 33L, "小米", "www.xiaomi.ocm"));
				add(new MenuDO(55L, null, "雄安是", "www.xionga.ocm"));
				add(new MenuDO(88L, 55L, "小且", "www.xiaoqie.ocm"));
				add(new MenuDO(22L, 11L, "六安", "www.liuan.ocm"));
				add(new MenuDO(33L, 11L, "合肥", "www.hefei.ocm"));
			}
		};
		List<MenuDO> menuDOS = dewightorintersectionList(menuList, "tts1","parentId");
		System.out.println(JSONObject.toJSONString(menuDOS));
	}

	private static<T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor, String methodFlog) {
		ConcurrentHashMap<Object, Boolean> seen = new ConcurrentHashMap<>();
		if ("tts".equals(methodFlog)) {
			//取相同
			Predicate<T> tPredicate = t -> seen.putIfAbsent(keyExtractor.apply(t), true) != null;
			return tPredicate ;
		}else {
			System.out.println();
			//去重
			Predicate<T> tPredicate = t -> seen.putIfAbsent(keyExtractor.apply(t), true) == null;
			return tPredicate;
		}
	}

	public static<T>  List<T> dewightorintersectionList(List<T> list, String methodFlag, String... field) {
		if (field.length == 0) {
			return list.stream().filter(distinctByKey(b -> b, methodFlag)).collect(Collectors.toList());
		} else {
			return list.stream().filter(distinctByKey(b -> {
				try {
					BeanInfo beanInfo = Introspector.getBeanInfo(b.getClass());
					PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
					StringBuffer stringBuffer = new StringBuffer();
					List<String> stringList = Arrays.asList(field);
					stringList.forEach(p->{
						for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
							String key = propertyDescriptor.getName();
							if (key.equals(p)) {
								Method readMethod = propertyDescriptor.getReadMethod();
								try {
									stringBuffer.append(readMethod.invoke(b));
									stringBuffer.append("\t");
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								}
							}
						}
					});
					System.out.println(stringBuffer.toString());
					return stringBuffer.toString();
				} catch (IntrospectionException e) {
					e.printStackTrace();
					return null;
				}
			}, methodFlag)).collect(Collectors.toList());
		}
	}
}
