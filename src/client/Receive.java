package client;

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
          if (!msg.equals("")){
             String name = msg.substring(0,msg.indexOf("+")) + "\n";
             String str = msg.substring(msg.indexOf("+")+1);
             String nameStr = name + str;
              mainChat.seeChat.append(nameStr);
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
