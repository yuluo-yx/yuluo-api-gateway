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
@Target(ElementType.TYPE)
public @interface ApiProducerClazz {

	/**
	 * 接口名称
	 */
	String interfaceName() default "";

	/**
	 * 接口版本
	 */
	String interfaceVersion() default "";

}
