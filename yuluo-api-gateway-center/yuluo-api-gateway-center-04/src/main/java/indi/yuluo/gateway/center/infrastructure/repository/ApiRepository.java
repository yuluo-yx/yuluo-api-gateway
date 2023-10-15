package indi.yuluo.gateway.center.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.domain.manage.model.ApiData;
import indi.yuluo.gateway.center.domain.manage.repository.IApiRepository;
import indi.yuluo.gateway.center.infrastructure.dao.IHttpStatementDao;
import indi.yuluo.gateway.center.infrastructure.po.HttpStatement;

import org.springframework.stereotype.Component;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Component
public class ApiRepository implements IApiRepository {

	@Resource
	private IHttpStatementDao httpStatementDao;

	@Override
	public List<ApiData> queryHttpStatementList() {

		List<HttpStatement> httpStatements = httpStatementDao.queryHttpStatementList();
		List<ApiData> apiDataList = new ArrayList<>(httpStatements.size());

		for (HttpStatement httpStatement : httpStatements) {
			ApiData apiData = new ApiData();
			apiData.setApplication(httpStatement.getApplication());
			apiData.setInterfaceName(httpStatement.getInterfaceName());
			apiData.setMethodName(httpStatement.getMethodName());
			apiData.setParameterType(httpStatement.getParameterType());
			apiData.setUri(httpStatement.getUri());
			apiData.setHttpCommandType(httpStatement.getHttpCommandType());
			apiData.setAuth(httpStatement.getAuth());
			apiDataList.add(apiData);
		}

		return apiDataList;
	}

}
