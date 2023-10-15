package indi.yuluo.gateway.center.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerVO;
import indi.yuluo.gateway.center.domain.manage.repository.IConfigManageRepository;
import indi.yuluo.gateway.center.infrastructure.dao.IGatewayServerDao;
import indi.yuluo.gateway.center.infrastructure.dao.IGatewayServerDetailDao;
import indi.yuluo.gateway.center.infrastructure.po.GatewayServer;
import indi.yuluo.gateway.center.infrastructure.po.GatewayServerDetail;

import org.springframework.stereotype.Component;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Component
public class ConfigManagerRepository implements IConfigManageRepository {

	@Resource
	private IGatewayServerDao gatewayServerDao;

	@Resource
	private IGatewayServerDetailDao gatewayServerDetailDao;

	@Override
	public List<GatewayServerVO> queryGatewayServerList() {

		List<GatewayServer> gatewayServers = gatewayServerDao.queryGatewayServerList();
		List<GatewayServerVO> gatewayServerVOList = new ArrayList<>(gatewayServers.size());

		for (GatewayServer gatewayServer : gatewayServers) {
			// 可以按照 IDEA 插件 vo2dto 方便转换
			GatewayServerVO gatewayServerVO = new GatewayServerVO();
			gatewayServerVO.setGroupId(gatewayServer.getGroupId());
			gatewayServerVO.setGroupName(gatewayServer.getGroupName());
			gatewayServerVOList.add(gatewayServerVO);
		}

		return gatewayServerVOList;
	}


	@Override
	public boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer status) {

		GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
		gatewayServerDetail.setGroupId(groupId);
		gatewayServerDetail.setGatewayId(gatewayId);
		gatewayServerDetail.setGatewayName(gatewayName);
		gatewayServerDetail.setGatewayAddress(gatewayAddress);
		gatewayServerDetail.setStatus(status);

		gatewayServerDetailDao.insert(gatewayServerDetail);

		return true;
	}

	@Override
	public GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress) {
		GatewayServerDetail req = new GatewayServerDetail();
		req.setGatewayId(gatewayId);
		req.setGatewayAddress(gatewayAddress);
		GatewayServerDetail gatewayServerDetail = gatewayServerDetailDao.queryGatewayServerDetail(req);
		if (null == gatewayServerDetail) return null;

		// 可以按照 IDEA 插件 vo2dto 方便转换
		GatewayServerDetailVO gatewayServerDetailVO = new GatewayServerDetailVO();
		gatewayServerDetailVO.setGatewayId(gatewayServerDetail.getGatewayId());
		gatewayServerDetailVO.setGatewayName(gatewayServerDetail.getGatewayName());
		gatewayServerDetailVO.setGatewayAddress(gatewayServerDetail.getGatewayAddress());
		gatewayServerDetailVO.setStatus(gatewayServerDetail.getStatus());

		return gatewayServerDetailVO;
	}

	@Override
	public List<GatewayServerDetailVO> queryGatewayServerDetailList() {

		List<GatewayServerDetail> gatewayServerDetails = gatewayServerDetailDao.queryGatewayServerDetailList();
		List<GatewayServerDetailVO> gatewayServerDetailVOS = new ArrayList<>();

		for (GatewayServerDetail gatewayServerDetail : gatewayServerDetails) {

			// 可以安装 IDEA 插件 vo2dto 方便转换
			GatewayServerDetailVO gatewayServerDetailVO = new GatewayServerDetailVO();
			gatewayServerDetailVO.setGatewayAddress(gatewayServerDetail.getGatewayAddress());
			gatewayServerDetailVO.setGatewayId(gatewayServerDetail.getGatewayId());
			gatewayServerDetail.setGatewayName(gatewayServerDetail.getGatewayName());
			gatewayServerDetail.setStatus(gatewayServerDetail.getStatus());

			gatewayServerDetailVOS.add(gatewayServerDetailVO);
		}

		return gatewayServerDetailVOS;
	}

	@Override
	public boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available) {

		GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
		gatewayServerDetail.setGatewayId(gatewayId);
		gatewayServerDetail.setGatewayAddress(gatewayAddress);
		gatewayServerDetail.setStatus(available);

		return gatewayServerDetailDao.updateGatewayStatus(gatewayServerDetail);
	}

}
