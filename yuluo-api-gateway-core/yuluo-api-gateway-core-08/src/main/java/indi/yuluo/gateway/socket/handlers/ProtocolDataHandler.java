package indi.yuluo.gateway.socket.handlers;

import java.util.Map;

import indi.yuluo.gateway.bind.IGenericReference;
import indi.yuluo.gateway.executor.result.SessionResult;
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
 */

public class ProtocolDataHandler extends BaseHandler<FullHttpRequest> {

	private final DefaultGatewaySessionFactory gatewaySessionFactory;

	private static final Logger log = LoggerFactory.getLogger(ProtocolDataHandler.class);

	public ProtocolDataHandler(DefaultGatewaySessionFactory gatewaySessionFactory) {
		this.gatewaySessionFactory = gatewaySessionFactory;
	}

	protected void session(
		ChannelHandlerContext ctx,
		Channel channel,
		FullHttpRequest request
	) {

		log.info("网关接受请求【消息】 uri：{} method：{}", request.uri(), request.method());

		try {

			// 1. 解析请求参数
			RequestParser requestParser = new RequestParser(request);
			String uri = requestParser.getUri();

			if (uri == null) {
				return;
			}
			Map<String, Object> args = requestParser.parser();

			// 2. 调用会话服务
			GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
			IGenericReference reference = gatewaySession.getMapper();
			SessionResult result = reference.$invoke(args);

			// 3. 封装返回结果
			DefaultFullHttpResponse response = new ResponseParser().parse(
					"0000".equals(result.getCode())
							? GatewayResultMessage.buildSuccess(result.getData())
							: GatewayResultMessage.buildError(
								AgreementConstants.ResponseCode._404.getCode(),
								"网关协议调用失败！"
							 )
			);
			channel.writeAndFlush(response);

		} catch (Exception e) {
			DefaultFullHttpResponse response = new ResponseParser().parse(
					GatewayResultMessage.buildError(
							AgreementConstants.ResponseCode._502.getCode(),
							"网关协议调用失败！" + e.getMessage()
					)
			);
			channel.writeAndFlush(response);
		}

	}

}
