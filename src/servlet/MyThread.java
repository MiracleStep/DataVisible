package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyThread extends Thread{
    HttpSession session;

    public MyThread() {
    }
    public  MyThread(HttpSession session){
        this.session = session;
    }



    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6980);
            System.out.println("TCPSERVER_STATR");
            Socket accept = serverSocket.accept();
            System.out.println("connection--ok");
            InputStream inputStream = accept.getInputStream();
            byte[] b = new  byte[1024];
            ArrayList arrayList = new ArrayList();
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
                }
                session.setAttribute("list",arrayList);
                for (int i=0;i<arrayList.size();i++){
                    System.out.println(arrayList.get(i));
                }
                session.setAttribute("list",arrayList);
                session.setAttribute("size",arrayList.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
