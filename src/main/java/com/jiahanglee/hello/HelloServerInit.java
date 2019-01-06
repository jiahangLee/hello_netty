package com.jiahanglee.hello;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HelloServerInit extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel channel) throws Exception {

        // 通过socketChannel获取对应的管道
        ChannelPipeline pipeline = channel.pipeline();

        // 通过管道添加handle助手类，netty的
        // 编解码器，响应到客户端做编码
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());
        // 添加自定义助手类
        pipeline.addLast("customHandler",new CustomerHandler());
    }
}
