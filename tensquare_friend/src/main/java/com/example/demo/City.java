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

/**
 * @Author: yuliang
 * @Date: 2020/3/16 0016 17:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City  {

//	private String citycode;
	private String adcode;
	private String name;
//	private String center;
//	private String level;
	private List<City> districts;

}
