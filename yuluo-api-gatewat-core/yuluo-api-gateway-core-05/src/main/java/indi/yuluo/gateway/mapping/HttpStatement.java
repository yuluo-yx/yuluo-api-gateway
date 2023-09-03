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
	 * 参数类型
	 * 不用集合的意思是：限定网关参数，减少 RPC 的参数，可以一定程度避免线上事故。
	 */
	private String parameterType;

	/**
	 * 网关接口
	 */
	private String uri;

	/**
	 * 接口类型
	 */
	private HttpCommandType httpCommandType;

	public HttpStatement(
			String application,
			String interfaceName,
			String methodName,
			String parameterType,
			String uri,
			HttpCommandType httpCommandType
	) {
		this.application = application;
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.parameterType = parameterType;
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

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public HttpCommandType getHttpCommandType() {
		return httpCommandType;
	}

	public void setHttpCommandType(HttpCommandType httpCommandType) {
		this.httpCommandType = httpCommandType;
	}
}
