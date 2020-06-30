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

        dawn_Login dawn_login = new dawn_Login();
        dawn_login.setBounds(0,200,Constant.W,400);

        main_login.add(up_login);
        main_login.add(dawn_login);

        main_login.setUndecorated(true);
        main_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_login.setVisible(true);

    }
    static class main_Login extends JFrame{
        boolean isDraging = false;
        int xx,yy;
        public main_Login() {
           this.setSize(Constant.W,Constant.H);
           this.setLayout(null);




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
        private JButton Login;
        private String name = "张三";

        public dawn_Login() {
            this.setSize(800,400);
            this.setLayout(null);
            ImageIcon qq = new ImageIcon("src/imgs/qq.jpg");
            img = new JLabel(qq);
            img.setBounds(100,100,qq.getIconWidth(),qq.getIconHeight());

            jPanel1 = new JPanel();
            JLabel jLabel = new JLabel("请输入你的账号                       注册账号");
            jLabel.setFont(new Font("楷体",1,20));
            jLabel.setBackground(Color.BLUE);
            jLabel.setBounds(200,100,500,60);

            JTextField jTextField = new JTextField(20);
            jTextField.setPreferredSize(new Dimension(100,20));
            JLabel jLabel1 = new JLabel("找回密码");
            jLabel.setFont(new Font("楷体",0,20));
            jLabel.setBackground(Color.BLUE);

            jPanel1.add(jTextField);
            jPanel1.add(jLabel1);
            jPanel1.setBounds(200,170,500,100);

            Login = new JButton("  登录  ");
            Login.setBounds(200,270,500,60);

            this.add(Login);
            this.add(jPanel1);
            this.add(jLabel);
            this.add(img);

            Login.addActionListener(e -> {
                if (jTextField.getText().equals(name)){

                 System.exit(0);
                }else{
                    JOptionPane.showMessageDialog(new JPanel(),"你输入的密码或者账号错误","请重新输入",JOptionPane.WARNING_MESSAGE);
                }

            });
        }

//        @Override
//        public void paint(Graphics g) {
//            super.paint(g);
//       this.setBackground(Color.BLACK);
//        }
    }
}
