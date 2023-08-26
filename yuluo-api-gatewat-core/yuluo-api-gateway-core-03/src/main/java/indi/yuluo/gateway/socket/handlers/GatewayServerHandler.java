package indi.yuluo.gateway.socket.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import indi.yuluo.gateway.bind.IGenericReference;
import indi.yuluo.gateway.session.GatewaySession;
import indi.yuluo.gateway.session.defaults.DefaultGatewaySessionFactory;
import indi.yuluo.gateway.socket.BaseHandler;
import indi.yuluo.gateway.util.DateTimeFormat;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * DefaultFullHttpResponse 相当于在 HTTP 会话中构建所需的协议信息，包括头信息，编码，响应体长度，跨域等。
 * 还包括需要向网页响应的数据，即 response.content().writeBytes(....) 中。
 */

public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

	private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);

	private final DefaultGatewaySessionFactory gatewaySessionFactory;

	public GatewayServerHandler(DefaultGatewaySessionFactory gatewaySessionFactory) {
		this.gatewaySessionFactory = gatewaySessionFactory;
	}

	@Override
	protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {

		logger.info("网关接受请求 uri: {} method: {}", request.uri(), request.method());

		// 返回信息控制，简单处理
		String uri = request.uri();
		if (uri.equals("favicon.ico")) {
			return;
		}

		GatewaySession gatewaySession = gatewaySessionFactory.openSession();
		IGenericReference reference = gatewaySession.getMapper(uri);

		// 返回信息处理
		DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		String result = reference.$invoke("test $invoke()") + " " + DateTimeFormat.format(System.currentTimeMillis());

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("RPC 调用结果：" + result);
		System.out.println("---------------------------------------------------------------------------");

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

		channel.writeAndFlush(response);
	}
}
