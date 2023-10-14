package indi.yuluo.gateway.bind;

import java.util.HashMap;
import java.util.Map;

import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.GatewaySession;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */
public class MapperRegistry {

	private final Configuration configuration;

	public MapperRegistry(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * 泛化调用静态工厂
	 */
	private final Map<String, MapperProxyFactory> knownMappers = new HashMap<>();

	public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {

		final MapperProxyFactory mapperProxyFactory = knownMappers.get(uri);

		if (mapperProxyFactory == null) {
			throw new RuntimeException("Uri " + uri + " is not known to thw MapperRegistry.");
		}

		try {
			return mapperProxyFactory.newInstance(gatewaySession);
		} catch (Exception e) {
			throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
		}
	}

	public void addMapper(HttpStatement httpStatement) {

		String uri = httpStatement.getUri();

		// 如果重复注册则返回
		if (hasMapper(uri)) {
			return;
		}

		knownMappers.put(uri, new MapperProxyFactory(uri));
		// 保存接口映射信息
		configuration.addHttpStatement(httpStatement);
	}

	public <T> boolean hasMapper(String uri) {
		return knownMappers.containsKey(uri);
	}


}
