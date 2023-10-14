package indi.yuluo.gateway.executor;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import indi.yuluo.gateway.datatsource.Connection;
import indi.yuluo.gateway.executor.result.SessionResult;
import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.type.SimpleTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public abstract class BaseExecutor implements Executor {

	protected Configuration configuration;

	protected Connection connection;

	public BaseExecutor(Configuration configuration, Connection connection) {
		this.configuration = configuration;
		this.connection = connection;
	}

	private static final Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

	/**
	 * 执行器的抽象实现
	 * @param httpStatement
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception {

		// 参数处理
		String methodName = httpStatement.getMethodName();
		String parameterType = httpStatement.getParameterType();
		String[] parameterTypes = {parameterType};
		Object[] args = SimpleTypeRegistry.isSimpleType(parameterType)
				? params.values().toArray()
				: new Object[] {params};

		logger.info("执行调用 method：{}#{}.{}.({}), args: {}",
				httpStatement.getApplication(),
				httpStatement.getInterfaceName(),
				httpStatement.getMethodName(),
				JSON.toJSONString(parameterTypes),
				JSON.toJSONString(args)
		);

		// 抽象方法
		try {
			Object data = doExec(methodName, parameterTypes, args);
			return SessionResult.buildSuccess(data);
		}
		catch (Exception e) {
			return SessionResult.buildError(e.getMessage());
		}

	}

	protected abstract Object doExec(String methodName, String[] parameterTypes, Object[] args);

}
