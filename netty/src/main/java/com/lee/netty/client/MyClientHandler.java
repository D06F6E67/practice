package com.lee.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Lee
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext channelHandlerContext;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送消息到服务端
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello word！", CharsetUtil.UTF_8));
        channelHandlerContext = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收服务端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        String s = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("收到服务端" + ctx.channel().remoteAddress() + "的消息：" + s);
        ctx.writeAndFlush(Unpooled.copiedBuffer(s, CharsetUtil.UTF_8));
    }

    public void send(String msg) {
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }
}
