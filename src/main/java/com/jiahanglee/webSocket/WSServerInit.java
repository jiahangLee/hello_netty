package com.jiahanglee.webSocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerInit extends ChannelInitializer {
    protected void initChannel(Channel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());
        pipeline.addLast("dashujuliu",new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        // 以上是为了支持http请求
        // 处理繁杂的事：握手操作和心跳等，指定url路径，frames为消息的载体
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 自定义handler
        pipeline.addLast(new WSHandler());
    }
}
