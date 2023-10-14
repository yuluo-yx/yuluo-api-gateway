package indi.yuluo.gateway.center.test;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import indi.yuluo.gateway.center.application.IApiService;
import indi.yuluo.gateway.center.domain.model.ApiData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

	private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);

	@Resource
	private IApiService apiService;

	@Test
	public void test() {

		List<ApiData> apiDataList = apiService.queryHttpStatementList();
		logger.info("测试结果：{}", JSON.toJSONString(apiDataList));
	}

}
