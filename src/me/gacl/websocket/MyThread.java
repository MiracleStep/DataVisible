package me.gacl.websocket;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyThread extends Thread{

    public MyThread() {
    }


    @Override
    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(6980);
            System.out.println("TCPSERVER_STATR");
            while (true){
                Socket accept = serverSocket.accept();
                new SocketThread(accept).start();
                System.out.println("connection--ok");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
