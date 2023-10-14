package indi.yuluo.gateway.bind;

import java.lang.reflect.Method;
import java.util.Map;

import indi.yuluo.gateway.mapping.HttpCommandType;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.GatewaySession;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 绑定调用方法
 */

public class MapperMethod {

	private final String methodName;

	private final HttpCommandType command;

	public MapperMethod(String uri, Method method, Configuration configuration) {
		this.methodName = configuration.getHttpStatement(uri).getMethodName();
		this.command = configuration.getHttpStatement(uri).getHttpCommandType();
	}

	public Object execute(GatewaySession session, Map<String, Object> params) {

		Object result = null;
		switch (command) {
		case GET:
			result = session.get(methodName, params);
			break;
		case POST:
			result = session.post(methodName, params);
		case PUT:
			break;
		case DELETE:
			break;
		default:
			throw new RuntimeException("Unknown execution method for: " + command);
		}

		return result;
	}

}
