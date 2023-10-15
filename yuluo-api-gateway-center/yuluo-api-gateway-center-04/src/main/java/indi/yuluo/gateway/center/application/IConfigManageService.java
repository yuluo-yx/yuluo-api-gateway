package indi.yuluo.gateway.center.application;

import java.util.List;

import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerVO;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IConfigManageService {

	List<GatewayServerVO> queryGatewayServerList();

	List<GatewayServerDetailVO> queryGatewayServerDetailList();

	boolean registryGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress);

}

