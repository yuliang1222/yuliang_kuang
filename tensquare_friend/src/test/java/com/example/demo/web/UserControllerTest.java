package com.example.demo.web;


import com.example.demo.DemoApplication;
import com.example.demo.web.hashmap.MenuDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2019/10/18 11:04
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserControllerTest {

	@Autowired
	private RedissonClient redissonClient;

	@Test
	public void lock() throws InvocationTargetException, IllegalAccessException {

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
//		dewightorintersectionList( menuList, "tts","parentId");

//		RMapCache<String, String> cmsmap = redissonClient.getMapCache("cms");
//		cmsmap.fastPut("cms1", JSONObject.toJSONString(menuList));
//		String cms2 = cmsmap.remove("cms");
//		cmsmap.fastRemove();

//		boolean cms = cmsmap.fastPut("cms1",  JSON.toJSONString(menuList),1L,TimeUnit.DAYS);
//
//		String cms1 = cmsmap.get("cms1");
//		List<MenuDO> menuVOSlist = JSON.parseArray(cms1, MenuDO.class);

//		List<MenuVO> menuVOS = hashmaptest.buildMenuTree(menuVOSlist);
//		log.info("menuVOS={}", JsonUtils.toString(menuVOS));
//		boolean expire = map.expire(100, TimeUnit.SECONDS);


//		RMapCache<Object, Object> cmsmapresult = redissonClient.getMapCache("cms");
//		RSetCache<Object> cmsmapresultset = redissonClient.getSetCache("cms");
//		boolean add = cmsmapresultset.add(menuList, 1, TimeUnit.DAYS);
//		RSetCache<String> setcms = redissonClient.getSetCache("setcms");
//		setcms.add("180126447896", 15, TimeUnit.MILLISECONDS);
//		setcms.add("180126447826", 15, TimeUnit.MILLISECONDS);
//		setcms.add("180126447896", 15, TimeUnit.MILLISECONDS);
//		setcms.remove("180126447826");

	}



}
