package servlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/WebSocketTest")
public class WebSocket {
    private Session session;
    @OnOpen//打开连接执行
    public void onOpw(Session session) {
        this.session=session;
        System.out.println("打开了连接");
    }
    @OnMessage//收到消息执行
    public void onMessage(String message,Session session) {
        System.out.println(message);
        try {
            sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnClose//关闭连接执行
    public void onClose(Session session) {
        System.out.println("关闭连接");
    }
    @OnError//连接错误的时候执行
    public void onError(Throwable error,Session session) {
        System.out.println("错误的时候执行");
        error.printStackTrace();
    }
    /*
    websocket  session发送文本消息有两个方法：getAsyncRemote()和
   getBasicRemote()  getAsyncRemote()和getBasicRemote()是异步与同步的区别，
   大部分情况下，推荐使用getAsyncRemote()。
  */
    public void sendMessage(String message) throws IOException{
        this.session.getAsyncRemote().sendText(message);
    }
}
