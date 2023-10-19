package indi.yuluo.gateway.center.application;

import java.util.List;

import indi.yuluo.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerVO;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IConfigManageService {

	List<GatewayServerVO> queryGatewayServerList();

	boolean registryGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress);

	ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId);

	String queryGatewayDistribution(String systemId);

}

