/**
 * ClassName:test
 * Author:机械革命
 * Date:2019/8/3011:03
 * Description:TODO
 */
package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.redisson.misc.Hash;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author: yuliang
 * @Date: 2019/8/30 11:03
 */

public class test {
	public static void main(String[] args) throws InterruptedException, IOException {
		Comparator<City> netTypeComparator = new Comparator<City>() {
			@Override
			public int compare(City o1, City o2) {
				return Integer.valueOf(o1.getAdcode()) - Integer.valueOf(o2.getAdcode());
			}
		};
		List<City> jshuju = jshuju("city.json", new TypeReference<List<City>>() {
		});
		Collections.sort( jshuju,netTypeComparator);

		ArrayList<HashMap<String,Object>> shenList = new ArrayList<>();
		for (City k : jshuju) {
			// k  是 省
			Collections.sort( k.getDistricts(),netTypeComparator);

			ArrayList<HashMap<String,Object>> shiList = new ArrayList<>();
			for (City h : k.getDistricts()) {
				//h 是  市
				Collections.sort( h.getDistricts(),netTypeComparator);

				List<String> xianListName = h.getDistricts().stream().map(City::getName).collect(Collectors.toList());
				HashMap<String, Object> shiMap = new HashMap<>();
				shiMap.put(h.getName(), xianListName);
				shiList.add(shiMap);
			}
			HashMap<String, Object> shenMap = new HashMap<>();
			shenMap.put(k.getName(), shiList);
			shenList.add(shenMap);
		}
		System.out.println(JSONObject.toJSONString(shenList));
	}


	public  static final ObjectMapper mapper = new ObjectMapper();

	public static <T> List<City> jshuju(String addr, TypeReference<T> type) throws IOException {
		ClassPathResource resource = new ClassPathResource(addr);
		String content = org.apache.commons.lang.StringUtils.join(IOUtils.readLines(resource.getInputStream(), "UTF-8"), "\n");
		return JSONObject.parseArray(content, City.class);


	}
}
