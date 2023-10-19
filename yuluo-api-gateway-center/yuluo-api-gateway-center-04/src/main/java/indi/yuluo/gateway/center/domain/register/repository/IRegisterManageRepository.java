package indi.yuluo.gateway.center.domain.register.repository;

import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationInterfaceMethodVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationInterfaceVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationSystemVO;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IRegisterManageRepository {

	void registerApplication(ApplicationSystemVO applicationSystemVO);

	void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

	void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}
