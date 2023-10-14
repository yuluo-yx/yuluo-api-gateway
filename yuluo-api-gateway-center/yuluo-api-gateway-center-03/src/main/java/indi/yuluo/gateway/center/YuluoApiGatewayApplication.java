package indi.yuluo.gateway.center;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 网关注册中心启动服务
 */

@SpringBootApplication
@Configurable
public class YuluoApiGatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(YuluoApiGatewayApplication.class, args);
	}

}
