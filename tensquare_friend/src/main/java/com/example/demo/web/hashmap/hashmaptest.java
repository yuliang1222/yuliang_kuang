/**
 * ClassName:hashmaptest
 * Author:机械革命
 * Date:2019/10/1222:30
 * Description:TODO
 */
package com.example.demo.web.hashmap;

import com.example.demo.utils.JsonUtils;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.redisson.misc.Hash;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yuliang
 * @Date: 2019/10/12 22:30
 */
@Slf4j
public class hashmaptest {
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
		List<MenuDO> menuList = new ArrayList<MenuDO>() {
			{
				add(new MenuDO(33L, 11L, "合肥", "www.hefei.ocm"));
				add(new MenuDO(88L, 11L, "小且", "www.xiaoqie.ocm"));
				add(new MenuDO(55L, 88L, "雄安是", "www.xionga.ocm"));
				add(new MenuDO(44L, 88L, "小米", "www.xiaomi.ocm"));
				add(new MenuDO(22L, 11L, "六安", "www.liuan.ocm"));
				add(new MenuDO(11L, 0L, "安徽", "www.baidu.ocm"));


			}
		};
		List<MenuVO> meunVoList = buildMenuTree(menuList);
//		log.info(JsonUtils.toString(menuList));
		System.out.println("----------------------------");
		log.info(JsonUtils.toString(meunVoList));
//		List<String> list = new ArrayList<>();
//		meunVoList.stream()
//				.filter(Objects::nonNull)
//				.forEach(ls -> ls.getChildList().stream()
//						.filter(Objects::nonNull)
//						.forEach(lc -> list.add(lc.getName())));
//		log.info(JsonUtils.toString(list));

	}


	public static List<MenuVO> buildMenuTree(List<MenuDO> menuList) throws InvocationTargetException, IllegalAccessException {
		//检查列表为空
		if (CollectionUtils.isEmpty(menuList)) {
			return Collections.emptyList();
		}
		//依次处理菜单
		int size = menuList.size();
		List<MenuVO> rootList = new ArrayList<>(size);
		Map<Long, MenuVO> menuMap = new HashMap<>(size);
		for (MenuDO menuDO : menuList) {
			//赋值菜单对象
			Long menuDoId = menuDO.getId();
			MenuVO menu = menuMap.get(menuDoId);
			if (Objects.isNull(menu)) {
				menu = new MenuVO();
				menu.setChildList(new ArrayList<>());
				menuMap.put(menuDoId, menu);
			}
			menu.setId(menuDO.getId());
			menu.setName(menuDO.getName());
			menu.setUrl(menuDO.getUrl());
//			BeanUtils.copyProperties(menu,menuDO );

			//根据父标识处理
			Long parentId = menuDO.getParentId();
//			if (Objects.nonNull(parentId)) {
			if (parentId!=0) {
				// 构建父菜单对象
				MenuVO parentMenu = menuMap.get(parentId);
				if (Objects.isNull(parentMenu)) {
					parentMenu = new MenuVO();
					parentMenu.setId(parentId);
					parentMenu.setChildList(new ArrayList<>());
					menuMap.put(parentId, parentMenu);
				}
				//添加子菜单对象
				parentMenu.getChildList().add(menu);
				Collections.sort(parentMenu.getChildList());
				System.out.println();
			} else {
				// 添加根菜单对象
				rootList.add(menu);
			}

		}
		// 返回根菜单列表
		return rootList;
	}
//students.stream()
//		.sorted((stu1,stu2) ->Long.compare(stu2.getId(), stu1.getId()))
//			.sorted((stu1,stu2) -> Integer.compare(stu2.getAge(),stu1.getAge()))
//			.forEach(System.out::println);
//public int compareTo(Field o) {
//	 TODO Auto-generated method stub
	// 先按age排序
//	if (this.age > o.getAge()) {
//		return (this.age - o.getAge());
//	}
//	if (this.age < o.getAge()) {
//		return (this.age - o.getAge());
//	}
//
//	// 按name排序
//	if (this.name.compareTo(o.getName()) > 0) {
//		return 1;
//	}
//	if (this.name.compareTo(o.getName()) < 0) {
//		return -1;
//	}
//
//	return 0;

}