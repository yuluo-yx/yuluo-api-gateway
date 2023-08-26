package indi.yuluo.gateway.session;

import java.util.HashMap;
import java.util.Map;

import indi.yuluo.gateway.bind.GenericReferenceRegistry;
import indi.yuluo.gateway.bind.IGenericReference;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 用于贯穿整个会话周期，所以本章节添加了配置项用于存放代理对象，服务配置等信息
 */

public class Configuration {

	 private final GenericReferenceRegistry registry = new GenericReferenceRegistry(this);

	/**
	 * RPC 应用服务配置项
	 */
	private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();

	/**
	 * RPC 注册中心配置项 zookeeper:127.0.0.1:2181
	 */
	private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();

	/**
	 * RPC 泛化调用服务配置项 indi.yuluo.gateway.rpc.IActivityBooth
	 */
	private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();

	public Configuration() {

		// TODO: 后期从配置中获取
		ApplicationConfig application = new ApplicationConfig();
		application.setName("yuluo-api-gateway-test");
		application.setQosEnable(false);

		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://127.0.0.1:2181");
		registry.setRegister(false);

		ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
		reference.setInterface("indi.yuluo.gateway.rpc.IActivityBooth");
		reference.setVersion("1.0.0");
		reference.setGeneric("true");

		applicationConfigMap.put("yuluo-api-gateway-test", application);
		registryConfigMap.put("yuluo-api-gateway-test", registry);
		referenceConfigMap.put("indi.yuluo.gateway.rpc.IActivityBooth", reference);
	}

	public ApplicationConfig getApplicationConfig(String applicationName) {

		return applicationConfigMap.get(applicationName);
	}

	public RegistryConfig getRegistryConfig(String applicationName) {

		return registryConfigMap.get(applicationName);
	}

	public ReferenceConfig<GenericService> getReferenceConfig(String interfaceName) {

		return referenceConfigMap.get(interfaceName);
	}

	public void addGenericReference(String application, String interfaceName, String methodName) {

		registry.addGenericReference(application, interfaceName, methodName);
	}

	public IGenericReference getGenericReference(String methodName) {

		return registry.getGenericReference(methodName);
	}

}
