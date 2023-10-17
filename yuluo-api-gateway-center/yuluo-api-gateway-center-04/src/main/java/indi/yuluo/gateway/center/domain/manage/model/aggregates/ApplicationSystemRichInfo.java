package indi.yuluo.gateway.center.domain.manage.model.aggregates;

import java.util.List;

import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationSystemVO;

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

	public ApplicationSystemRichInfo() {
	}

	public ApplicationSystemRichInfo(String gatewayId, List<ApplicationSystemVO> applicationSystemVOList) {
		this.gatewayId = gatewayId;
		this.applicationSystemVOList = applicationSystemVOList;
	}

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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ApplicationSystemRichInfo{");
		sb.append("gatewayId='").append(gatewayId).append('\'');
		sb.append(", applicationSystemVOList=").append(applicationSystemVOList);
		sb.append('}');
		return sb.toString();
	}
}

