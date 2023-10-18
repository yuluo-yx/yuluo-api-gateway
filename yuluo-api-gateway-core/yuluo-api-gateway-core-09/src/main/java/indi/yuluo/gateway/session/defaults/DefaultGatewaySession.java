package indi.yuluo.gateway.session.defaults;

import java.util.Map;

import indi.yuluo.gateway.bind.IGenericReference;
import indi.yuluo.gateway.datatsource.DataSource;
import indi.yuluo.gateway.executor.Executor;
import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.GatewaySession;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class DefaultGatewaySession implements GatewaySession {

	private Configuration configuration;
	private String uri;
	private DataSource dataSource;

	private Executor executor;

	public DefaultGatewaySession(Configuration configuration, String uri,  Executor executor) {
		this.configuration = configuration;
		this.uri = uri;
		this.executor = executor;
	}

	/**
	 * 调用数据源进行 rpc 执行处理.
	 */
	@Override
	public Object get(String methodName, Map<String, Object> params) {

		HttpStatement httpStatement = configuration.getHttpStatement(uri);

		try {
			return executor.exec(httpStatement, params);
		} catch (Exception e) {
			throw new RuntimeException("Error exec get. Cause: " + e);
		}
	}

	@Override
	public Object post(String methodName, Map<String, Object> params) {

		return get(methodName, params);
	}

	@Override
	public IGenericReference getMapper() {

		return configuration.getMapper(uri, this);
	}

	@Override
	public Configuration getConfiguration() {

		return configuration;
	}

}
