package indi.yuluo.gateway.session;

import java.util.HashMap;
import java.util.Map;

import indi.yuluo.gateway.authorization.IAuth;
import indi.yuluo.gateway.authorization.auth.AuthService;
import indi.yuluo.gateway.bind.IGenericReference;
import indi.yuluo.gateway.bind.MapperRegistry;
import indi.yuluo.gateway.datatsource.Connection;
import indi.yuluo.gateway.executor.Executor;
import indi.yuluo.gateway.executor.SimpleExecutor;
import indi.yuluo.gateway.mapping.HttpStatement;
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

	private final Map<String, HttpStatement> httpStatements = new HashMap<>();

	private final MapperRegistry mapperRegistry = new MapperRegistry(this);

	private final IAuth auth = new AuthService();

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

	// 网关 Netty 服务地址
	private String hostName = "127.0.0.1";

	// 网关 Netty 服务端口

	private int port = 7397;

	// 网关 Netty 服务线程数配置
	private int bossNThreads = 1;

	private int workNThreads = 4;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getBossNThreads() {
		return bossNThreads;
	}

	public void setBossNThreads(int bossNThreads) {
		this.bossNThreads = bossNThreads;
	}

	public int getWorkNThreads() {
		return workNThreads;
	}

	public void setWorkNThreads(int workNThreads) {
		this.workNThreads = workNThreads;
	}

	public Configuration() {

//		// TODO: 后期从配置中获取
//		ApplicationConfig application = new ApplicationConfig();
//		application.setName("yuluo-api-gateway-test");
//		application.setQosEnable(false);
//
//		RegistryConfig registry = new RegistryConfig();
//		registry.setAddress("zookeeper://127.0.0.1:2181");
//		registry.setRegister(false);
//
//		ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
//		reference.setInterface("indi.yuluo.gateway.rpc.IActivityBooth");
//		reference.setVersion("1.0.0");
//		reference.setGeneric("true");
//
//		applicationConfigMap.put("yuluo-api-gateway-test", application);
//		registryConfigMap.put("yuluo-api-gateway-test", registry);
//		referenceConfigMap.put("indi.yuluo.gateway.rpc.IActivityBooth", reference);

	}

	public synchronized void registryConfig(String applicationName, String address, String interfaceName,
			String version){
		if (applicationConfigMap.get(applicationName) == null) {
			ApplicationConfig application = new ApplicationConfig();
			application.setName(applicationName);
			application.setQosEnable(false);
			applicationConfigMap.put(applicationName, application);
		}
		if (registryConfigMap.get(applicationName) == null) {
			RegistryConfig registry = new RegistryConfig();
			registry.setAddress(address);
			registry.setRegister(false);
			registryConfigMap.put(applicationName, registry);
		}
		if (referenceConfigMap.get(interfaceName) == null) {
			ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
			reference.setInterface(interfaceName);
			reference.setVersion(version);
			reference.setGeneric("true");
			referenceConfigMap.put(interfaceName, reference);
		}
	}


	public void addMapper(HttpStatement httpStatement) {

		mapperRegistry.addMapper(httpStatement);
	}

	public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {

		return mapperRegistry.getMapper(uri, gatewaySession);
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

	public void addHttpStatement(HttpStatement httpStatement) {

		httpStatements.put(httpStatement.getUri(), httpStatement);
	}

	public HttpStatement getHttpStatement(String uri) {


		return httpStatements.get(uri);
	}

	public Executor newExecutor(Connection connection) {

		return new SimpleExecutor(this, connection);
	}

	/**
	 * 鉴权操作
	 */
	public boolean authValidate(String uId, String token) {

		return auth.validate(uId, token);
	}

}
