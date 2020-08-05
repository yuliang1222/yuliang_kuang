/**
 * ClassName:TestService2
 * Author:Administrator
 * Date:2020/7/20 002013:37
 * Description:TODO
 */
package com.pazl.authorization.danliduoli;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: yuliang
 * @Date: 2020/7/20 0020 13:37
 */
@Component("TestService2")
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class TestService2 {
	public HashMap hashMap = new HashMap(2){{
	}};
	Thread thread = new Thread();
	public HashMap getMap() {
		return hashMap;
	}

}
