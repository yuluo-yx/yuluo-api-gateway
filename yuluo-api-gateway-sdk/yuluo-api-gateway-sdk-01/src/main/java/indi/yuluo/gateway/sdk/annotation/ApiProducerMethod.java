package indi.yuluo.gateway.sdk.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiProducerMethod {

	/**
	 * 方法名称
	 */
	String methodName() default "";

	/**
	 * 访问路径 /wg/activity/sayHi
	 */
	String uri() default "";

	/**
	 * 接口类型 GET POST PUT ...
	 */
	String httpCommandType() default "GET";

	/**
	 * 是否认证 true = 1 是 false = 0 否
	 */
	int auth() default 0;

}
