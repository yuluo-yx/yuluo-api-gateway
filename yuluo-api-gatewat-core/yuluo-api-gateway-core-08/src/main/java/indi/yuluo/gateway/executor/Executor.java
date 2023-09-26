package indi.yuluo.gateway.executor;

import java.util.Map;

import indi.yuluo.gateway.executor.result.SessionResult;
import indi.yuluo.gateway.mapping.HttpStatement;


/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 执行器模块，封装对数据源 （RPC）的调用，以及处理相关的入参和出参信息。
 * 同时将调用结果包装到一个标准的类中，包含 code码，info 描述，data结果信息
 */

public interface Executor {

	SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;

}
