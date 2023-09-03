package indi.yuluo.gateway.datatsource;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 将 DUBBO/GRPC/HTTP 抽象为数据源，数据源接口中的方法为获取相关 RPC 连接。
 */

public interface DataSource {

	Connection getConnection();

}
