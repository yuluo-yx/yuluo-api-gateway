package indi.yuluo.gateway.rpc.dto;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class XReq {

	private String id;

	private String name;

	public XReq() {
	}

	public XReq(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
