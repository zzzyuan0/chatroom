package sty;

import java.util.HashMap;
import java.util.Map;

public class sty {
    public static Map<Integer,String> stringMap = new HashMap<Integer,String>();
    public static int anInt ;

    public static void main(String[] args) {
        stringMap.put(1231,"4546456");
        anInt = 15465456;
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(stringMap.size());
        }
    }
}
