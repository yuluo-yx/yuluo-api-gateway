package indi.yuluo.gateway.sdk.interfaces.impl;

import indi.yuluo.gateway.sdk.annotation.ApiProducerClazz;
import indi.yuluo.gateway.sdk.annotation.ApiProducerMethod;
import indi.yuluo.gateway.sdk.interfaces.UserService;

import org.springframework.stereotype.Service;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 用户服务
 */

@ApiProducerClazz(interfaceName = "用户服务", interfaceVersion = "1.0.0")
@Service
public class UserServiceImpl implements UserService {

	@ApiProducerMethod(
			methodName = "探测",
			uri = "/wg/user/hi",
			httpCommandType = "GET",
			auth = 1
	)
	public String hi(String str) {

		return "hi " + str + "by yuluo-api-gateway-sdk";
	}

}
