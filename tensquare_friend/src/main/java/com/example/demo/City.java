/**
 * ClassName:City
 * Author:Administrator
 * Date:2020/3/16 001617:05
 * Description:TODO
 */
package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: yuliang
 * @Date: 2020/3/16 0016 17:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
	//	private String citycode;
	private String adcode;
	private String name;
	//	private String center;
//	private String level;
	private List<City> districts;


	private static Object obj1 = new Object();
	private static Object obj2 = new Object();


	public static void main(String[] args) {
		String aa =null;
		Objects.requireNonNull(aa, "clock");
		System.out.println(aa);
	}


}
