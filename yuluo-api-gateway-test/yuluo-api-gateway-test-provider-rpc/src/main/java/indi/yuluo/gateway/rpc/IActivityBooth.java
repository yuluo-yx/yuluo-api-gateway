package indi.yuluo.gateway.rpc;

import indi.yuluo.gateway.rpc.dto.XReq;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IActivityBooth {

	String sayHi(String str);

	String insert(XReq req);

	String test(String str, XReq req);

}
