package indi.yuluo.gateway.datatsource;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface Connection {

	/**
	 * RPC 连接接口 （封装连接资源）
	 * @param method 			方法名
	 * @param parameterTypes 	参数类型
	 * @param parameterNames 	参数名
	 * @param args 				参数值
	 * @return
	 */
		Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args);

}
