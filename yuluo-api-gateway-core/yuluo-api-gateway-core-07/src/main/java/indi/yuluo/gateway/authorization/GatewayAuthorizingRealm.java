package indi.yuluo.gateway.authorization;

import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 验证领域
 */

public class GatewayAuthorizingRealm extends AuthorizingRealm {

	@Override
	public Class<?> getAuthenticationTokenClass() {

		return GatewayAuthorizingToken.class;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		// 暂时不需要做授权处理
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {

		try {
			// 验证解析是否报错
			Claims claims = JwtUtil.decode(((GatewayAuthorizingToken) authenticationToken).getJwt());
			if(!authenticationToken.getPrincipal().equals(claims.getSubject())){
				throw new AuthenticationException("无效令牌");
			}
		} catch (Exception e) {
			throw new AuthenticationException("无效 jwt 令牌！");
		}

		return new SimpleAuthenticationInfo(
				authenticationToken.getPrincipal(),
				authenticationToken.getCredentials(),
				this.getName()
		);
	}

}
