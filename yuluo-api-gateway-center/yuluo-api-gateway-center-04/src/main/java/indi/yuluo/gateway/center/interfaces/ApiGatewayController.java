package indi.yuluo.gateway.center.interfaces;

import java.util.List;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.application.IApiService;
import indi.yuluo.gateway.center.domain.manage.model.ApiData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@RestController
@RequestMapping("/api")
public class ApiGatewayController {
	private final Logger logger = LoggerFactory.getLogger(ApiGatewayController.class);

	@Resource
	private IApiService apiService;

	@GetMapping(value = "list", produces = "application/json;charset=utf-8")
	public List<ApiData> getAnswerMap(){

		return apiService.queryHttpStatementList();
	}
}

