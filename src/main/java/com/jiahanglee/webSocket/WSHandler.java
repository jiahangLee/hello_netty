package com.jiahanglee.webSocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

public class WSHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup  client = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

        String content = textWebSocketFrame.text();
        System.out.println("接收到的数据："+content);

        for(Channel channel: client) {
            channel.writeAndFlush(new TextWebSocketFrame("服务器接收到消息"+ LocalDateTime.now()+"接收到的消息："+content));
        }
//        client.writeAndFlush(
//                new TextWebSocketFrame("服务器接收到消息"+ LocalDateTime.now()+"接收到的消息："+content)
//        );

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 收集用户channel
        client.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        client.remove(ctx.channel());
        System.out.println("长id："+ctx.channel().id().asLongText());
        System.out.println("短id："+ctx.channel().id().asShortText());
    }
}
