package indi.yuluo.gateway.session.defaults;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.IGenericReferenceSessionFactory;
import indi.yuluo.gateway.session.SessionServer;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 泛化调用会话工厂
 */

public class GenericReferenceSessionFactory implements IGenericReferenceSessionFactory {

	private final Logger logger = LoggerFactory.getLogger(GenericReferenceSessionFactory.class);

	private final Configuration configuration;

	public GenericReferenceSessionFactory(Configuration configuration) {

		this.configuration = configuration;
	}

	@Override
	public Future<Channel> openSession() throws ExecutionException, InterruptedException {

		SessionServer sessionServer = new SessionServer(configuration);

		Future<Channel> future = Executors.newFixedThreadPool(2).submit(sessionServer);
		Channel channel = future.get();

		if (channel == null) {
			throw new RuntimeException("netty server start error channel is null");
		}

		while(!channel.isActive()) {
			logger.info("netty server gateway start Ing ...");
			Thread.sleep(5000);
		}
		logger.info("netty server gateway start Done! {}", channel.localAddress());

		return future;
	}

}
