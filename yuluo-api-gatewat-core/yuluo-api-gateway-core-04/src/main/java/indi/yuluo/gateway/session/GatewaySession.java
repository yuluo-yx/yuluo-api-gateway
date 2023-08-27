package indi.yuluo.gateway.session;

import indi.yuluo.gateway.bind.IGenericReference;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */
public interface GatewaySession {

	Object get(String uri, Object parameter);

	IGenericReference getMapper();

	Configuration getConfiguration();

}
