package file;

import java.io.*;
import java.net.Socket;

public class sendFile {
    FileInputStream fis = null;
    DataOutputStream dos;
    File file;
    public sendFile(String filePath, Socket client) {
        try {
            dos = new DataOutputStream(client.getOutputStream());
            file = new File(filePath);
            fis = new FileInputStream(file);
            String[] str = filePath.split("\\\\");
            OutputStream out = client.getOutputStream();//这里的输出流 对应的是服务器端的输入流
            dos.writeUTF(str[str.length-1]);
            System.out.println(str[str.length-1]);
            byte[] b = new byte[1024];
            int len = 0;
            while((len = fis.read(b)) != -1){
                out.write(b,0,len);
            }
            System.out.println("发送成功");
            client.shutdownOutput();
            out.close();
            fis.close();
//            dos.close();
            client.close();
        }catch (Exception e){

        }
    }
}
