package indi.yuluo.gateway.test;

import java.util.HashMap;
import java.util.Map;

import indi.yuluo.gateway.authorization.IAuth;
import indi.yuluo.gateway.authorization.JwtUtil;
import indi.yuluo.gateway.authorization.auth.AuthService;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class ShiroTest {

	@Test
	public void test_auth_service() {

		String issuer = "xiuxiu";
		long ttlMillis = 7 * 24 * 60 * 60 * 1000L;

		Map<String, Object> claims = new HashMap<>();
		claims.put("key", issuer);

		// 编码
		String token = JwtUtil.encode(issuer, ttlMillis, claims);

		String id = "xiuxiu";

		IAuth auth = new AuthService();
		boolean validate = auth.validate(id, token);

		System.out.println(validate ? "验证成功！" : "验证失败！");

	}

	@Test
	public void test_jwt() {

		String issuer = "xiuxiu";
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
