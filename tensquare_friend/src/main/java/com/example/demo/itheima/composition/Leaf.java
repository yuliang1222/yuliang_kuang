/**
 * ClassName:Leaf
 * Author:机械革命
 * Date:2019/9/2311:55
 * Description:TODO
 */
package com.example.demo.itheima.composition;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yuliang
 * @Date: 2019/9/23 11:55
 */

@Slf4j
public class Leaf extends Component {
	public Leaf(String name) {
		super(name);
	}

	@Override
	void add(Component component) {
		log.error("cannot add to a leaf");
	}

	@Override
	void remove(Component component) {
		log.error("cannot remove to a leaf");
	}

	@Override
	void display(int depth) {
		log.info(AppendString.appendChar("-",depth) + name);
	}
}
