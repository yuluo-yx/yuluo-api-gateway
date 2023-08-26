package indi.yuluo.gateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@EnableDubbo
@Configurable
@SpringBootApplication
public class ApiGatewayTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiGatewayTestApplication.class);
	}

}
