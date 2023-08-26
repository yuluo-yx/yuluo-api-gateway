package indi.yuluo.gateway.session.defaults;

import indi.yuluo.gateway.bind.IGenericReference;
import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.GatewaySession;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class DefaultGatewaySession implements GatewaySession {

	private final Configuration configuration;

	public DefaultGatewaySession(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public Object get(String uri, Object parameter) {

		/*
		以下这部分内容，后续拆到执行器中处理
		 */

		// 配置信息
		HttpStatement httpStatement = configuration.getHttpStatement(uri);
		String application = httpStatement.getApplication();
		String interfaceName = httpStatement.getInterfaceName();

		// 获取基础服务（创建成本较高，内存存放获取）
		ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
		RegistryConfig registryConfig = configuration.getRegistryConfig(application);
		ReferenceConfig<GenericService> reference = configuration.getReferenceConfig(interfaceName);

		// 构建 Dubbo 服务
		DubboBootstrap bootstrap = DubboBootstrap.getInstance();
		bootstrap
				.application(applicationConfig)
				.reference(reference)
				.registry(registryConfig)
				.start();

		// 获取泛化调用服务
		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		GenericService genericService = cache.get(reference);

		return genericService.$invoke(
				httpStatement.getMethodName(),
				new String[]{"java.lang.String"},
				new Object[]{"yuluo"}
		);
	}

	@Override
	public IGenericReference getMapper(String uri) {

		return configuration.getMapper(uri, this);
	}

	@Override
	public Configuration getConfiguration() {

		return configuration;
	}

}
