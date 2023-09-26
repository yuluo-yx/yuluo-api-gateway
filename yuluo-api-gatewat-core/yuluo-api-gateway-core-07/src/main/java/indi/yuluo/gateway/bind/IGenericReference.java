package indi.yuluo.gateway.bind;

import java.util.Map;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 定义接口，专门给通信层做编码时调用使用（统一泛化调用接口）
 */

public interface IGenericReference {

	Object $invoke(Map<String, Object> args);

}
