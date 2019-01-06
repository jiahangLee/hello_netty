# hello_netty -> 主从线程组
 一、netty类
 * EventLoopGroup定义主线程和从线程组
 * ServerBootstrap创建netty服务
    * 给ServerBootstrap添加定义的主从线程组和nio双向通道以及字处理器即用于处理从线程池
    * 给ServerBootstrap绑定channel端口和设置同步方式以及关闭channel的同步方式
 * 最后优雅关闭主从线程组
 二、HelloServerInit（主要处理channel）
 * 初始化channel，通过channel获取ChannelPipeline
    * ChannelPipeline是由channel和一系列handler组成
 * 给ChannelPipeline添加编解码器
 * 给ChannelPipeline添加自定义handler
 三、CustomerHandler（主要处理handler）
 * 从上下文ctx中获取channel（这里可以获取channel的系列信息）
 * 创建ByteBuf，往里面塞数据
 * 创建httpResponse，把ByteBuf中的内容添加到response
 * 给response添加header系列参数
 * ctx.writeAndFlush(response)数据刷写
 * End 