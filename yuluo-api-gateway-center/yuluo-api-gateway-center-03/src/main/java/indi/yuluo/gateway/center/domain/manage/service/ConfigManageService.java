package indi.yuluo.gateway.center.domain.manage.service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.application.IConfigManageService;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerVO;
import indi.yuluo.gateway.center.domain.manage.repository.IConfigManageRepository;
import indi.yuluo.gateway.center.infrastructure.common.Constants;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Service
public class ConfigManageService implements IConfigManageService {

	@Resource
	private IConfigManageRepository configManageRepository;

	@Override
	public List<GatewayServerVO> queryGatewayServerList() {

		return configManageRepository.queryGatewayServerList();
	}

	@Override
	public List<GatewayServerDetailVO> queryGatewayServerDetailList() {

		return configManageRepository.queryGatewayServerDetailList();
	}

	@Override
	public boolean registryGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress) {

		GatewayServerDetailVO gatewayServerDetailVO = configManageRepository.queryGatewayServerDetail(gatewayId, gatewayAddress);

		if (Objects.isNull(gatewayServerDetailVO)) {
			try {
				return configManageRepository.registerGatewayServerNode(
						groupId,
						gatewayId,
						gatewayName,
						gatewayAddress,
						Constants.GatewayStatus.Available
				);
			} catch (DuplicateKeyException e) {
				// 异常处理，可以扩展报警，日志提示等
				return configManageRepository.updateGatewayStatus(
						gatewayId,
						gatewayAddress,
						Constants.GatewayStatus.Available
				);
			}
		} else {
			return configManageRepository.updateGatewayStatus(
					gatewayId,
					gatewayAddress,
					Constants.GatewayStatus.Available
			);
		}
	}
}
