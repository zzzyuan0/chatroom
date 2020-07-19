package mainFrame;


import Myuser.user;

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
        up_login.setBounds(0,0,Constant.L_W,150);

        dawn_Login dawn_login = new dawn_Login();
        dawn_login.setBounds(0,200,Constant.L_W,350);

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
           this.setSize(Constant.L_W,Constant.L_H);
           this.setLayout(null);
           //实现无边框拖拽界面
            //监听最初位置
            this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    isDraging = true;
                    xx = e.getX();
                    yy = e.getY();
                    if (xx <= 600 && xx >= 573 && yy >=0 && yy <= 30){
                        dispose();
                    }
                    if (xx <= 573 && xx >= 500 && yy >=0 && yy <= 30){
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
            g.drawImage(back.getImage(),0,0,Constant.L_W,200,null);
        }
    }
    static class dawn_Login extends JPanel{
        private JLabel img ;
        private JPanel jPanel1;
        private JPanel jPanel2;
        private JButton Login;
        private String name = "张三";

        public dawn_Login() {
            this.setSize(800,400);
            this.setLayout(null);
            ImageIcon qq = new ImageIcon("src/imgs/qq.jpg");
            img = new JLabel(qq);
            img.setBounds(70,60,qq.getIconWidth(),qq.getIconHeight());

            jPanel1 = new JPanel();
            jPanel2 = new JPanel();

            JLabel jLabel = new JLabel("请输入你的账号");
            jLabel.setFont(new Font("楷体",0,20));
            jLabel.setForeground(Color.blue);;
            jLabel.setBounds(165,20,400,60);

            JTextField jTextField = new JTextField(20);
            jTextField.setPreferredSize(new Dimension(100,30));
            JLabel jLabel1 = new JLabel("    注册账号");
            jLabel1.setFont(new Font("楷体",0,20));
            jLabel1.setForeground(Color.BLUE);

            JPasswordField passwordField = new JPasswordField();
            passwordField.setPreferredSize(new Dimension(220,30));
            JLabel jLabel2 = new JLabel("    找回密码");
            jLabel2.setFont(new Font("楷体",0,20));
            jLabel2.setForeground(Color.BLUE);

            jPanel1.add(jTextField);
            jPanel1.add(jLabel1);
            jPanel2.add(passwordField);
            jPanel2.add(jLabel2);
            jPanel1.setBounds(150,60,400,45);
            jPanel2.setBounds(150,100,400,100);

            Login = new JButton("  登录  ");
            Login.setBounds(220,145,80,50);

            this.add(Login);
            this.add(jPanel1);
            this.add(jPanel2);
            this.add(jLabel);
            this.add(img);

            Login.addActionListener(e -> {
                String account = jTextField.getText();
                String password = new String(passwordField.getPassword());
                System.out.println(account + "*---" + password);
                if (account.equals(name)){
                    new mainChat(new user(account,account,password));

            //        System.exit(0);
                }else{
                    JOptionPane.showMessageDialog(new JPanel(),"你输入的密码或者账号错误","请重新输入",JOptionPane.WARNING_MESSAGE);
                }

            });
        }

//        @Override
//        public void paint(Graphics g) {
//            super.paint(g);
//        this.setBackground(Color.BLACK);
//        }
    }
}
