package minaDemo;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaTimeClient {

	public static void main(String[] args) throws InterruptedException {
        // 创建客户端连接器.
		NioSocketConnector connector = new NioSocketConnector();
		// 设置日志记录器
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		// 设置编码过滤器
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        // 设置连接超时检查时间
		connector.setConnectTimeoutCheckInterval(30);
		// 指定业务逻辑处理器
		connector.setHandler(new TimeClientHandler());

        // 建立连接
		ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1",6488));
        // 等待连接创建完成--阻塞式的
		future.awaitUninterruptibly();
		future.getSession().write("Hi Server");
		Thread.sleep(30*1000);
		future.getSession().write("quit");
	    // 等待连接断开
		future.getSession().getCloseFuture().awaitUninterruptibly();
		// 释放连接
		connector.dispose();
	}
}
