package indi.yuluo.gateway.test;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Test;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class RPCTest {

	@Test
	public void test_rpc() {

		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("yuluo-api-gateway-test");
		applicationConfig.setQosEnable(false);

		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress("zookeeper://127.0.0.1:2181");
		registryConfig.setRegister(false);

		ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
		referenceConfig.setInterface("indi.yuluo.gateway.rpc.IActivityBooth");
		referenceConfig.setVersion("1.0.0");
		referenceConfig.setGeneric("true");

		DubboBootstrap bootstrap = DubboBootstrap.getInstance();
		bootstrap.application(applicationConfig)
				.registry(registryConfig)
				.reference(referenceConfig)
				.start();

		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		GenericService genericService = cache.get(referenceConfig);

		Object result = genericService.$invoke(
				"sayHi",
				new String[] {"java.lang.String"},
				new Object[] {"yuluo"}
		);

		System.out.println(result);

	}


}
