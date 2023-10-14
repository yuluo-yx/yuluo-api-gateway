package indi.yuluo.gateway.session;

import java.util.Map;

import indi.yuluo.gateway.bind.IGenericReference;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */
public interface GatewaySession {

	Object get(String uri, Map<String, Object> parameter);

	Object post(String uri, Map<String, Object> parameter);

	IGenericReference getMapper();

	Configuration getConfiguration();

}
