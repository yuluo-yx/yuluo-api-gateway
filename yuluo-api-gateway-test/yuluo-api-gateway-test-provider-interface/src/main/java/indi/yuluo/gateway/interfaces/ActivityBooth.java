package indi.yuluo.gateway.interfaces;

import com.alibaba.fastjson.JSON;
import indi.yuluo.gateway.rpc.dto.XReq;
import indi.yuluo.gateway.rpc.IActivityBooth;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Service(version = "1.0.0")
public class ActivityBooth implements IActivityBooth {

	@Override
	public String sayHi(String str) {

		return "hi " + str + " by yuluo-api-gateway-test-provider";
	}

	/**
	 * 对面参数
	 * @param req
	 * @return
	 */
	@Override
	public String insert(XReq req) {

		return "hi " + JSON.toJSONString(req) + " by yuluo-api-gateway-provider";
	}

	/**
	 * 多参数类型
	 */
	@Override
	public String test(String str, XReq req) {

		return "hi " + str + JSON.toJSONString(req) + " by yuluo-api-gateway-test-provider";
	}

}
