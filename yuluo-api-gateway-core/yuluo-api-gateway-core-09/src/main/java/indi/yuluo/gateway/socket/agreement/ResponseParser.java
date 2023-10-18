package indi.yuluo.gateway.socket.agreement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public class ResponseParser {

	public DefaultFullHttpResponse parse(Object result) {

		DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

		// 设置回写数据
		response.content().writeBytes(JSON.toJSONBytes(result, SerializerFeature.PrettyFormat));

		// 头部信息设置
		HttpHeaders headers = response.headers();
		// 返回内容类型
		headers.add(
				HttpHeaderNames.CONTENT_TYPE,
				HttpHeaderValues.APPLICATION_JSON + "; charset=UTF-8"
		);
		// 响应体的长度
		headers.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
		// 配置持久连接
		headers.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
		// 配置跨域访问
		headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
		headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
		headers.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

		return response;
	}

}
