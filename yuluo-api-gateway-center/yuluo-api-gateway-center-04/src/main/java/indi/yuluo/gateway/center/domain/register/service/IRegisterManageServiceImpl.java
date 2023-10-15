package indi.yuluo.gateway.center.domain.register.service;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.application.IRegisterManageService;
import indi.yuluo.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import indi.yuluo.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import indi.yuluo.gateway.center.domain.register.model.vo.ApplicationSystemVO;
import indi.yuluo.gateway.center.domain.register.repository.IRegisterManageRepository;

import org.springframework.stereotype.Service;

/**
 * 接口注册服务
 */

@Service
public class IRegisterManageServiceImpl implements IRegisterManageService {

	@Resource
	private IRegisterManageRepository registerManageRepository;

	@Override
	public void registerApplication(ApplicationSystemVO applicationSystemVO) {

		registerManageRepository.registerApplication(applicationSystemVO);
	}

	@Override
	public void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {

		registerManageRepository.registerApplicationInterface(applicationInterfaceVO);
	}

	@Override
	public void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {

		registerManageRepository.registerApplicationInterfaceMethod(applicationInterfaceMethodVO);
	}

	@Override
	public void hello(String s) {

		System.out.println(s);
	}
}

