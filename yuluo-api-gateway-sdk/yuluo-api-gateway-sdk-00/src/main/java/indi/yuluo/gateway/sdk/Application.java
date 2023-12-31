package indi.yuluo.gateway.sdk;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Configurable
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}
