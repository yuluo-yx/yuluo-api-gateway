package indi.yuluo.gateway.center.infrastructure.repository;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationInterfaceMethodVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationInterfaceVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.ApplicationSystemVO;
import indi.yuluo.gateway.center.domain.register.repository.IRegisterManageRepository;
import indi.yuluo.gateway.center.infrastructure.dao.IApplicationInterfaceDao;
import indi.yuluo.gateway.center.infrastructure.dao.IApplicationInterfaceMethodDao;
import indi.yuluo.gateway.center.infrastructure.dao.IApplicationSystemDao;
import indi.yuluo.gateway.center.infrastructure.po.ApplicationInterface;
import indi.yuluo.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import indi.yuluo.gateway.center.infrastructure.po.ApplicationSystem;

import org.springframework.stereotype.Component;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Component
public class RegisterManagerRepository implements IRegisterManageRepository {
	@Resource
	private IApplicationSystemDao applicationSystemDao;
	@Resource
	private IApplicationInterfaceDao applicationInterfaceDao;
	@Resource
	private IApplicationInterfaceMethodDao applicationInterfaceMethodDao;

	@Override
	public void registerApplication(ApplicationSystemVO applicationSystemVO) {
		ApplicationSystem applicationSystem = new ApplicationSystem();
		applicationSystem.setSystemId(applicationSystemVO.getSystemId());
		applicationSystem.setSystemName(applicationSystemVO.getSystemName());
		applicationSystem.setSystemType(applicationSystemVO.getSystemType());
		applicationSystem.setSystemRegistry(applicationSystemVO.getSystemRegistry());
		applicationSystemDao.insert(applicationSystem);
	}

	@Override
	public void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
		ApplicationInterface applicationInterface = new ApplicationInterface();
		applicationInterface.setSystemId(applicationInterfaceVO.getSystemId());
		applicationInterface.setInterfaceId(applicationInterfaceVO.getInterfaceId());
		applicationInterface.setInterfaceName(applicationInterfaceVO.getInterfaceName());
		applicationInterface.setInterfaceVersion(applicationInterfaceVO.getInterfaceVersion());
		applicationInterfaceDao.insert(applicationInterface);
	}

	@Override
	public void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
		ApplicationInterfaceMethod applicationInterfaceMethod = new ApplicationInterfaceMethod();
		applicationInterfaceMethod.setSystemId(applicationInterfaceMethodVO.getSystemId());
		applicationInterfaceMethod.setInterfaceId(applicationInterfaceMethodVO.getInterfaceId());
		applicationInterfaceMethod.setMethodId(applicationInterfaceMethodVO.getMethodId());
		applicationInterfaceMethod.setMethodName(applicationInterfaceMethodVO.getMethodName());
		applicationInterfaceMethod.setParameterType(applicationInterfaceMethodVO.getParameterType());
		applicationInterfaceMethod.setUri(applicationInterfaceMethodVO.getUri());
		applicationInterfaceMethod.setHttpCommandType(applicationInterfaceMethodVO.getHttpCommandType());
		applicationInterfaceMethod.setAuth(applicationInterfaceMethodVO.getAuth());
		applicationInterfaceMethodDao.insert(applicationInterfaceMethod);
	}

}

