package indi.yuluo.gateway.center.infrastructure.dao;

import java.util.List;

import indi.yuluo.gateway.center.domain.operation.model.vo.GatewayServerDetaiDatalVO;
import indi.yuluo.gateway.center.infrastructure.common.OperationRequest;
import indi.yuluo.gateway.center.infrastructure.po.GatewayServerDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Mapper
public interface IGatewayServerDetailDao {

	void insert(GatewayServerDetail gatewayServerDetail);

	GatewayServerDetail queryGatewayServerDetail(GatewayServerDetail gatewayServerDetail);

	boolean updateGatewayStatus(GatewayServerDetail gatewayServerDetail);

	List<GatewayServerDetail> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

	int queryGatewayServerDetailListCountByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

}
