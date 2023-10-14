package indi.yuluo.gateway.datatsource;

import indi.yuluo.gateway.session.Configuration;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface DataSourceFactory {

	void setProperties(Configuration configuration, String uri);

	DataSource getDataSource();

}
