package indi.yuluo.gateway.center.domain.manage.repository;

import java.util.List;

import indi.yuluo.gateway.center.domain.manage.model.ApiData;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IApiRepository {

	List<ApiData> queryHttpStatementList();

}
