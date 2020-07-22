package server;

import file.fileSever;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    ServerSocket serverSocket;
    Socket client;
    Channel channel;
    List<Channel>  clients = new ArrayList<Channel>();
    public static List<String> fileList = new ArrayList<String>();
    public static Map<String, Channel> userClient = new HashMap<String, Channel>();
    public Server() {
        try {
            new Thread(new fileSever()).start();  //开启文件服务器
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                client = serverSocket.accept();  //接受客户端连接
                channel = new Channel(client);  //该客户端创建一个线程Channel，时刻监听信息的接收
                new Thread(channel).start();
                System.out.println("1111---------链接成功--------");
                clients.add(channel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 负责信息的接收与发送，每一个客户端都有自己独立的线程
     */
    class Channel implements Runnable {
        Socket client;
        DataInputStream dis;
        DataOutputStream dos;
        boolean isRun = true;

        public Channel(Socket client) {
            new Thread(new reFriend()).start();
            this.client = client;
            try {
                dos = new DataOutputStream(client.getOutputStream());
                dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                relase();
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            String msg;
            while (isRun){
                msg= receive();
                if (!msg.equals("")){
                    sendfirend(msg);
                }
            }
        }
        //信息的接收
        private String receive(){
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                System.out.println("退出连接");
                relase();
            }
            return msg;
        }
        // 遍历clients里面已经连接的客户端，并且判断信息是否以@开头(相当于上线)，如果不是则直接调用send发送信息
        private void sendfirend(String msg){
            for (Channel c:
                clients ) {
              if (msg.startsWith("@")){
                  String str = msg.substring(1);
                  if (userClient.containsKey(str)){
                      userClient.replace(str,this);
                  }else {
                      userClient.put(str,this);
                  }
              }else{
                      c.send(msg);
              }
            }
        }
        //信息发送
        private synchronized void send(String msg){
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                relase();
                e.printStackTrace();
            }
        }
        //释放流，移除相对应的客户端
        private void relase(){
            isRun = false;
            try {
                for (Map.Entry<String,Channel> l:
                    userClient.entrySet() ) {
                    if (l.getValue() == this){
                        System.out.println(l.getKey() + "退出群聊");
                        userClient.replace(l.getKey(),null);
                    }
                }
                dos.close();
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //时刻监听那些客户端上线和离线
        class reFriend implements Runnable{
            @Override
            public void run() {
                while (true){
                    for (Map.Entry<String,Channel> l:
                            userClient.entrySet() ) {
                        if (l.getValue() == null) {
                            for (Channel c :
                                    clients) {
                                c.send("-" + l.getKey());
                            }
                        }else {
                            for (Channel c :
                                    clients) {
                                c.send("+" + l.getKey());
                            }
                        }
                    }
                    for (String str:
                         fileList) {
                        send("#" + str);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
