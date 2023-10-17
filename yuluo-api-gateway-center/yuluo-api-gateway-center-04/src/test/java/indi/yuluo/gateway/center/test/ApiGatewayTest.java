package indi.yuluo.gateway.center.test;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import indi.yuluo.gateway.center.YuluoApiGatewayApplication;
import indi.yuluo.gateway.center.application.IConfigManageService;
import indi.yuluo.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import indi.yuluo.gateway.center.domain.manage.model.vo.GatewayServerVO;
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
@SpringBootTest(classes = YuluoApiGatewayApplication.class)
public class ApiGatewayTest {

	private final Logger logger = LoggerFactory.getLogger(ApiGatewayTest.class);

	@Resource
	private IConfigManageService configManageService;

	@Test
	public void test_queryGatewayServerList() {

		List<GatewayServerVO> gatewayServerVO = configManageService.queryGatewayServerList();
		logger.info("测试结果：{}", JSON.toJSONString(gatewayServerVO));
	}

	@Test
	public void test_queryGatewayServerDetailList() {

		List<GatewayServerDetailVO> gatewayServerDetailVOS = configManageService.queryGatewayServerDetailList();
		logger.info("测试结果：{}", JSON.toJSONString(gatewayServerDetailVOS));
	}

	@Test
	public void test_registerGatewayServerNode() {

		configManageService.registryGatewayServerNode(
				"10001",
				"api-gateway-g1",
				"电商支付网关",
				"127.0.0.196"
		);
		configManageService.registryGatewayServerNode(
				"10001",
				"api-gateway-g2",
				"电商支付网关",
				"127.0.0.197"
		);
		configManageService.registryGatewayServerNode(
				"10001",
				"api-gateway-g3",
				"电商配送网关",
				"127.0.0.198"
		);
	}

	@Test
	public void test_queryApplicationSystemRichInfo() {
		ApplicationSystemRichInfo info = configManageService.queryApplicationSystemRichInfo("api-gateway-g4");
		System.out.println("测试结果：" + info);
	}

}

