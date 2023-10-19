package indi.yuluo.gateway.sdk.config;

import indi.yuluo.gateway.sdk.application.GatewaySDKApplication;
import indi.yuluo.gateway.sdk.domain.service.GatewayCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Configuration
@EnableConfigurationProperties(GatewaySDKServiceProperties.class)
public class GatewaySDKAutoConfig {

	private final Logger logger = LoggerFactory.getLogger(GatewaySDKAutoConfig.class);

	@Bean
	public GatewayCenterService gatewayCenterService() {

		return new GatewayCenterService();
	}

	@Bean
	public GatewaySDKApplication gatewaySDKApplication(GatewaySDKServiceProperties properties, GatewayCenterService gatewayCenterService) {

		return new GatewaySDKApplication(properties, gatewayCenterService);
	}
}
