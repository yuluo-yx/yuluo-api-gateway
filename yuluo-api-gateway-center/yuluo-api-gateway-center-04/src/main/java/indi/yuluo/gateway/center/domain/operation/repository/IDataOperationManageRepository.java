package indi.yuluo.gateway.center.domain.operation.repository;

import java.util.List;

import indi.yuluo.gateway.center.domain.operation.model.vo.ApplicationInterfaceDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.ApplicationInterfaceMethodDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.ApplicationSystemDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.GatewayDistributionDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.GatewayServerDataVO;
import indi.yuluo.gateway.center.domain.operation.model.vo.GatewayServerDetaiDatalVO;
import indi.yuluo.gateway.center.infrastructure.common.OperationRequest;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IDataOperationManageRepository {

	List<GatewayServerDataVO> queryGatewayServerListByPage(OperationRequest<String> request);

	int queryGatewayServerListCountByPage(OperationRequest<String> request);

	List<ApplicationSystemDataVO> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request);

	int queryApplicationSystemListCountByPage(OperationRequest<ApplicationSystemDataVO> request);

	List<ApplicationInterfaceDataVO> queryApplicationInterfaceListByPage(OperationRequest<ApplicationInterfaceDataVO> request);

	int queryApplicationInterfaceListCountByPage(OperationRequest<ApplicationInterfaceDataVO> request);

	List<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethodListByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);

	int queryApplicationInterfaceMethodListCountByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);

	List<GatewayServerDetaiDatalVO> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

	int queryGatewayServerDetailListCountByPage(OperationRequest<GatewayServerDetaiDatalVO> request);

	List<GatewayDistributionDataVO> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);

	int queryGatewayDistributionListCountByPage(OperationRequest<GatewayDistributionDataVO> request);

}

