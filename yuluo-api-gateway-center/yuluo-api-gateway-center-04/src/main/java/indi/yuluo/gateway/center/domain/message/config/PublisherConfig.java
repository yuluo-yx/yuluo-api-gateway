package indi.yuluo.gateway.center.domain.message.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */
@Configuration
public class PublisherConfig {

	@Bean
	public RedisTemplate<String, Object> redisMessageTemplate(RedisConnectionFactory connectionFactory){

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new FastJsonRedisSerializer<>(Object.class));

		return template;
	}
}
