package indi.yuluo.gateway.socket;

import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.defaults.DefaultGatewaySessionFactory;
import indi.yuluo.gateway.socket.handlers.AuthorizationHandler;
import indi.yuluo.gateway.socket.handlers.GatewayServerHandler;
import indi.yuluo.gateway.socket.handlers.ProtocolDataHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 包装 Http 种 GET/POST 协议的解析，还包括需要自己完成的网络请求。
 */

public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {

	private final Configuration configuration;

	private final DefaultGatewaySessionFactory gatewaySessionFactory;

	public GatewayChannelInitializer(
			Configuration configuration,
			DefaultGatewaySessionFactory gatewaySessionFactory
	) {
		this.gatewaySessionFactory = gatewaySessionFactory;
		this.configuration = configuration;
	}

	@Override
	protected void initChannel(SocketChannel socketChannel) {

		// 编解码信息
		ChannelPipeline pipeline = socketChannel.pipeline();
		pipeline.addLast(new HttpRequestDecoder());

		// unsupported message type: DefaultFullHttpResponse (expected: ByteBuf, FileRegion))
		// 如果出现上述问题，可能是编解码不合适导致的！需要注意
		pipeline.addLast(new HttpResponseEncoder());

		// 处理 Post 请求
		pipeline.addLast(new HttpObjectAggregator(1024 * 1024));

		pipeline.addLast(new GatewayServerHandler(configuration));
		pipeline.addLast(new AuthorizationHandler(configuration));
		pipeline.addLast(new ProtocolDataHandler(gatewaySessionFactory));
	}
}
