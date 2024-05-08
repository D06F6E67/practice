package com.lee.websocket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author lee
 */
@Component
public class NettyWebSocketServer {

    HashMap<String, ChannelHandlerContext> userMap = new HashMap<>();

    @PostConstruct
    public void run() {
        // 创建线程池执行器
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try {
            // 服务器启动引导对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .localAddress(8080)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            NettyWebSocketServerHandler nettyWebSocketServerHandler = new NettyWebSocketServerHandler();
                            nettyWebSocketServerHandler.setUserMap(userMap);

                            socketChannel.pipeline()
                                    // HTTP 请求解码和响应编码
                                    .addLast(new HttpServerCodec())
                                    // 以块方式写，添加 chunkedWriter 处理器
                                    .addLast(new ChunkedWriteHandler())
                                    // HTTP 压缩支持
                                    .addLast(new HttpContentCompressor())
                                    // HTTP 对象聚合完整对象
                                    .addLast(new HttpObjectAggregator(65536))
                                    // WebSocket支持
                                    .addLast(new WebSocketServerProtocolHandler("/netty-ws"))
                                    // 自定义handler ，处理业务逻辑
                                    .addLast(nettyWebSocketServerHandler);
                        }
                    });

            //绑定端口号，启动服务端
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("WebSocketNettServer启动成功");

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully().syncUninterruptibly();
            bossGroup.shutdownGracefully().syncUninterruptibly();
        }
    }
}