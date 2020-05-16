/**
 * ClassName:MenuVO
 * Author:机械革命
 * Date:2019/10/1222:29
 * Description:TODO
 */
package com.example.demo.web.hashmap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2019/10/12 22:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO implements Comparable<MenuVO> {
	private Long id;
	private String name;
	private String url;
	private List<MenuVO> childList;

	@Override
	public int compareTo(MenuVO o) {
//		return (int) (o.getId()-this.getId());
		return (int) (this.getId()-o.getId());
	}
}
