package sty;

import java.util.HashMap;
import java.util.Map;

public class sty {
    public static Map<Integer,String> stringMap = new HashMap<Integer,String>();
    public static void main(String[] args) {
        stringMap.put(1,"张三");
        while (true){
            try {
                System.out.println(stringMap.size());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
