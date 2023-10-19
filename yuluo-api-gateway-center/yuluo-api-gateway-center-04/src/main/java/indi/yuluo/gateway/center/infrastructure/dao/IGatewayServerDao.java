package indi.yuluo.gateway.center.infrastructure.dao;

import java.util.List;

import indi.yuluo.gateway.center.infrastructure.common.OperationRequest;
import indi.yuluo.gateway.center.infrastructure.po.GatewayServer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Mapper
public interface IGatewayServerDao {

	List<GatewayServer> queryGatewayServerList();

	List<GatewayServer> queryGatewayServerListByPage(OperationRequest<String> request);

	int queryGatewayServerListCountByPage(OperationRequest<String> request);

}
