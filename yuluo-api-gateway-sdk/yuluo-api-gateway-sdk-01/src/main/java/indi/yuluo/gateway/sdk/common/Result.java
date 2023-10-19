package indi.yuluo.gateway.sdk.common;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class Result <T>{
	private String code;
	private String info;
	private T data;

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
