package mainFrame;

import file.fileReciver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class filePanel extends JPanel implements Runnable{
    int pos_y= 0;
    private static Map<String, JButton> map_file = new HashMap<String, JButton>();
    private static List<String> fileList = new ArrayList<String>();
    public static void setFileList(List<String> fileList) {
        filePanel.fileList = fileList;
        System.out.println(fileList.size() + "      list");
    }
    public filePanel() {

    }
    @Override
    public void run() {
        fileList.add("1.png");
        while (true) {
            repaint();
         //   System.out.println(fileList.size());
            for (String l:
                 fileList) {
                   if (!map_file.containsKey(l)){
                       addfile(l);
                   }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void addfile(String file){
        JButton jButton = new JButton(file);
        jButton.setSize(600,50);
        jButton.setBounds(10,pos_y,330,30);
        pos_y += 30;
        jButton.setFont(new Font("楷体", 1, 14));
        jButton.setForeground(Color.cyan);
        jButton.addActionListener(e -> {
           new fileReciver(file);
        });
        map_file.put(file,jButton);
        this.add(jButton);
    }
}
