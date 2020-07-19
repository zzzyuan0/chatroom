package mainFrame;

import Myuser.user;
import client.Receive;
import client.Send;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;



public class mainChat extends JFrame {
    boolean isDraging;
    int xx,yy,mouse_x=1000,range_x = 40;
    int width = Constant.C_W,high = Constant.C_H;
    boolean isMin = true;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    filePanel filePanel = new filePanel();
    public static friendPanel friendPanel1 = new friendPanel();
    public JTextArea seeChat = new JTextArea(500,500);
    public JTextArea keyChat = new JTextArea(300,500);
    user user;


    private JButton send = new JButton("发送");
    public mainChat(user user){
        this.user = user;
        setSocket();
        this.setBounds(500,500,width,high);
        this.setLayout(null);
        this.setUndecorated(true);
        chatPanel chatPanel = new chatPanel();
        chatPanel.setLayout(null);
        chatPanel.setOpaque(true);
        send.setBackground(Color.DARK_GRAY);
        send.setFont(new Font("楷体",0,15));
        send.setForeground(Color.cyan);
        send.setBounds(550,960,70,30);
        friendPanel1.setBounds(630,100,350,300);
        filePanel.setBounds(630,410,350,540);
        seeChat.setBounds(10,100,610,500);
        keyChat.setBounds(10,650,610,300);
        new Thread(friendPanel1).start();
        chatPanel.add(send);
        chatPanel.add(friendPanel1);
        chatPanel.add(filePanel);
        chatPanel.add(seeChat);
        chatPanel.add(keyChat);
        this.setContentPane(chatPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);

        //实现无边框拖拽界面
        //监听最初位置
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                isDraging = true;
                xx = e.getX();
                yy = e.getY();
                if (xx <= mouse_x && xx >= mouse_x-range_x && yy >=0 && yy <= 30){
                    dispose();
                }
//                if (xx <= mouse_x - range_x && xx >= mouse_x - 2*range_x && yy >=0 && yy <= 30){
//                    if (isMin){
//                        width = screen.width;
//                        high = screen.height;
//                        mouse_x = screen.width;
//                        range_x = 70;
//                        setBounds(0,0,width,high);
//                        repaint();
//                        isMin = false;
//                    }else{
//                        width = Constant.C_W;
//                        high = Constant.C_H;
//                        mouse_x = Constant.C_W;
//                        range_x = 40;
//                        setBounds(0,0,width,high);
//                        repaint();
//                        isMin = true;
//                    }
//                }
                if (xx <= mouse_x - range_x && xx >= mouse_x - 2*range_x && yy >=0 && yy <= 30){
                    setExtendedState(JFrame.ICONIFIED);
                }

            }
            public void mouseReleased(MouseEvent e) {
                isDraging = false;
            }
        });
        //时刻更新鼠标位置
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                //修改位置
                if (isDraging) {
                    int left = getLocation().x;
                    int top = getLocation().y;
                    setLocation(left + e.getX() - xx, top + e.getY() - yy);
                }
            }
        });
    }
    private void setSocket(){
        try {
            Socket client = new Socket("localhost",8888);
            new Thread(new Receive(client,this)).start();
            new Send(client,user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class chatPanel extends JPanel{
        ImageIcon close = new ImageIcon("src/imgs/close.png");
        ImageIcon min = new ImageIcon("src/imgs/login.png");
        ImageIcon panel = new ImageIcon("src/imgs/panel.png");
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            this.setBackground(Color.darkGray);
            g.drawImage(close.getImage(),960,0,40,40,null);
            g.drawImage(min.getImage(),920,0,40,40,null);
            g.drawImage(panel.getImage(),10,600,610,50,null);
        }
    }

    class filePanel extends JPanel{

    }

//    public static void main(String[] args) {
//        new mainChat();
//    }
}
