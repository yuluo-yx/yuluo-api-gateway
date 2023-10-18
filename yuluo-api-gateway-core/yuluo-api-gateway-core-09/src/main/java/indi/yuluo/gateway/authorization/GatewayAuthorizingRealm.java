package indi.yuluo.gateway.authorization;

import java.util.Objects;

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
	public Class<?> getAuthenticationTokenClass(){

		return GatewayAuthorizingToken.class;
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	public boolean supports(AuthenticationToken token) {

		return token instanceof GatewayAuthorizingToken;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		try{
			String jwt = ((GatewayAuthorizingToken) token).getJwt();

			if (Objects.equals(jwt, null)) {
				throw new AuthenticationException("token jwt is null, please check!");
			}

			Claims claims = JwtUtil.decode(jwt);
			if(!token.getPrincipal().equals(claims.getSubject())){
				throw new AuthenticationException("无效令牌");
			}
		} catch (Exception e) {
			throw new AuthenticationException(e.getMessage());
		}
		return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), this.getName());
	}
}
