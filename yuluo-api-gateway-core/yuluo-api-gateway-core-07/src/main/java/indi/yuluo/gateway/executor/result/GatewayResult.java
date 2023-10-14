package indi.yuluo.gateway.executor.result;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class GatewayResult {

	private String code;

	private String info;

	private Object data;

	public GatewayResult(String code, String info, Object data) {
		this.code = code;
		this.info = info;
		this.data = data;
	}

	public static GatewayResult buildSuccess(Object data) {

		return new GatewayResult("0000", "调用成功", data);
	}

	public static GatewayResult buildError(Object data) {

		return new GatewayResult("0001", "调用成功", data);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
