/**
 * ClassName:poi
 * Author:Administrator
 * Date:2019/12/4 000410:24
 * Description:TODO
 */
package com.example.demo.web;

import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2019/12/4 0004 10:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class poi {

	@Test
	public void lock() throws InvocationTargetException, IllegalAccessException {
		File file = new File("D:\\平安项目_放\\fls-cgj-service\\src\\main\\resources\\user.xls");
		List<User> beanList = ExcelTool.getBeanList(User.class, file);
		for (User user : beanList) {
			System.out.println(user.toString());
		}

	}

	@Test
	public void lods() {
			String title = "abc";
			List<List<Object>> rowList = new ArrayList<List<Object>>();
			// 表头
			List<Object> head = new ArrayList<Object>();
			head.add("头像");
			head.add("姓名");
			head.add("年龄");
			head.add("备注");
			head.add("空间");
			rowList.add(head);
			// 表数据
			List<Object> row = new ArrayList<Object>();
			try {
				row.add(new URL("https://cdn-img.easyicon.net/png/11002/1100254.gif"));
			} catch (Exception e) {}
			row.add("孙悟空");
			row.add("18");
			row.add("唐僧大徒弟");
			row.add("https://www.baidu.com");
			rowList.add(row);
			List<Object> row2 = new ArrayList<Object>();
			try {
				row2.add(new URL("https://cdn-img.easyicon.net/png/5467/546720.gif"));
			} catch (Exception e) {}
			row2.add("猪八戒");
			row2.add("18");
			row2.add("唐僧儿徒弟");
			row2.add("https://www.tencent.com");
			rowList.add(row2);
			ExcelTool.export(title, rowList);
			System.out.println("导出完成，请刷新工程，查看文件....");
		}



}