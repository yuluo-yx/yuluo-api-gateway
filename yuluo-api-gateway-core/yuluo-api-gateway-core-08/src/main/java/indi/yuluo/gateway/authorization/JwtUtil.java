package indi.yuluo.gateway.authorization;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class JwtUtil {

	private static final String signingKey = "B8B^5Fe";

	public static String encode(String issuer, long ttlMills, Map<String, Object> claims) {

		if (claims == null) {
			claims = new HashMap<>();
		}

		// 签发时间：载荷部分的标准字段之一
		long nowMills = System.currentTimeMillis();
		Date now = new Date(nowMills);
		// 签发操作
		JwtBuilder builder = Jwts.builder()
				// 载荷部分
				.setClaims(claims)
				// 签发时间
				.setIssuedAt(now)
				// 签发人：类似 userId，userName
				.setSubject(issuer)
				// 设置生成签名的算法和密钥
				.signWith(SignatureAlgorithm.HS256, signingKey);

		if (ttlMills > 0) {
			long expMills = nowMills + ttlMills;
			Date exp = new Date(expMills);
			// exp：过期时间，载荷部分的标准字段之一，代表这个 jwt 的有效期
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	public static Claims decode(String token) {

		return Jwts.parser()
				// 设置签名密钥
				.setSigningKey(signingKey)
				// 设置需要解析的 jwt
				.parseClaimsJws(token)
				.getBody();
	}

}
