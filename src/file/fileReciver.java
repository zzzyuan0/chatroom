package file;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class fileReciver {
    FileOutputStream fos;
    FileInputStream fis;
    DataInputStream dis;
    String path;
    File file;
    public fileReciver(String path) {
        this.path = path;
        try {
            fis = new FileInputStream("D:\\upload\\" + path);
            System.out.println(path + "------*---");
            file = addFilePath();
            String down = path.substring(path.indexOf('.'),path.length());
             System.out.println(down);
            file = new File(file.toString() + down);
            if (!file.exists()){
                file.createNewFile();
            }
            System.out.println(file);
            fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fis.read(b)) != -1){
                fos.write(b,0,len);
            }
            fos.flush();
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public fileReciver() {
    }

    public File addFilePath(){
        JFileChooser jfc;
        File file = null;
        int judge= JOptionPane.showConfirmDialog(null, "正在接受文件 " + path );
        if(judge==0){
            jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.setSelectedFile(file);
            jfc.showSaveDialog(null);
            file = jfc.getSelectedFile();

        }else{
            return null;
        }

        return file;
    }
}
