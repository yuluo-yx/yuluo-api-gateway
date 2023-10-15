package indi.yuluo.gateway.center.application;

import java.util.List;

import indi.yuluo.gateway.center.domain.manage.model.ApiData;


/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IApiService {

	List<ApiData> queryHttpStatementList();

}

