package indi.yuluo.gateway.authorization;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class GatewayAuthorizingToken implements AuthenticationToken {

	private static final long serialVersionUID = 1L;

	// 通信管道 id
	private String channelId;

	// JSON WEB TOKE
	private String jwt;

	public GatewayAuthorizingToken() {
	}

	public GatewayAuthorizingToken(String channelId, String jwt) {
		this.channelId = channelId;
		this.jwt = jwt;
	}

	@Override
	public Object getPrincipal() {
		return this.channelId;
	}

	@Override
	public Object getCredentials() {

		return this.jwt;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
}
