package indi.yuluo.gateway.executor.result;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class SessionResult {

	private String code;

	private String info;

	private Object data;

	public SessionResult(String code, String info, Object data) {
		this.code = code;
		this.info = info;
		this.data = data;
	}

	public static SessionResult buildSuccess(Object data) {

		return new SessionResult("0000", "调用成功", data);
	}

	public static SessionResult buildError(Object data) {

		return new SessionResult("0001", "调用成功", data);
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
