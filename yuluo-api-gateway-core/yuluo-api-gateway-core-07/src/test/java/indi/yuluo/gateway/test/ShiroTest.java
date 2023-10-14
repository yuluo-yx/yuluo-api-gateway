package indi.yuluo.gateway.test;

import java.util.HashMap;
import java.util.Map;

import indi.yuluo.gateway.authorization.IAuth;
import indi.yuluo.gateway.authorization.JwtUtil;
import indi.yuluo.gateway.authorization.auth.AuthService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class ShiroTest {

	@Test
	public void test_shiro() {

		String user = "yuluo";
		String password = "163";

		// 从配置文件加载 IniSecurityManagerFactory 工厂
		IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:test-shiro.ini");
		// 获取 SecurityManager 实例
		SecurityManager securityManager = securityManagerFactory.getInstance();
		// 绑定 SecurityManager 给 SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();

		// 默认提供的验证方式，用户名/密码
		UsernamePasswordToken token = new UsernamePasswordToken(user, password);

		try {
			// 登陆验证
			subject.login(token);
		} catch (Exception e) {
			// 登陆失败
			throw new AuthenticationException("验证失败！");
		}

		System.out.println(subject.isAuthenticated() ? "验证成功！" : "验证失败！");

		// 退出
		subject.logout();

	}

	@Test
	public void test_auth_service() {

		String id = "Dpij8iUY";

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5dWx1byIsImV4cCI6MTY5NTU2ODE1NSwiaWF0IjoxNjk0OTYzMzU1LCJrZXkiOiJ5dWx1byJ9.wlT_wfbhdRN5MZAsLtyRlNCb51Hxeefc5p5C0lGbBXg";

		IAuth auth = new AuthService();
		boolean validate = auth.validate(id, token);

		System.out.println(validate ? "验证成功！" : "验证失败！");

	}

	@Test
	public void test_jwt() {

		String issuer = "yuluo";
		long ttlMillis = 7 * 24 * 60 * 60 * 1000L;

		Map<String, Object> claims = new HashMap<>();
		claims.put("key", issuer);

		// 编码
		String token = JwtUtil.encode(issuer, ttlMillis, claims);

		System.out.println(token);

		// 解码
		Claims parser = JwtUtil.decode(token);
		System.out.println(parser.getSubject());
	}

}
