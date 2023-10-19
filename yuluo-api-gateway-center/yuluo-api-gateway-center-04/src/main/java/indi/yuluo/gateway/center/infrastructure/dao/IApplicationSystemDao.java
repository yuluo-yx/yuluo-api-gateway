package indi.yuluo.gateway.center.infrastructure.dao;

import java.util.List;

import indi.yuluo.gateway.center.domain.operation.model.vo.ApplicationSystemDataVO;
import indi.yuluo.gateway.center.infrastructure.common.OperationRequest;
import indi.yuluo.gateway.center.infrastructure.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Mapper
public interface IApplicationSystemDao {

	void insert(ApplicationSystem applicationSystem);

	List<ApplicationSystem> queryApplicationSystemList(List<String> list);
	
	List<ApplicationSystem> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request);

	int queryApplicationSystemListCountByPage(OperationRequest<ApplicationSystemDataVO> request);
}
