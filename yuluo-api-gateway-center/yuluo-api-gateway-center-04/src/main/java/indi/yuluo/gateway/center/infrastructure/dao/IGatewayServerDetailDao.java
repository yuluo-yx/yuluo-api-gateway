package indi.yuluo.gateway.center.infrastructure.dao;

import java.util.List;

import indi.yuluo.gateway.center.infrastructure.po.GatewayServerDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Mapper
public interface IGatewayServerDetailDao {

	void insert(GatewayServerDetail gatewayServerDetail);

	GatewayServerDetail queryGatewayServerDetail(GatewayServerDetail gatewayServerDetail);

	List<GatewayServerDetail> queryGatewayServerDetailList();

	boolean updateGatewayStatus(GatewayServerDetail gatewayServerDetail);

}
