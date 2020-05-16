/**
 * ClassName:Composite
 * Author:机械革命
 * Date:2019/9/2313:35
 * Description:TODO
 */
package com.example.demo.itheima.composition;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2019/9/23 13:35
 */
@Slf4j
public class Composite extends Component {
	private List<Component> children = new ArrayList<Component>();

	public Composite(String name) {
		super(name);
	}

	@Override
	void add(Component component) {
		children.add(component);
	}

	@Override
	void remove(Component component) {
		children.remove(component);
	}

	@Override
	void display(int depth) {
		log.info(AppendString.appendChar("-",depth)+ name);
		for (Component c : children) {
			c.display(depth + 2);
		}
	}
}
