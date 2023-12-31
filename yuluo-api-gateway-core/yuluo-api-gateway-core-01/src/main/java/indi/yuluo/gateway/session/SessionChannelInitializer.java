package indi.yuluo.gateway.session;

import indi.yuluo.gateway.session.handlers.SessionServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 包装 Http 种 GET/POST 协议的解析，还包括需要自己完成的网络请求。
 */

public class SessionChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {

		// 编解码信息
		ChannelPipeline pipeline = socketChannel.pipeline();
		pipeline.addLast(new HttpRequestDecoder());
		pipeline.addLast(new HttpRequestEncoder());

		// 处理 Post 请求
		pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
		pipeline.addLast(new SessionServerHandler());
	}
}
