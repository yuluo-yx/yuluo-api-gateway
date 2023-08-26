package indi.yuluo.gateway.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import indi.yuluo.gateway.session.SessionServer;
import io.netty.channel.Channel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class ApiGatewayTest {

	private final Logger logger = LoggerFactory.getLogger(ApiGatewayTest.class);

	@Test
	public void test() throws ExecutionException, InterruptedException {

		SessionServer sessionServer = new SessionServer();
		Future<Channel> future = Executors.newFixedThreadPool(2).submit(sessionServer);
		Channel channel = future.get();

		if (null == channel) {

			throw new RuntimeException("netty server start error: channel is null!");
		}

		// 检测是否启动成功
		while (!channel.isActive()) {

			logger.info("Netty Server 服务启动……");
			Thread.sleep(500);
		}

		logger.info("Netty Server 服务启动完成。{}", channel.localAddress());
		Thread.sleep(Long.MAX_VALUE);

	}

}

