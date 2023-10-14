package indi.yuluo.gateway.center.domain.service;

import java.util.List;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.application.IApiService;
import indi.yuluo.gateway.center.domain.model.ApiData;
import indi.yuluo.gateway.center.domain.repository.IApiRepository;

import org.springframework.stereotype.Component;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Component
public class ApiServiceImpl implements IApiService {

	@Resource
	private IApiRepository apiRepository;

	@Override
	public List<ApiData> queryHttpStatementList() {

		return apiRepository.queryHttpStatementList();
	}

}
