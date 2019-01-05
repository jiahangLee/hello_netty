package com.jiahanglee;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


// 创建自定义助手类
public class CustomerHandler extends SimpleChannelInboundHandler<HttpObject> {

    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //具体操作
        Channel channel = ctx.channel();
        if (msg instanceof HttpRequest) {
            System.out.println(channel.remoteAddress());

            //发送消息
            ByteBuf content = Unpooled.copiedBuffer("hello netty~", CharsetUtil.UTF_8);
            // 构建http response
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
            //为响应设置数据类型和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            // 把相应刷到客户端
            ctx.writeAndFlush(response);
        }

    }
}
