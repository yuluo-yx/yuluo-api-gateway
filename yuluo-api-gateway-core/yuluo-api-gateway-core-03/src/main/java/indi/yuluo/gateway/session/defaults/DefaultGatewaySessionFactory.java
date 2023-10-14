package indi.yuluo.gateway.session.defaults;

import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.GatewaySession;
import indi.yuluo.gateway.session.GatewaySessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 泛化调用会话工厂
 */

public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

	private final Logger logger = LoggerFactory.getLogger(DefaultGatewaySessionFactory.class);

	private final Configuration configuration;

	public DefaultGatewaySessionFactory(Configuration configuration) {

		this.configuration = configuration;
	}

	@Override
	public GatewaySession openSession() {

		return new DefaultGatewaySession(configuration);
	}

}
