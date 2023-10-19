package indi.yuluo.gateway.center.application;

import java.util.Map;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IMessageService {
	Map<String, String> queryRedisConfig();

	void pushMessage(String gatewayId, Object message);

}
