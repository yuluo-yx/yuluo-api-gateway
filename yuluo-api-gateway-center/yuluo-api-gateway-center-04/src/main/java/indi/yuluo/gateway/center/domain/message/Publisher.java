package indi.yuluo.gateway.center.domain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Service
public class Publisher {

	private final RedisTemplate<String, Object> redisMessageTemplate;

	@Autowired
	public Publisher(RedisTemplate<String, Object> redisMessageTemplate) {

		this.redisMessageTemplate = redisMessageTemplate;
	}

	public void pushMessage(String topic, Object message){
		redisMessageTemplate.convertAndSend(topic, message);
	}
}

