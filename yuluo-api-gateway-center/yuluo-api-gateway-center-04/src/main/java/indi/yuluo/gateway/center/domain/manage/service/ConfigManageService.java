package indi.yuluo.gateway.center.domain.manage.service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.application.IConfigManageService;
import indi.yuluo.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationInterfaceMethodVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationInterfaceVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerVO;
import indi.yuluo.gateway.center.domain.manage.repository.IConfigManageRepository;
import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationSystemVO;
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

	@Override
	public ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId) {

		ApplicationSystemRichInfo applicationSystemRichInfo = new ApplicationSystemRichInfo();
		applicationSystemRichInfo.setGatewayId(gatewayId);

		// 1. 查询出网关 ID 对应的关联系统 ID 集合，也就是一个网关 ID 会被分配一些系统的 RPC 服务注册进来，需要将这些服务查询出来
		List<String> systemIdList = configManageRepository.queryGatewayDistributionSystemIdList(gatewayId);

		if (systemIdList.size() != 0 || null == systemIdList) {
			return applicationSystemRichInfo;
		}

		System.out.println("输出 SystemIdList 属性：" + systemIdList);

		// 2. 查询系统 ID 对应的的系统列表信息
		List<ApplicationSystemVO> applicationSystemVOList =
				configManageRepository.queryApplicationSystemList(systemIdList);

		// 3. 查询系统下的接口信息
		// 这里的查询是一个不断循环的查询，是否可以优化下，减少查询次数
		for (ApplicationSystemVO applicationSystemVO : applicationSystemVOList) {
			List<ApplicationInterfaceVO> applicationInterfaceVOList =
					configManageRepository.queryApplicationInterfaceList(applicationSystemVO.getSystemId());
			for (ApplicationInterfaceVO applicationInterfaceVO : applicationInterfaceVOList) {
				List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList =
						configManageRepository.queryApplicationInterfaceMethodList(applicationSystemVO.getSystemId(), applicationInterfaceVO.getInterfaceId());
				applicationInterfaceVO.setMethodList(applicationInterfaceMethodVOList);
			}
			applicationSystemVO.setInterfaceList(applicationInterfaceVOList);
		}

		return new ApplicationSystemRichInfo(gatewayId, applicationSystemVOList);
	}


	@Override
	public String queryGatewayDistribution(String systemId) {
		return null;
	}
}
