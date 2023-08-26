package indi.yuluo.gateway.mapping;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class HttpStatement {

	/**
	 * 应用名称
	 */
	private String application;

	/**
	 * 服务接口：RPC，其他
	 */
	private String interfaceName;

	/**
	 * 服务方法：RPC#method
	 */
	private String methodName;

	/**
	 * 网关接口
	 */
	private String uri;

	/**
	 * 接口类型
	 */
	private HttpCommandType httpCommandType;

	public HttpStatement(String application, String interfaceName, String methodName, String uri, HttpCommandType httpCommandType) {
		this.application = application;
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.uri = uri;
		this.httpCommandType = httpCommandType;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public HttpCommandType getHttpCommandType() {
		return httpCommandType;
	}

	public void setHttpCommandType(HttpCommandType httpCommandType) {
		this.httpCommandType = httpCommandType;
	}
}
