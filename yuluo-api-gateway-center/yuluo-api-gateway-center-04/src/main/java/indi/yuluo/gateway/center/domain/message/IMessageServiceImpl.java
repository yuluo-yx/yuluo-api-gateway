package indi.yuluo.gateway.center.domain.message;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import indi.yuluo.gateway.center.application.IMessageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Service
public class IMessageServiceImpl implements IMessageService {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private Integer port;

	@Resource
	private Publisher publisher;

	@Override
	public Map<String, String> queryRedisConfig() {
		return new HashMap<String, String>() {{
			put("host", host);
			put("port", String.valueOf(port));
		}};
	}

	@Override
	public void pushMessage(String gatewayId, Object message) {
		publisher.pushMessage(gatewayId, message);

	}
}

