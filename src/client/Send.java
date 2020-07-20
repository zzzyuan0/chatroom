package client;

import Myuser.user;
import mainFrame.mainChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Send{
       Socket client;
       DataOutputStream dos;
       user user;
    SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间

    public Send(Socket client, user user) {
        this.client = client;
        this.user = user;
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
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
            Date date = new Date();
            String str = user.getName() +"(" + sdf.format(date) + ")+" + msg;
            dos.writeUTF(str);
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
