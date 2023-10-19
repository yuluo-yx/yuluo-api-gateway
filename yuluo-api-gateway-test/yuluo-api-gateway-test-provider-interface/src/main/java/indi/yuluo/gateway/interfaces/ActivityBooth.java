package indi.yuluo.gateway.interfaces;

import com.alibaba.fastjson.JSON;
import indi.yuluo.gateway.rpc.dto.XReq;
import indi.yuluo.gateway.rpc.IActivityBooth;
import indi.yuluo.gateway.sdk.annotation.ApiProducerClazz;
import indi.yuluo.gateway.sdk.annotation.ApiProducerMethod;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Service(version = "1.0.0")
@ApiProducerClazz(interfaceName = "活动服务", interfaceVersion = "1.0.0")
public class ActivityBooth implements IActivityBooth {

	@Override
	@ApiProducerMethod(methodName = "探活方法", uri = "/wg/activity/sayHi", httpCommandType = "GET", auth = 0)
	public String sayHi(String str) {

		return "hi " + str + " by yuluo-api-gateway-test-provider";
	}

	/**
	 * 对面参数
	 * @param req
	 * @return
	 */
	@Override
	@ApiProducerMethod(methodName = "插入方法", uri = "/wg/activity/insert", httpCommandType = "POST", auth = 1)
	public String insert(XReq req) {

		return "hi " + JSON.toJSONString(req) + " by yuluo-api-gateway-provider";
	}

	/**
	 * 多参数类型
	 */
	@Override
	@ApiProducerMethod(methodName = "测试方法", uri = "/wg/activity/test", httpCommandType = "POST", auth = 0)
	public String test(String str, XReq req) {

		return "hi " + str + JSON.toJSONString(req) + " by yuluo-api-gateway-test-provider";
	}

}
