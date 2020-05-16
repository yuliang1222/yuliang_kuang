/**
 * ClassName:CompositeApplicationTests
 * Author:机械革命
 * Date:2019/9/2313:36
 * Description:TODO
 */
package com.example.demo.itheima.composition;

/**
 * @Author: yuliang
 * @Date: 2019/9/23 13:36
 */
public class CompositeApplicationTests {
	public static void main(String[] args) {
		Composite root=new Composite("root");
		root.add(new Leaf("Leaf A"));
		root.add(new Leaf("Leaf B"));

		Composite comp=new Composite("Composite X");
		comp.add(new Leaf("Leaf XA"));
		comp.add(new Leaf("Leaf XB"));

		root.add(comp);

		Composite comp2=new Composite("Composite XY");
		comp2.add(new Leaf("Leaf XYA"));
		comp2.add(new Leaf("Leaf XYB"));

		comp.add(comp2);

		root.add(new Leaf("Leaf C"));

		Leaf leaf=new Leaf("Leaf D");
		root.add(leaf);
		root.remove(leaf);


		root.display(1);
}
}
