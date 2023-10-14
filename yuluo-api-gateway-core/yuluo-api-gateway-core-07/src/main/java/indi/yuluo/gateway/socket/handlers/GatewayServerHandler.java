package indi.yuluo.gateway.socket.handlers;

import java.util.Map;

import indi.yuluo.gateway.bind.IGenericReference;
import indi.yuluo.gateway.session.GatewaySession;
import indi.yuluo.gateway.session.defaults.DefaultGatewaySessionFactory;
import indi.yuluo.gateway.socket.BaseHandler;
import indi.yuluo.gateway.socket.agreement.RequestParser;
import indi.yuluo.gateway.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
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

		// 1. 解析请求参数
		RequestParser requestParser = new RequestParser(request);
		String uri = requestParser.getUri();
		if (null == uri) {
			return;
		}
		Map<String, Object> args = new RequestParser(request).parser();

		// 2. 调用会话服务
		GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
		IGenericReference reference = gatewaySession.getMapper();
		Object result = reference.$invoke(args);

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("RPC 调用结果：" + result.toString());
		System.out.println("---------------------------------------------------------------------------");

		// 3. 封装返回结果
		DefaultFullHttpResponse response = new ResponseParser().parse(result);

		channel.writeAndFlush(response);
//				.addListener((ChannelFutureListener) channelFuture -> {
//					System.out.println("添加监听器---------------------------------");
//					if (channelFuture.isSuccess()) {
//						System.out.println("成功！------------------------");
//					} else {
//						System.out.println(channelFuture.toString());
//					}
//				});
	}


}
