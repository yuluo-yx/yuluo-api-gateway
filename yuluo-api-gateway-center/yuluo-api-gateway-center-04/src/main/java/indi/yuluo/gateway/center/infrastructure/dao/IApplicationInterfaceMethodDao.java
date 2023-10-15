package indi.yuluo.gateway.center.infrastructure.dao;

import java.util.List;

import indi.yuluo.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Mapper
public interface IApplicationInterfaceMethodDao {

	void insert(ApplicationInterfaceMethod applicationInterfaceMethod);

	List<ApplicationInterfaceMethod> queryApplicationInterfaceMethodList(ApplicationInterfaceMethod req);

}
