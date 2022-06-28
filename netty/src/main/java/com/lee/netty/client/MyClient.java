package com.lee.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.scheduling.annotation.Async;

/**
 * 客户端
 *
 * @author Lee
 */
public class MyClient {

    private MyClientHandler myClientHandler;

    public MyClient() {
    }

    public MyClient(Integer port) {
        System.out.println(port);
        new Thread(() -> run(port)).start();
        System.out.println(port);
    }

    @Async
    public void run(Integer serverPort) {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        myClientHandler = new MyClientHandler();
        try {
            //创建bootstrap对象，配置参数
            Bootstrap bootstrap = new Bootstrap();
            //设置线程组
            bootstrap.group(eventExecutors)
                    //设置客户端的通道实现类型
                    .channel(NioSocketChannel.class)
                    //使用匿名内部类初始化通道
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            //添加客户端通道的处理器
                            ch.pipeline().addLast(myClientHandler);
                        }
                    });
            System.out.println("客户端启动");
            //连接服务端
            ChannelFuture channelFuture = bootstrap.connect("192.168.31.22", serverPort).sync();
            //对通道关闭进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭线程组
            eventExecutors.shutdownGracefully();
        }
    }

    public void send(String msg) {
        myClientHandler.send(msg);
    }

}
