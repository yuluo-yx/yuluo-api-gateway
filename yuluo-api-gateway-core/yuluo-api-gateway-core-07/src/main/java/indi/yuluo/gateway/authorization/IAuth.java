package indi.yuluo.gateway.authorization;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 认证服务接口
 */

public interface IAuth {

	boolean validate(String id, String token);

}
