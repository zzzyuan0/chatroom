package file;

import mainFrame.filePanel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class fileSever implements Runnable {
    List<String> fileList = new ArrayList<String>();
    ServerSocket serverSocket = null;
    DataInputStream dis;
    FileOutputStream fos;
    Socket socket;
    String filePath;
    File file;
    public fileSever() {
        try {
            serverSocket = new ServerSocket(1314);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void run() {
         while (true){
             try {
                 socket = serverSocket.accept();
                 System.out.println("连接成功");
                 dis = new DataInputStream(socket.getInputStream());
                 filePath = dis.readUTF();
                 file = new File("D:\\upload\\");
                 System.out.println(file);
                  if (!file.exists()){
                     file.mkdir();
                     System.out.println("文件创建完毕");
                 }
                 fileList.add(filePath);
                 filePanel.setFileList(fileList);
                 file = new File(file +"\\"+ filePath);
                 System.out.println(file);
                  if (!file.exists()){
                      file.createNewFile();
                  }
                 fos = new FileOutputStream(file);
                 System.out.println("文件开始接受");
                 byte[] b = new byte[1024];
                 int len;
                 while ((len = dis.read(b)) != -1){
                    fos.write(b,0,len);
                 }
                 System.out.println("文件已经保存");
             } catch (IOException e) {
                 e.printStackTrace();
             }finally {
                 try {
                     fos.close();
                     dis.close();
                     socket.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

             }
         }
    }

    public static void main(String[] args) {
        new Thread(new fileSever()).start();
    }
}
