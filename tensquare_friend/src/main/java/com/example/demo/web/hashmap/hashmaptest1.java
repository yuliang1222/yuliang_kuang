/**
 * ClassName:hashmaptest
 * Author:机械革命
 * Date:2019/10/1222:30
 * Description:TODO
 */
package com.example.demo.web.hashmap;

import com.example.demo.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.redisson.misc.Hash;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2019/10/12 22:30
 */
@Slf4j
public class hashmaptest1 {
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
		String str = "20150252522512";
		char[] aa=str.toCharArray();//分割字符串为char数组
		int j=aa.length/2;//char数组长度整除2
		int k=aa.length%2;//char数组长度对2取余，为了处理字符为奇数
		int le=j+k;//定义字符串数组的长度
		String[] bbb=new String[le];
		for(int i=0;i<j;i++){//把字符两两存进去
			bbb[i]=aa[2*i]+" "+aa[2*i+1];
		}
		if(k==1){
			bbb[j]=aa[aa.length-1]+"";//字符长度为奇数时，最后一个存一个字母
		}
		for(int p=0;p<bbb.length;p++){
			System.out.println(bbb[p]);
		}

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
//			menu.setId(menuDO.getId());
//			menu.setName(menuDO.getName());
//			menu.setUrl(menuDO.getUrl());
			BeanUtils.copyProperties(menu,menuDO );
			menu = mapperUtils.mappperBean(menuDO, MenuVO.class);
			//根据父标识处理
			Long parentId = menuDO.getParentId();
			if (Objects.nonNull(parentId)) {
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
			} else {
				// 添加根菜单对象
				rootList.add(menu);
			}

		}
		// 返回根菜单列表
		return rootList;
	}

}