package indi.yuluo.gateway.datatsource.connection;

import indi.yuluo.gateway.datatsource.Connection;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 提取出 Dubbo 泛化调用相关初始化和执行提取到连接的接口实现中。
 */

public class DubboConnection implements Connection {

	private final GenericService genericService;

	public DubboConnection(
			ApplicationConfig applicationConfig,
			RegistryConfig registryConfig,
			ReferenceConfig<GenericService> reference
	) {

		// 连接远程服务
		DubboBootstrap bootstrap = DubboBootstrap.getInstance();
		bootstrap.application(applicationConfig)
				.registry(registryConfig)
				.reference(reference)
				.start();

		// 获取泛化接口调用服务
		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		genericService = cache.get(reference);
	}

	/**
	 * Dubbo 泛化调用执行
	 * @param method 			方法名
	 * @param parameterTypes 	参数类型
	 * @param parameterNames 	参数名
	 * @param args 				参数值
	 */
	@Override
	public Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args) {

		return genericService.$invoke(method, parameterTypes, args);
	}

}
