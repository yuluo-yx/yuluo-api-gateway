package indi.yuluo.gateway.center.infrastructure.dao;

import java.util.List;

import indi.yuluo.gateway.center.domain.operation.model.vo.ApplicationInterfaceDataVO;
import indi.yuluo.gateway.center.infrastructure.common.OperationRequest;
import indi.yuluo.gateway.center.infrastructure.po.ApplicationInterface;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Mapper
public interface IApplicationInterfaceDao {

	void insert(ApplicationInterface applicationInterface);

	List<ApplicationInterface> queryApplicationInterfaceList(String systemId);

	List<ApplicationInterface> queryApplicationInterfaceListByPage(OperationRequest<ApplicationInterfaceDataVO> request);

	int queryApplicationInterfaceListCountByPage(OperationRequest<ApplicationInterfaceDataVO> request);

}
