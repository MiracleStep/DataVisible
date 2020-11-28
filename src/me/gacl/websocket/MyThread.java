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
    public ArrayList<String> arrayList = null;

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        arrayList = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(6980);
            System.out.println("TCPSERVER_STATR");
            Socket accept = serverSocket.accept();
            System.out.println("connection--ok");
            InputStream inputStream = accept.getInputStream();
            byte[] b = new  byte[1024];

            long index = 0;
            while (true){
                int len = inputStream.read(b);
                String str = "";
                for (int i=0;i<len;i++){
                    str += ((char)b[i]+"");
                }
                String rpstr = str.replace(" ", ",");
                String[] split = rpstr.split(",");
                for (int i=0;i<split.length;i++){
                    arrayList.add(split[i]);
                    System.out.println("数据库添加了数据"+split[i]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
