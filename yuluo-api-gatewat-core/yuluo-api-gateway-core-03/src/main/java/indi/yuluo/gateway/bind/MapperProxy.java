package indi.yuluo.gateway.bind;

import java.lang.reflect.Method;

import indi.yuluo.gateway.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 简化映射器处理，将原有的 RPC 泛化调用拆分。这个类中只完成代理部分，并调用映射器方法完成逻辑处理。
 */

public class MapperProxy implements MethodInterceptor {

	/**
	 * 网关会话
	 */
	private GatewaySession gatewaySession;

	/**
	 * 资源 uri
	 */
	private final String uri;

	public MapperProxy(GatewaySession gatewaySession, String uri) {
		this.gatewaySession = gatewaySession;
		this.uri = uri;
	}

	/**
	 * 做一层代理控制，不止是 Dubbo 的泛化调用，也可以是其他服务的泛化调用
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {

		MapperMethod linkMethod = new MapperMethod(uri, method, gatewaySession.getConfiguration());

		return linkMethod.execute(gatewaySession, args);
	}

}
