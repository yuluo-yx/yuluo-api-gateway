package indi.yuluo.gateway.sdk.exception;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class GatewayException extends RuntimeException{

	public GatewayException(String msg) {
		super(msg);
	}

	public GatewayException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
