/**
 * ClassName:mapperUtils
 * Author:机械革命
 * Date:2019/10/1613:36
 * Description:TODO
 */
package com.example.demo.web.hashmap;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import java.util.Map;

/**
 * @Author: yuliang
 * @Date: 2019/10/16 13:36
 */
public class mapperUtils {
	private static  final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	/**a对象->b类->b对象**/
	public static<S,R>  R mappperBean(S s, Class<R> rClass) {
		return mapperFactory.getMapperFacade().map(s, rClass);
	}
//
//	public static<S,R>  R mappperBean(S s, Class<R> rClass,Map<String,String> diffFieldMap) {
//		ClassMapBuilder<?, R> classMap = mapperFactory.classMap(s.getClass(), rClass);
//		diffFieldMap.
//		return mapperFactory.getMapperFacade().map(s, rClass);
//	}

}
