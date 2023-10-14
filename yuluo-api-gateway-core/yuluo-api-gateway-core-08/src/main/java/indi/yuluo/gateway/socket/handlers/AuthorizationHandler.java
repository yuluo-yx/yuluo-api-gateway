package indi.yuluo.gateway.socket.handlers;

import indi.yuluo.gateway.mapping.HttpStatement;
import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.socket.BaseHandler;
import indi.yuluo.gateway.socket.agreement.AgreementConstants;
import indi.yuluo.gateway.socket.agreement.GatewayResultMessage;
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

public class AuthorizationHandler extends BaseHandler<FullHttpRequest> {

	private final Configuration configuration;

	public AuthorizationHandler(Configuration configuration) {
		this.configuration = configuration;
	}

	private static final Logger log = LoggerFactory.getLogger(AuthorizationHandler.class);

	protected void session(
			ChannelHandlerContext ctx,
			Channel channel,
			FullHttpRequest request
	) {

		log.info("网关接受请求【鉴权】 uri：{} method：{}", request.uri(), request.method());

		try {
			HttpStatement httpStatement = channel.attr(AgreementConstants.HTTP_STATEMENT).get();

			if (httpStatement.isAuth()) {
				try {
					// 鉴权信息
					String uId = request.headers().get("uId");
					String token = request.headers().get("token");

					// 鉴权判断
					if (null == token || "".equals(token)) {
						DefaultFullHttpResponse response = new ResponseParser().parse(
								GatewayResultMessage.buildError(
										AgreementConstants.ResponseCode._400.getCode(),
										"对不起，你的 token 不合法！"
								)
						);
						channel.writeAndFlush(response);
					}

					// 鉴权处理 shiro + jwt
					boolean status = configuration.authValidate(uId, token);

					// 鉴权成功，直接放行
					if (status) {
						request.retain();
						ctx.fireChannelRead(request);
					}
					// 鉴权失败
					else {
						DefaultFullHttpResponse response = new ResponseParser().parse(
								GatewayResultMessage.buildError(
										AgreementConstants.ResponseCode._403.getCode(),
										"对不起，你无权访问此接口！"
								)
						);
						channel.writeAndFlush(response);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					DefaultFullHttpResponse response = new ResponseParser().parse(
							GatewayResultMessage.buildError(
									AgreementConstants.ResponseCode._403.getCode(),
									"对不起，你的鉴权结果不合法！"
							)
					);
					channel.writeAndFlush(response);
				}
			}
			// 不鉴权放行 获取资源展示的接口
			else {
				request.retain();
				ctx.fireChannelRead(request);
			}
		} catch (Exception e) {
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
