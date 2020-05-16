package com.example.demo.itheima.strategy1;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.datasource.properties.DynamicDataSourceProperties;
import com.example.demo.wx.moban.config.WxAppProperties;

import com.example.demo.wx.moban.factory.wxtemplate.WxtemplateFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

/**
 * @Author: yuliang
 * @Date: 2019/9/27 10:31
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties(WxAppProperties.class)
public class WorkPunishTest {



	@Autowired
	private WxAppProperties wxAppProperties;
	@Autowired
	private DynamicDataSourceProperties dynamicDataSourceProperties;
	@Autowired
	private WxtemplateFactory wxtemplateFactory;

	@Test
	public void insertuser() {
		HashMap<String, Object> objectObjectHashMap = new HashMap<>();


	}
	@Autowired
	private PunishFactory punishFactory;

	public  void punish(String state){
		//静态工厂类获取处罚对象
		IPunish punish = punishFactory.getPunish(state);
		//执行处罚逻辑
		punish.exePunish();
	}

	@Test
	public void punish() {
		String state ="LatePunish";
		punish(state);

	}

	/**
	 * 通过输入指定日期时间生成cron表达式
	 * @param
	 * @return cron表达式
	 */
	@Test
	public  void getCron(){
		Date date = new Date();
		String dateFormat="ss mm HH dd MM ? yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		System.out.println(formatTimeStr);


	}
	@Test
	public  void getCron111(){

		final String[] array = {"adc", "abc"};
		List<String> stringList = Arrays.asList(array);
		String test = "av";
		//有就是true
		//没有就是false
		boolean empty = !CollectionUtils.isEmpty(stringList.stream().filter(ls->ls.startsWith(test)).collect(Collectors.toList()));
		System.out.println(empty);
	}

	@Autowired
	private  StringRedisTemplate redisTemplate;
//	from=01051749
//	appkey=61000158
//	secret=0031186e-5cc6-45a6-a090-3e88ec220452
//			ver=3.4.2
//	权益id=1000076，签名=32c616e8858c8cef9c1e

//	http://open.d.api.edaijia.cn/customer/authorizeToken?appkey=xxx&from=xxx&phone=xxx&sig=xxx&strategyId=xxx&strategyServiceSign=xxx&timestamp=xxx&ver=xxx
//	正式环境参数
//	appkey：61000298
//	secret：0dea9366-14ec-44ca-a95f-887a6d97b5ac
//			from不变
	@Test
	public  void edaijia(){
		String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Map<String, String> treeMap = new TreeMap<>();
		treeMap.put("appkey", "61000298");
		treeMap.put("from", "01051749");
		treeMap.put("phone", "15201236548");
		treeMap.put("strategyId", "1000342");
		treeMap.put("strategyServiceSign", "c64cbe51fd7988f17e76");
		treeMap.put("timestamp", format);
		treeMap.put("ver", "3.4.2");
		treeMap.put("thirdOrderId", "WE1qoqYO");
		StringBuilder sb = new StringBuilder();
		String secret = "0dea9366-14ec-44ca-a95f-887a6d97b5ac";
		for (String key : treeMap.keySet()) {
			String value = treeMap.get(key);
			if (!key.equals("gpsstring") && !key.equals("callback") && !key.equals("_") && !key.equals("sig")) {
				sb.append(key);
				sb.append(value);
			}
		}

		String sig = MD5Utils.md5(secret + sb.toString() + secret).substring(0,30);
		treeMap.put("sig", sig);

		try {
			UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://open.api.edaijia.cn/customer/authorizeToken?appkey={appkey}&from={from}&phone={phone}&sig={sig}&strategyId={strategyId}&strategyServiceSign={strategyServiceSign}&thirdOrderId={thirdOrderId}&timestamp={timestamp}&ver={ver}").build().expand(treeMap).encode();
			RestTemplate restTemplate  = new RestTemplate();
			URI uri = uriComponents.toUri();
			System.out.println(uri.toString());
			String s = restTemplate.getForObject(uri, String.class);
			System.out.println(s);
			JSONObject jsonObject = JSONObject.parseObject(s);
			String data = jsonObject.getString("data");

			String s1 = "https://h5.edaijia.cn/app/index.html?strategyToken=" + data + "&from=01051749";
			System.out.println(s1);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}

//https://h5.d.edaijia.cn/app/index.html?strategyToken=xxx&from=xxx,拼接url跳转edaijiaH5页面

	@Test
	public  void getCron1(){
		SeckillStatus seckillStatus = new SeckillStatus("小侄女", new Date(),1, 12L,"20130202");
		Long seckillOrderQueue = redisTemplate.boundListOps("SeckillOrderQueue").leftPush(JSONObject.toJSONString(seckillStatus));
		redisTemplate.boundHashOps("UserQueueStatus").put("小侄女", JSONObject.toJSONString(seckillStatus));
		System.out.println(seckillOrderQueue);
		String seckillOrderQueue1 = redisTemplate.boundListOps("SeckillOrderQueue").rightPop();
		seckillStatus.setStatus(2);
		seckillStatus.setOrderId("good001");
		seckillStatus.setMoney(80f);
		redisTemplate.boundHashOps("UserQueueStatus").put("小侄女", JSONObject.toJSONString(seckillStatus));
		System.out.println(seckillOrderQueue1);

	}


	@Test
	public  void getCron11(){
//		SeckillStatus username = new SeckillStatus("小侄女", new Date(),1, 12L,"20130202");
//		redisTemplate.boundHashOps("UserQueueCount").increment("小侄女", 1);
		Long[] ids = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};
		Long aLong = redisTemplate.boundListOps("SeckillGoodsCountList_" + 1).leftPushAll( JSONObject.toJSONString(ids));
		Long seckillGoodsCount = redisTemplate.boundHashOps("SeckillGoodsCount").increment("SeckillGoodsCountList_" + 1, 10L);
		Long seckillGoodsCount1 = redisTemplate.boundHashOps("SeckillGoodsCount").increment("SeckillGoodsCountList_" + 1, -1);
		System.out.println(aLong);
		System.out.println(seckillGoodsCount);
		System.out.println(seckillGoodsCount1);
	}


	public static void main(String[] args) {

		ArrayList<String> strings = new ArrayList<>();
		strings.add("2013-10-12 18:12:10");
		strings.add("2013-10-13 18:12:10");
		strings.add("2013-10-13 15:12:10");
		strings.stream().forEach(ls -> ls = timeset(ls));

		Collections.sort(strings);
		System.out.println(strings);
	}

	private static String timeset(String ls) {
		String a = compile("[^0-9]").matcher(ls).replaceAll("").trim();
		return a;
	}
}
