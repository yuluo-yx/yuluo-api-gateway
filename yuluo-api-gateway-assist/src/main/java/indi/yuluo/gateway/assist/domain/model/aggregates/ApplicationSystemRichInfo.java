package indi.yuluo.gateway.assist.domain.model.aggregates;

import java.util.List;

import indi.yuluo.gateway.assist.domain.model.vo.ApplicationSystemVO;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 网关算力配置信息
 */

public class ApplicationSystemRichInfo {

	/** 网关ID */
	private String gatewayId;

	/** 系统列表 */
	private List<ApplicationSystemVO> applicationSystemVOList;

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public List<ApplicationSystemVO> getApplicationSystemVOList() {
		return applicationSystemVOList;
	}

	public void setApplicationSystemVOList(List<ApplicationSystemVO> applicationSystemVOList) {
		this.applicationSystemVOList = applicationSystemVOList;
	}

}

