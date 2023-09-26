package indi.yuluo.gateway.authorization;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 验证token
 */
public class GatewayAuthorizingToken implements AuthenticationToken {
	private static final long serialVersionUID = 1L;

	private String channelId;
	private String jwt;

	public GatewayAuthorizingToken(){

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
