package mainFrame;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class friendPanel extends JPanel implements Runnable{
    int pos_y = 0;
    public  Map<String, JButton> map_friend = new HashMap<String, JButton>();
    public static  Map<String, Integer> userClient = new HashMap<String, Integer>();
    public synchronized static Map<String, Integer> getUserClient() {
        return userClient;
    }
    public synchronized static void setUserClient(String str,Integer s) {
        if (!userClient.containsKey(str))  userClient.put(str,1);
        else userClient.replace(str,s);
    }
    @Override
    public void run() {
        while (true) {
            System.out.println(getUserClient().size() + "  -----");
            repaint();
            for (Map.Entry str :
                    getUserClient().entrySet()) {
                if (str.getValue() != null) {
                 //   System.out.println(str.getKey() + "-----------");
                    if (!map_friend.containsKey(str.getKey().toString())) {
                        addFriend(str.getKey().toString());
                    }
                } else {
                    if (map_friend.containsKey(str.getKey().toString())) {
                        reFriend(str.getKey().toString());
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void addFriend(String friend){
        JButton jButton = new JButton(friend);
        jButton.setBounds(10,pos_y,330,30);
        pos_y += 30;
        jButton.setPreferredSize(new Dimension(330,30));
        jButton.setFont(new Font("楷体", 1, 14));
        jButton.setForeground(Color.black);
        map_friend.put(friend,jButton);
        this.add(jButton);
    }
    public void reFriend(String friend){
        this.remove(map_friend.get(friend));
        pos_y -=30;
        map_friend.remove(friend);
    }
}