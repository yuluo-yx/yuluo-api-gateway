package indi.yuluo.gateway.center.interfaces;

import java.util.List;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.application.IConfigManageService;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerVO;
import indi.yuluo.gateway.center.infrastructure.common.ResponseCode;
import indi.yuluo.gateway.center.infrastructure.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 网关接口服务
 */

@RestController
@RequestMapping("/api")
public class GatewayConfigManage {

	private static final Logger logger = LoggerFactory.getLogger(GatewayConfigManage.class);

	@Resource
	private IConfigManageService configManageService;

	@GetMapping(value = "queryServerConfig", produces = "application/json;charset=utf-8")
	public Result<List<GatewayServerVO>> queryServerConfig() {
		try {
			logger.info("查询网关服务配置项信息");
			List<GatewayServerVO> gatewayServerVOList = configManageService.queryGatewayServerList();
			return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOList);
		}
		catch (Exception e) {
			logger.error("查询网关服务配置项信息异常");
			return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
		}
	}

	/**
	 * 注册网关服务节点
	 * @param groupId			注册组
	 * @param gatewayId			网关id
	 * @param gatewayName		网关name
	 * @param gatewayAddress	网关地址
	 * @return					注册是否成功
	 */
	@PostMapping(value = "registryGateway")
	public Result<Boolean> registryGatewayServerNode(
			@RequestParam String groupId,
			@RequestParam String gatewayId,
			@RequestParam String gatewayName,
			@RequestParam String gatewayAddress
	) {
		try {

			logger.info("注册网关服务节点 gatewayId: {}, gatewayName: {}, gatewayAddress: {}",
					gatewayId, gatewayName, gatewayAddress);

			boolean done = configManageService.registryGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress);
			return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), done);

		} catch (Exception e) {

			logger.error("注册网关服务节点异常", e);
			return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
		}
	}

}
