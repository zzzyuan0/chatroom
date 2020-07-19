package client;

import Myuser.user;
import mainFrame.mainChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Send{
       Socket client;
       DataOutputStream dos;
    public Send(Socket client, user user) {
        this.client = client;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF("@" + user.getName());
            System.out.println(user.getName() + "--------------");
        } catch (IOException e) {
            relase();
            e.printStackTrace();
        }
    }
    public void send(String msg){
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            relase();
            e.printStackTrace();
        }
    }
    private void relase(){
        try {
            dos.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
