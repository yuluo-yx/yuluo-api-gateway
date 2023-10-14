package indi.yuluo.gateway.authorization.auth;

import indi.yuluo.gateway.authorization.GatewayAuthorizingToken;
import indi.yuluo.gateway.authorization.IAuth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class AuthService implements IAuth {

	private Subject subject;

	public AuthService() {
		// 1. 获取 SecurityManager 工厂，此处使用 shiro.init 配置文件初始化 SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2. 得到 SecurityManager 实例，并绑定给 SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3. 得到 Subject 及 Token
		this.subject = SecurityUtils.getSubject();

//		DefaultSecurityManager securityManager = new DefaultSecurityManager();
//		IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
//		securityManager.setRealm(iniRealm);
//		this.subject = SecurityUtils.getSubject();


	}

	@Override
	public boolean validate(String id, String token) {

		try {
			// 身份验证
			subject.login(new GatewayAuthorizingToken(id, token));
			// 返回结果
			return subject.isAuthenticated();
		} finally {
			// 退出
			subject.logout();
		}
	}
}
