package server;

import file.fileSever;
import mainFrame.friendPanel;

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
    public static Map<String, Channel> userClient = new HashMap<String, Channel>();
    public Server() {
        try {
            new Thread(new fileSever()).start();
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                client = serverSocket.accept();
                channel = new Channel(client);
                new Thread(channel).start();
                System.out.println("1111---------链接成功--------");
                clients.add(channel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Channel implements Runnable {
        Socket client;
        DataInputStream dis;
        DataOutputStream dos;
        boolean isRun = true;

        public Channel(Socket client) {
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
                  friendPanel.setUserClient(str,1);
              }else{
                      c.send(msg);
              }
            }
        }
        private void send(String msg){
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                relase();
                e.printStackTrace();
            }
        }
        private void relase(){
            isRun = false;
            try {
                for (Map.Entry<String,Channel> l:
                    userClient.entrySet() ) {
                    if (l.getValue() == this){
                        System.out.println(l.getKey() + "退出群聊");
                           userClient.replace(l.getKey(),null);
                           friendPanel.setUserClient(l.getKey(),0);
                    }
                }
                dos.close();
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        new Server();
    }
}
