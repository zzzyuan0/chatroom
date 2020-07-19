package mainFrame;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import static server.Server.userClient;

public class friendPanel extends JPanel implements Runnable{
    public static Map<String, JButton> map_friend = new HashMap<String, JButton>();
    @Override
    public void run() {
        while (true) {
            repaint();
            for (Map.Entry str :
                    userClient.entrySet()) {
                if (str.getValue() != null) {
                    if (!map_friend.containsKey(str.getKey().toString())) {
                        System.out.println(str.getKey().toString() + "----------*");
                        addFriend(str.getKey().toString());
                    }
                } else {
                    if (map_friend.containsKey(str.getKey().toString())) {
                        reFriend(str.getKey().toString());
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void addFriend(String friend){
        JButton jButton = new JButton(friend);
        map_friend.put(friend,jButton);
        this.add(jButton);
    }
    public void reFriend(String friend){
        this.remove(map_friend.get(friend));
        map_friend.remove(friend);
    }

    public static void main(String[] args) {
        System.out.println(userClient.size());
    }
}