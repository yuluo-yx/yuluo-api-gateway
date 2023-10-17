package indi.yuluo.gateway.center.domain.manage.repository;

import java.util.List;

import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationInterfaceMethodVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationInterfaceVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationSystemVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerVO;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IConfigManageRepository {

	List<GatewayServerVO> queryGatewayServerList();

	boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer available);

	GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress);

	List<GatewayServerDetailVO> queryGatewayServerDetailList();

	boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available);

	List<String> queryGatewayDistributionSystemIdList(String gatewayId);

	List<ApplicationSystemVO> queryApplicationSystemList(List<String> systemIdList);

	List<ApplicationInterfaceVO> queryApplicationInterfaceList(String systemId);

	List<ApplicationInterfaceMethodVO> queryApplicationInterfaceMethodList(String systemId, String interfaceId);

	String queryGatewayDistribution(String systemId);


}
