package indi.yuluo.gateway.datatsource.unpooled;

import indi.yuluo.gateway.datatsource.Connection;
import indi.yuluo.gateway.datatsource.DataSource;
import indi.yuluo.gateway.datatsource.DataSourceType;
import indi.yuluo.gateway.datatsource.connection.DubboConnection;
import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.Configuration;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 通过无池化的连接池对 BuddoConnection，HTTPConnection 进行管理即用
 * 最后是通过 UnpooledDataSourceFactory 无池化的数据源工厂进行构建和获取
 */

public class UnpooledDataSource implements DataSource {

	private Configuration configuration;

	private HttpStatement httpStatement;

	private DataSourceType dataSourceType;

	@Override
	public Connection getConnection() {

		switch (dataSourceType) {
			case HTTP:
				// TODO: 预留接口，暂时不需要实现
				break;
			case Dubbo:

				System.out.println("---------------------------------------------------------------------------");
				System.out.println("UnpoolDataSource#getConnection().HttpStatement: " + httpStatement);
				System.out.println("---------------------------------------------------------------------------");

				// 配置信息
				String application = httpStatement.getApplication();
				String interfaceName = httpStatement.getInterfaceName();
				// 获取服务
				ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
				RegistryConfig registryConfig = configuration.getRegistryConfig(application);
				ReferenceConfig<GenericService> reference = configuration.getReferenceConfig(interfaceName);

				return new DubboConnection(applicationConfig, registryConfig, reference);
			default:
				break;
		}
		throw new RuntimeException("DataSourceType: " + dataSourceType + " 没有对应的数据源实现！");
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public	void setHttpStatement(HttpStatement httpStatement) {
		this.httpStatement = httpStatement;
	}

	public void setDataSourceType(DataSourceType dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
}
