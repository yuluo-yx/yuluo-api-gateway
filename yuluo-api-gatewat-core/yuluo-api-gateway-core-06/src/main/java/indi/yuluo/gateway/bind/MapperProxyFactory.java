package indi.yuluo.gateway.bind;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.GatewaySession;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import org.objectweb.asm.Type;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 用于创建代理对象，用到了 cglib 操作：
 * 先构建出一个 RPC 接口信息，之后把 IGenericReference 接口和构建的就扣，一起给代理类实现
 * 就是说 一个代理实现两个接口，创建完成之后，使用定义的接口调用，模拟创建出来的接口逻辑。
 */

public class MapperProxyFactory {

	private String uri;

	public MapperProxyFactory(String uri) {
		this.uri = uri;
	}

	private final Map<String, IGenericReference> genericReferenceCache = new ConcurrentHashMap<>();

	public IGenericReference newInstance(GatewaySession gatewaySession) {

		return genericReferenceCache.computeIfAbsent(uri, k -> {

			HttpStatement httpStatement = gatewaySession.getConfiguration().getHttpStatement(uri);

			// 泛化调用
			MapperProxy genericReferenceProxy = new MapperProxy(gatewaySession, uri);

			// 创建接口
			InterfaceMaker interfaceMaker = new InterfaceMaker();
			interfaceMaker.add(new Signature(httpStatement.getMethodName(), Type.getType(String.class), new Type[]{Type.getType(String.class)}), null);
			Class<?> interfaceClass = interfaceMaker.create();

			// 代理对象
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(Object.class);

			// IGenericReference 统一泛化调用接口
			// interfaceClass    根据泛化调用注册信息创建的接口，建立 http -> rpc 关联
			enhancer.setInterfaces(new Class[]{IGenericReference.class, interfaceClass});
			enhancer.setCallback(genericReferenceProxy);

			return (IGenericReference) enhancer.create();
		});
	}

}
