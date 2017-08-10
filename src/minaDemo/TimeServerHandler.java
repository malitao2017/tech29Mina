package minaDemo;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
/**
 * 服务器端业务逻辑
 */
public class TimeServerHandler extends IoHandlerAdapter{
	/**
     * 连接创建事件
     */
	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		System.out.println("服务端接入了一个客户端为："+iosession.getRemoteAddress().toString());
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
	/**
     * 消息接收事件
     */
	@Override
	public void messageReceived(IoSession iosession, Object message) throws Exception {
		String strMsg = message.toString();
        // 打印客户端传来的消息内容
		System.out.println("服务端接受消息: （ "+strMsg +" ）同时发送了一条消息");
		if(strMsg.trim().equalsIgnoreCase("quit")){
			iosession.close(true);
			return;
		}
        // 返回消息字符串
		iosession.write("(server message - Hi Client!)");
	}
	
	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus) throws Exception {
		System.out.println("服务端空闲信息 IDLE可看做心跳: "+iosession.getIdleCount(idlestatus));
	}
}
