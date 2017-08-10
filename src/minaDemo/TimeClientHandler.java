package minaDemo;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeClientHandler extends IoHandlerAdapter{
	
	@Override
	public void messageReceived(IoSession iosession, Object message) throws Exception {
		String content = message.toString();
		System.out.println("客户端接收消息："+content);
	}

	@Override
	public void messageSent(IoSession iosession, Object message) throws Exception {
		System.out.println("客户端发送消息："+message);
	}
}
