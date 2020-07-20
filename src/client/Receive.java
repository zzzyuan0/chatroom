package client;

import mainFrame.filePanel;
import mainFrame.friendPanel;
import mainFrame.mainChat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
     DataInputStream dis;
     Socket client;
     mainChat mainChat;

    public Receive(Socket client,mainChat mainChat) {
        this.client = client;
        this.mainChat = mainChat;
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            relase();
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
      while (true){
          String msg = receive();
    //      System.out.println(msg + "----------");
          if (!msg.equals("")){
              if (msg.startsWith("+")){
                  msg = msg.substring(1,msg.length());
                  friendPanel.setUserClient(msg,1);
              }else if(msg.startsWith("-")){
                  msg = msg.substring(1,msg.length());
                  friendPanel.setUserClient(msg,null);
              }else if (msg.startsWith("#")){
                  msg = msg.substring(1,msg.length());
                  filePanel.setFileList(msg);
              }
              else {
                  String name = msg.substring(0,msg.indexOf("+")) + "\n";
                  String str = msg.substring(msg.indexOf("+")+1) + "\n";
                  String nameStr = name + str;
                  mainChat.seeChat.append(nameStr);
              }
//              mainChat.seeChat.append(  str);
          }
      }
    }
    public String receive(){
       String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            relase();
            e.printStackTrace();
        }
        return msg;
    }
    private void relase(){
        try {
            dis.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
