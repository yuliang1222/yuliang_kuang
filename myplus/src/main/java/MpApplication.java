/**
 * ClassName:mpApplication
 * Author:Administrator
 * Date:2020/6/7 00078:20
 * Description:TODO
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: yuliang
 * @Date: 2020/6/7 0007 8:20
 */
//@EnableAsync
//@EnableScheduling
@SpringBootApplication
@MapperScan("com.examp")
@ComponentScan(value = "com.examp")
public class MpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpApplication.class, args);
	}

}