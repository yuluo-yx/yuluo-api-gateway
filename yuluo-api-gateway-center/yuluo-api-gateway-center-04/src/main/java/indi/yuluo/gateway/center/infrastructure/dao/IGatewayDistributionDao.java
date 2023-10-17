package indi.yuluo.gateway.center.infrastructure.dao;

import java.util.List;

import indi.yuluo.gateway.center.domain.operation.model.vo.GatewayDistributionDataVO;
import indi.yuluo.gateway.center.infrastructure.common.OperationRequest;
import indi.yuluo.gateway.center.infrastructure.po.GatewayDistribution;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Mapper
public interface IGatewayDistributionDao {

	List<String> queryGatewayDistributionSystemIdList();

	String queryGatewayDistribution(String systemId);

	List<GatewayDistribution> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);

	int queryGatewayDistributionListCountByPage(OperationRequest<GatewayDistributionDataVO> request);

}
