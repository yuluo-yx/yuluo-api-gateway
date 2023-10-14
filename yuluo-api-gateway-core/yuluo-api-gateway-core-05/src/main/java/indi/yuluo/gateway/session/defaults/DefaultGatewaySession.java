package indi.yuluo.gateway.session.defaults;

import java.util.Map;

import indi.yuluo.gateway.bind.IGenericReference;
import indi.yuluo.gateway.datatsource.Connection;
import indi.yuluo.gateway.datatsource.DataSource;
import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.GatewaySession;
import indi.yuluo.gateway.type.SimpleTypeRegistry;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class DefaultGatewaySession implements GatewaySession {

	private Configuration configuration;
	private String uri;
	private DataSource dataSource;

	public DefaultGatewaySession(Configuration configuration, String uri, DataSource dataSource) {
		this.configuration = configuration;
		this.uri = uri;
		this.dataSource = dataSource;
	}

	/**
	 * 调用数据源进行 rpc 执行处理.
	 */
	@Override
	public Object get(String methodName, Map<String, Object> params) {

		Connection connection = dataSource.getConnection();
		HttpStatement httpStatement = configuration.getHttpStatement(uri);
		String parameterType = httpStatement.getParameterType();

		/**
		 * 调用服务
		 * 封装参数：PS：为什么这样构建参数，参考测试 case ： indi.yuluo.gateway.test.RPCTest
		 * 01（允许）：java.lang.String
		 * 02（允许）：indi.yuluo.gateway.rpc.dto.XReq
		 * 03（拒绝）：java.lang.String, indi.yuluo.gateway.rpc.dto.XReq 不提供多个方法参数的处理
		 */
		return connection.execute(
				methodName,
				new String[]{parameterType},
				new String[]{"ignore"},
				SimpleTypeRegistry.isSimpleType(parameterType) ?
						params.values().toArray(new Object[0]) :
						new Object[]{params}
		);
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
