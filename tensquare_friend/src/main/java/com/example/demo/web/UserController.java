/**
 * ClassName:UserController
 * Author:机械革命
 * Date:2019/9/2316:20
 * Description:TODO
 */
package com.example.demo.web;





import static com.example.demo.constant.Constants.*;

import com.example.demo.constant.Constants;
import com.example.demo.utils.Result;
import com.example.demo.web.hashmap.MenuDO;
import com.example.demo.web.hashmap.MenuVO;
import com.example.demo.web.hashmap.hashmaptest;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.lang.invoke.ConstantCallSite;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuliang
 * @Date: 2019/9/23 16:20
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {


	@Autowired
	private RedissonClient redissonClient;
	/**
	 * redisson  解锁用户
	 * @return
	 */
	@GetMapping("unLock")
	public Result unLock(@RequestParam Integer MobileNo) {
//		RSet<String> setCache = redissonClient.getSet(REDIS_LOCK_USER);
//		RSetCache<String> setCache = redissonClient.getSetCache(REDIS_LOCK_USER);

		RMapCache<Object, Object> map = redissonClient.getMapCache(REDIS_LOCK_USER);

//		if (map.contains(MobileNo)) {
//			map.remove(MobileNo);
//		}
		map.remove("1000");
		map.fastRemove(1002);
		return Result.success(map);
	}
	/**
	 * redisson  判断是否存在这个电话号码
	 * @return
	 */
	@GetMapping("isunLock")
	public Result isunLock(@RequestParam Integer MobileNo) {
		RSetCache<Object> ceshi = redissonClient.getSetCache("ceshi");
//		Set<String> setCache = redisTemplate.opsForSet().members(REDIS_LOCK_USER);
		return Result.success(ceshi.contains(MobileNo));
	}

	@Autowired
	private StringRedisTemplate redisTemplate;
	@GetMapping("lock")
	public Result lock() throws InvocationTargetException, IllegalAccessException {
//		Long add = redisTemplate.opsForSet().add(REDIS_LOCK_USER, "182", "189", "183");

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
		List<MenuVO> menuVOS = hashmaptest.buildMenuTree(menuList);
		RMapCache<Object, Object> cmsmap = redissonClient.getMapCache("cms");

//		boolean expire = map.expire(100, TimeUnit.SECONDS);
		cmsmap.fastPut("cms", menuVOS);

//		map.fastPutIfAbsent(1002, 10024, 10, TimeUnit.SECONDS);
//		map.fastPutIfAbsent(1001, 10024, 10, TimeUnit.SECONDS,10,TimeUnit.SECONDS);
		return Result.success();

	}

}
