package indi.yuluo.gateway.socket;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

import indi.yuluo.gateway.session.Configuration;
import indi.yuluo.gateway.session.defaults.DefaultGatewaySessionFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * 启动 netty 服务，netty 的启动除了配置对应的端口之外，还需要把对应的数据处理
 * 一并初始化到 channel 种，也就是对应的 SessionChannelInitializer 功能。
 *
 * 实现 Callable 接口，让服务在线程池中启动。
 */

public class GatewaySocketServer implements Callable<Channel> {

	private final DefaultGatewaySessionFactory gatewaySessionFactory;

	private final Configuration configuration;

	public GatewaySocketServer(
			Configuration configuration,
			DefaultGatewaySessionFactory gatewaySessionFactory
	) {
		this.configuration = configuration;
		this.gatewaySessionFactory = gatewaySessionFactory;

		// 初始化 netty 线程数
		this.initEventLoopGroup();
	}

	private final Logger logger = LoggerFactory.getLogger(GatewaySocketServer.class);

	// 分别启动的是 连接等待 和 数据处理
	private EventLoopGroup boss;

	private EventLoopGroup work;

	// 允许外部配置线程数
	private void initEventLoopGroup() {

		boss = new NioEventLoopGroup(configuration.getBossNThreads());
		work = new NioEventLoopGroup(configuration.getWorkNThreads());
	}

	private Channel channel;

	@Override
	public Channel call() {

		ChannelFuture channelFuture = null;

		try {

			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boss, work)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 128)
					// 添加会话的初始信息
					.childHandler(
							new GatewayChannelInitializer(
									configuration,
									gatewaySessionFactory
							)
					);

			channelFuture = serverBootstrap.bind(new InetSocketAddress(
					configuration.getHostName(), configuration.getPort())).syncUninterruptibly();
			this.channel = channelFuture.channel();

		} catch (Exception e) {
			logger.error("socket server start error. ", e);
		} finally {
			if (null != channelFuture && channelFuture.isSuccess()) {
				logger.info("socket server start done!");
			} else {
				logger.error("socket server start error!");
			}
		}

		return channel;
	}
}
