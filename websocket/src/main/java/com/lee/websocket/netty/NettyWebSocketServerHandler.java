package com.lee.websocket.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashMap;

/**
 * @author lee
 */
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    HashMap<String, ChannelHandlerContext> userMap;

    public void setUserMap(HashMap<String, ChannelHandlerContext> userMap) {
        this.userMap = userMap;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("WsTextInBoundHandle 收到了连接" + ctx.channel().id().asLongText() + " : " + ctx.channel().id().asShortText());
        userMap.put(ctx.channel().id().asShortText(), ctx);
    }

    // 读取客户端发送的请求报文
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        String str = "WsTextInBoundHandle 收到了一条消息, 内容为：" + msg.text();
        System.out.println(str);

        String[] split = msg.text().split("&&");
        String s = ctx.channel().id().asShortText() + " 发来消息：" + split[1];
        System.out.println(s);
        if (split[1].contains("len")) {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(ctx.channel().id().asShortText() + " 连接数：" + userMap.size()));
        } else if (split[0].isEmpty()) {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(ctx.channel().id().asShortText()));
        } else {
            userMap.get(split[0]).channel().writeAndFlush(new TextWebSocketFrame(s));
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("WsTextInBoundHandle 消息收到完毕");
        ctx.flush();
    }

    // 客户端离线
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // ctx.channel().id() 表示唯一的值
        System.out.println("handlerRemoved 被调用， channel.id.longText = " + ctx.channel().id().asLongText());
        userMap.remove(ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("WsTextInBoundHandle 连接逻辑中发生了异常");
        cause.printStackTrace();
        ctx.close();
        userMap.remove(ctx.channel().id().asShortText());
    }
}