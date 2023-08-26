package indi.yuluo.gateway.interfaces;

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

}
