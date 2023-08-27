package indi.yuluo.gateway.session.defaults;

import indi.yuluo.gateway.bind.IGenericReference;
import indi.yuluo.gateway.datatsource.Connection;
import indi.yuluo.gateway.datatsource.DataSource;
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

	public DefaultGatewaySession(Configuration configuration, String uri, DataSource dataSource) {
		this.configuration = configuration;
		this.uri = uri;
		this.dataSource = dataSource;
	}

	/**
	 * 调用数据源进行 rpc 执行处理.
	 */
	@Override
	public Object get(String methodName, Object parameter) {

		/*
		需要在执行器中再次封装，封装返回结果成为标准信息！
		 */

		Connection connection = dataSource.getConnection();

		return connection.execute(
				methodName,
				new String[]{"java.lang.String"},
				new String[]{"name"},
				new Object[]{parameter}
		);
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
