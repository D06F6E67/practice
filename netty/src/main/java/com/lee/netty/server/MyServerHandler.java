package com.lee.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义的Handler需要继承Netty规定好的HandlerAdapter
 * 才能被Netty框架所关联，有点类似SpringMVC的适配器模式
 *
 * @author Lee
 */
@Component
@ChannelHandler.Sharable
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private Map<String, ChannelHandlerContext> map = new HashMap<>();

    public void send(String id, String msg) {
        ChannelHandlerContext channelHandlerContext = map.get(id);
        if (Objects.nonNull(channelHandlerContext)) {
            channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        }
    }

    public void ids() {
        map.keySet().stream().forEach(System.out::println);
    }

    /**
     * 通道连接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("通道连接：" + ctx.channel().id());
        map.put(ctx.channel().id().toString(), ctx);
        System.out.println(map.size());
    }

    /**
     * 通道关闭
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("通道关闭：" + ctx.channel().id());
        map.remove(ctx.channel().id().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //获取客户端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到客户端" + ctx.channel().remoteAddress() + "$$" + ctx.channel().id() +
                "发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //发送消息给客户端
        // ctx.writeAndFlush(Unpooled.copiedBuffer("服务端已收到消息", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //发生异常，关闭通道
        ctx.close();
    }
}
