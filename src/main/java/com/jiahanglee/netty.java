package com.jiahanglee;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class netty {
    public static void main(String[] args) throws Exception {

        //定义一对主线程组,接受客户端连接，不做其他事
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //从线程组，负责主要工作
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //netty服务器的创建
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //主从线程组和通道
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HelloServerInit());

            //启动，设置端口，启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            //监听关闭的channel，也为同步
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
