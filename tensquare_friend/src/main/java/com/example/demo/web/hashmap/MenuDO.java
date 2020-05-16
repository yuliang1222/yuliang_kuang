/**
 * ClassName:MenuDO
 * Author:机械革命
 * Date:2019/10/1222:26
 * Description:TODO
 */
package com.example.demo.web.hashmap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yuliang
 * @Date: 2019/10/12 22:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDO {
	private Long id;
	private Long parentId;
	private String name;
	private String url;
}
