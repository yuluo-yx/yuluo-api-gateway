package indi.yuluo.gateway.session.defaults;

import indi.yuluo.gateway.datatsource.DataSource;
import indi.yuluo.gateway.datatsource.DataSourceFactory;
import indi.yuluo.gateway.datatsource.unpooled.UnpooledDataSourceFactory;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.GatewaySession;
import indi.yuluo.gateway.session.GatewaySessionFactory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 泛化调用会话工厂
 */

public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

	private final Configuration configuration;

	public DefaultGatewaySessionFactory(Configuration configuration) {

		this.configuration = configuration;
	}

	/**
	 * 通过 DataSourceFactory 构建数据源的服务.
	 */
	@Override
	public GatewaySession openSession(String uri) {

		// 获取数据源连接信息：这里把 Dubbo、HTTP 抽象为一种连接资源
		DataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();
		dataSourceFactory.setProperties(configuration, uri);
		DataSource dataSource = dataSourceFactory.getDataSource();

		// 创建会话
		return new DefaultGatewaySession(configuration, uri, dataSource);
	}

	public Configuration getConfiguration() {

		return configuration;
	}
}
