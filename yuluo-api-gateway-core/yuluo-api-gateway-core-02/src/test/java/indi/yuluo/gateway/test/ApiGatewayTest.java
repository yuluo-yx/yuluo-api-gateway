package indi.yuluo.gateway.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.GenericReferenceSessionFactoryBuilder;
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
	public void test_GenericReference() throws ExecutionException, InterruptedException {

		Configuration configuration = new Configuration();
		configuration.addGenericReference("yuluo-api-gateway-test", "indi.yuluo.gateway.rpc.IActivityBooth", "sayHi");

		GenericReferenceSessionFactoryBuilder builder = new GenericReferenceSessionFactoryBuilder();
		Future<Channel> future = builder.build(configuration);

		logger.info("服务启动完成 {}", future.get().id());
		Thread.sleep(Long.MAX_VALUE);

	}

}

