package indi.yuluo.gateway.datatsource.unpooled;

import indi.yuluo.gateway.datatsource.DataSource;
import indi.yuluo.gateway.datatsource.DataSourceFactory;
import indi.yuluo.gateway.datatsource.DataSourceType;
import indi.yuluo.gateway.session.Configuration;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 无池化数据源工厂
 */

public class UnpooledDataSourceFactory implements DataSourceFactory {

	protected UnpooledDataSource dataSource;

	public UnpooledDataSourceFactory() {

		this.dataSource = new UnpooledDataSource();
	}

	@Override
	public void setProperties(Configuration configuration, String uri) {

		this.dataSource.setConfiguration(configuration);
		this.dataSource.setDataSourceType(DataSourceType.Dubbo);
		this.dataSource.setHttpStatement(configuration.getHttpStatement(uri));
	}

	@Override
	public DataSource getDataSource() {

		return dataSource;
	}

}
