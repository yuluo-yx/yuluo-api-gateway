package indi.yuluo.gateway.center.application;

import indi.yuluo.gateway.center.domain.operation.model.vo.ApplicationInterfaceDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.ApplicationInterfaceMethodDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.ApplicationSystemDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.GatewayDistributionDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.GatewayServerDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.GatewayServerDetaiDatalVO;
import indi.yuluo.gateway.center.infrastructure.common.OperationRequest;
import indi.yuluo.gateway.center.infrastructure.common.OperationResult;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IDataOperationManageService {

	OperationResult<GatewayServerDataVO> queryGatewayServer(OperationRequest<String> request);

	OperationResult<ApplicationSystemDataVO> queryApplicationSystem(OperationRequest<ApplicationSystemDataVO> request);

	OperationResult<ApplicationInterfaceDataVO> queryApplicationInterface(OperationRequest<ApplicationInterfaceDataVO> request);

	OperationResult<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethod(OperationRequest<ApplicationInterfaceMethodDataVO> request);

	OperationResult<GatewayServerDetaiDatalVO> queryGatewayServerDetail(OperationRequest<GatewayServerDetaiDatalVO> request);

	OperationResult<GatewayDistributionDataVO> queryGatewayDistribution(OperationRequest<GatewayDistributionDataVO> request);

}

