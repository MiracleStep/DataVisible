package me.gacl.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketThread extends Thread{
    private Socket socket;
    public static ArrayList<String> arrayList = null;

    public SocketThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            byte[] b = new  byte[1024];

            arrayList = new ArrayList<>();
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
                if (socket.isClosed()){
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
