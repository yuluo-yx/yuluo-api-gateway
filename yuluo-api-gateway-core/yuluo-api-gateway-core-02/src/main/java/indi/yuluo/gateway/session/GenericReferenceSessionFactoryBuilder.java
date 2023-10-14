package indi.yuluo.gateway.session;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class GenericReferenceSessionFactoryBuilder {

	private final Logger logger = LoggerFactory.getLogger(GenericReferenceSessionFactoryBuilder.class);

	public Future<Channel> build(Configuration configuration) throws InterruptedException, ExecutionException {

		SessionServer sessionServer = new SessionServer(configuration);

		Future<io.netty.channel.Channel> future = Executors.newFixedThreadPool(2).submit(sessionServer);
		io.netty.channel.Channel channel = future.get();

		if (channel == null) {
			throw new RuntimeException("netty server start error channel is null!");
		}

		while(!channel.isActive()) {
			logger.info("netty server gateway start Ing ...");
			Thread.sleep(5000);
		}
		logger.info("netty server gateway start Done! {}", channel.localAddress());

		return future;
	}

}
