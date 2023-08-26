package indi.yuluo.gateway.bind;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 用于 RPC 接口的泛化调用封装处理，封装 RPC 提供的 GenericService 方法
 */

public class GenericReferenceProxy implements MethodInterceptor {

	/**
	 * RPC 泛化调用服务
	 */
	private final GenericService genericService;

	/**
	 * RPC 泛化调用方法
	 */
	private final String methodName;

	public GenericReferenceProxy(GenericService genericService, String methodName) {
		this.genericService = genericService;
		this.methodName = methodName;
	}

	/**
	 * 做一层代理控制，不止是 Dubbo 的泛化调用，也可以是其他服务的泛化调用
	 */
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {

		Class<?>[] parameterTypes = method.getParameterTypes();
		String[] parameters = new String[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			parameters[i] = parameterTypes[i].getName();
		}

		/*
		举例：
		genericService.$invoke("sayHi", new String[]{"java.lang.String", new Object[]{"World"}};
		 */

		return genericService.$invoke(methodName, parameters, args);
	}

}
