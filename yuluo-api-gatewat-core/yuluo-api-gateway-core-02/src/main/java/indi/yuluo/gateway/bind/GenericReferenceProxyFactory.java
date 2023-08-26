package indi.yuluo.gateway.bind;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import org.apache.dubbo.rpc.service.GenericService;
import org.objectweb.asm.Type;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 用于创建代理对象，用到了 cglib 操作：
 * 先构建出一个 RPC 接口信息，之后把 IGenericReference 接口和构建的就扣，一起给代理类实现
 * 就是说 一个代理实现两个接口，创建完成之后，使用定义的接口调用，模拟创建出来的接口逻辑。
 */

public class GenericReferenceProxyFactory {

	/**
	 * RPC 泛化调用服务
	 */
	private final GenericService genericService;

	private final Map<String, IGenericReference> genericReferenceCache = new ConcurrentHashMap<>();

	public GenericReferenceProxyFactory(GenericService genericService) {
		this.genericService = genericService;
	}

	public IGenericReference newInstance(String method) {

		return genericReferenceCache.computeIfAbsent(method, k -> {
			// 泛化调用
			GenericReferenceProxy genericReferenceProxy = new GenericReferenceProxy(genericService, method);

			// 创建接口
			InterfaceMaker interfaceMaker = new InterfaceMaker();
			interfaceMaker.add(new Signature(
					method,
					Type.getType(String.class),
					new Type[]{Type.getType(String.class)}),
					null
			);
			Class<?> interfaceclass = interfaceMaker.create();

			// 代理对象
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(Object.class);

			// IGenericReference 统一泛化调用接口
			// interfaceClass 根绝泛化调用注册信息创建的接口，建立 http -> rpc 映射关系

			enhancer.setInterfaces(new Class[]{IGenericReference.class, interfaceclass});
			enhancer.setCallback(genericReferenceProxy);

			return (IGenericReference) enhancer.create();
		});
	}

}
