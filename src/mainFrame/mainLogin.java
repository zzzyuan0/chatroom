package mainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class mainLogin {
    private static ImageIcon back = new ImageIcon("src/imgs/back.jpg");
    public static void main(String[] args) {
        main_Login main_login = new main_Login();

        up_Login up_login = new up_Login();
        up_login.setBounds(0,0,Constant.W,200);


        main_login.add(up_login);

        dawn_Login dawn_login = new dawn_Login();
        dawn_login.setBounds(0,300,Constant.W,300);
        main_login.add(dawn_login);

    }
    static class main_Login extends JFrame{
        boolean isDraging = false;
        int xx,yy;
        public main_Login() {
           this.setSize(Constant.W,Constant.H);
           this.setLayout(null);
           this.setUndecorated(true);
           this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           this.setVisible(true);



            //实现无边框拖拽界面
            //监听最初位置
            this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    isDraging = true;
                    xx = e.getX();
                    yy = e.getY();
                    if (xx <= 800 && xx >= 773 && yy >=0 && yy <= 30){
                        dispose();
                    }
                    if (xx <= 773 && xx >= 700 && yy >=0 && yy <= 30){
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
    }
    static class up_Login extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(back.getImage(),0,0,Constant.W,200,null);
        }
    }
    static class dawn_Login extends JPanel{
        private JLabel img ;
        private JPanel jPanel1;
        private JPanel jPanel2 ;
        private JButton Login;

        public dawn_Login() {
            this.setLayout(null);
            ImageIcon qq = new ImageIcon("src/imgs/qq.jpg");
            img = new JLabel(qq);
            img.setBounds(100,300,qq.getIconWidth(),qq.getIconHeight());

//            jPanel1 = new JPanel(null);

            this.add(img);
        }
    }
}
