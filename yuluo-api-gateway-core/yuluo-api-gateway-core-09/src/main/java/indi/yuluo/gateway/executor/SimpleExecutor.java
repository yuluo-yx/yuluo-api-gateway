package indi.yuluo.gateway.executor;

import indi.yuluo.gateway.datatsource.Connection;
import indi.yuluo.gateway.session.Configuration;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class SimpleExecutor extends BaseExecutor {

	public SimpleExecutor(Configuration configuration, Connection connection) {
		super(configuration, connection);
	}

	/**
	 * 调用服务
	 * 封装参数：PS：为什么这样构建参数，参考测试 case ： indi.yuluo.gateway.test.RPCTest
	 * 01（允许）：java.lang.String
	 * 02（允许）：indi.yuluo.gateway.rpc.dto.XReq
	 * 03（拒绝）：java.lang.String, indi.yuluo.gateway.rpc.dto.XReq 不提供多个方法参数的处理
	 */
	@Override
	protected Object doExec(String methodName, String[] parameterTypes, Object[] args) {


		return connection.execute(
				methodName,
				parameterTypes,
				new String[]{"ignore"},
				args
		);
	}

}
