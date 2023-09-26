package indi.yuluo.gateway.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import indi.yuluo.gateway.mapping.HttpCommandType;
import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.defaults.DefaultGatewaySessionFactory;
import indi.yuluo.gateway.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class APIGatewayTest08 {

	private final Logger logger = LoggerFactory.getLogger(APIGatewayTest08.class);

	@Test
	public void test_gateway_06() throws ExecutionException, InterruptedException {

		// 1. 创建配置信息加载配置
		Configuration configuration = new Configuration();

		/**
		 * get http://127.0.0.1:8001/wg/activity/sayHi?str=yuluo
		 *
		 * 需要设置 content-type 请求头属性 value: application/json
		 */
		HttpStatement httpStatement1 = new HttpStatement(
				"yuluo-api-gateway-test",
				"indi.yuluo.gateway.rpc.IActivityBooth",
				"sayHi",
				"java.lang.String",
				"/wg/activity/sayHi",
				HttpCommandType.GET,
				false
		);

		/**
		 * post http://127.0.0.1:8001/wg/activity/sayHi?str=yuluo
		 *
		 * 需要设置 content-type 请求头属性 value: application/json
		 */
		HttpStatement httpStatement2 = new HttpStatement(
				"yuluo-api-gateway-test",
				"indi.yuluo.gateway.rpc.IActivityBooth",
				"insert",
				"indi.yuluo.gateway.rpc.dto.XReq",
				"/wg/activity/insert",
				HttpCommandType.POST,
				true
		);

		// 加载进入 configuration 中
		configuration.addMapper(httpStatement1);
		configuration.addMapper(httpStatement2);

		// 2. 基于配置构建会话工厂
		DefaultGatewaySessionFactory gatewaySessionFactory =
				new DefaultGatewaySessionFactory(configuration);

		// 3. 创建并启动网关网络服务
		GatewaySocketServer server = new GatewaySocketServer(configuration, gatewaySessionFactory);
		Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
		Channel channel = future.get();
		if (future == null) {
			throw new RuntimeException("netty server start error channel is null1");
		}

		while (!channel.isActive()) {
			logger.info("netty server gateway start Ing ... ");
			Thread.sleep(500);
		}

		logger.info("netty server gateway start Done! {}", future.get().id());
		Thread.sleep(Long.MAX_VALUE);

	}

}

