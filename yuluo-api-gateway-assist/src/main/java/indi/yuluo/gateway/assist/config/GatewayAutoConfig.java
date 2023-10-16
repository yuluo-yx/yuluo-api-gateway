package indi.yuluo.gateway.assist.config;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import indi.yuluo.gateway.assist.appliation.GatewayApplication;
import indi.yuluo.gateway.assist.domain.service.GatewayCenterService;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.defaults.DefaultGatewaySessionFactory;
import indi.yuluo.gateway.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(GatewayServiceProperties.class)
public class GatewayAutoConfig {

	private final Logger logger = LoggerFactory.getLogger(GatewayAutoConfig.class);

	@Bean
	public GatewayCenterService registerGatewayService() {
		return new GatewayCenterService();
	}

	@Bean
	public GatewayApplication gatewayApplication(GatewayServiceProperties properties, GatewayCenterService registerGatewayService, Configuration configuration, Channel gatewaySocketServerChannel) {
		return new GatewayApplication(properties, registerGatewayService, configuration, gatewaySocketServerChannel);
	}

	/**
	 * 创建网关配置对象；Configuration 是用于贯穿整个网关核心通信服务的。
	 */

	@Bean
	public Configuration gatewayCoreConfiguration(GatewayServiceProperties properties) {
		Configuration configuration = new Configuration();
		String[] split = properties.getGatewayAddress().split(":");
		configuration.setHostName(split[0].trim());
		configuration.setPort(Integer.parseInt(split[1].trim()));
		return configuration;
	}

	@Bean("gatewaySocketServerChannel")
	public io.netty.channel.Channel initGateway(Configuration configuration) throws ExecutionException, InterruptedException {
		// 1. 基于配置构建会话工厂
		DefaultGatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);
		// 2. 创建启动网关网络服务
		GatewaySocketServer server = new GatewaySocketServer(configuration, gatewaySessionFactory);
		Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
		io.netty.channel.Channel channel = future.get();
		if (null == channel) throw new RuntimeException("api gateway core netty server start error channel is null");
		while (!channel.isActive()) {
			logger.info("api gateway core netty server gateway start Ing ...");
			Thread.sleep(500);
		}
		logger.info("api gateway core netty server gateway start Done! {}", channel.localAddress());
		return channel;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory(GatewayServiceProperties properties,
			GatewayCenterService gatewayCenterService) {

		//1.拉去注册中心的配置信息
		Map<String, String> redisConfig = gatewayCenterService.queryRedisConfig(properties.getAddress());

		// 2. 构建 Redis 服务
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
		standaloneConfig.setHostName(redisConfig.get("host"));
		standaloneConfig.setPort(Integer.parseInt(redisConfig.get("port")));

		// 3. 默认配置信息；一般这些配置可以被抽取出来
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(100);
		poolConfig.setMaxWaitMillis(30 * 1000);
		poolConfig.setMinIdle(20);
		poolConfig.setMaxIdle(40);
		poolConfig.setTestWhileIdle(true);

		// 4. 创建 Redis 配置
		JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
				.connectTimeout(Duration.ofSeconds(2))
				.clientName("api-gateway-assist-redis-" + properties.getGatewayId())
				.usePooling().poolConfig(poolConfig).build();

		// 5. 实例化 Redis 链接对象
		return new JedisConnectionFactory(standaloneConfig, clientConfig);
	}
}
