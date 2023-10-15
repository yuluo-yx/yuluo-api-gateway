package indi.yuluo.gateway.center.application;

import indi.yuluo.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import indi.yuluo.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import indi.yuluo.gateway.center.domain.register.model.vo.ApplicationSystemVO;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 接口注册服务
 */

public interface IRegisterManageService {

	void registerApplication(ApplicationSystemVO applicationSystemVO);

	void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

	void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

	void hello(String s);

}

