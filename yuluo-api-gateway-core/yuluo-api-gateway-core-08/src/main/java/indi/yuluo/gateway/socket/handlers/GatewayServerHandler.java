package indi.yuluo.gateway.socket.handlers;

import java.util.Map;

import indi.yuluo.gateway.bind.IGenericReference;
import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.GatewaySession;
import indi.yuluo.gateway.session.defaults.DefaultGatewaySessionFactory;
import indi.yuluo.gateway.socket.BaseHandler;
import indi.yuluo.gateway.socket.agreement.AgreementConstants;
import indi.yuluo.gateway.socket.agreement.GatewayResultMessage;
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

	private final Configuration configuration;

	public GatewayServerHandler(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected void session(
			ChannelHandlerContext ctx,
			Channel channel,
			FullHttpRequest request
	) {

		logger.info("网关接受请求 [全局] uri: {} method: {}", request.uri(), request.method());

		try {
			// 1. 解析请求参数
			RequestParser requestParser = new RequestParser(request);
			String uri = requestParser.getUri();

			// 2. 保存信息：HttpStatement
			HttpStatement httpStatement = configuration.getHttpStatement(uri);
			channel.attr(AgreementConstants.HTTP_STATEMENT).set(httpStatement);

			// 3. 放行服务
			request.retain();
			ctx.fireChannelRead(request);
		}
		catch (Exception e) {
			// 4. 封装返回结果
			DefaultFullHttpResponse response = new ResponseParser().parse(
					GatewayResultMessage.buildError(
							AgreementConstants.ResponseCode._500.getCode(),
							"网关协议调用失败！" + e.getMessage()
					)
			);
			channel.writeAndFlush(response);

		}
	}


}
