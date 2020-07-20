package sty;
import java.util.HashMap;
import java.util.Map;

/**
 * 问题描述：
 * 我需要在sty类中修改本类中的stringMap
 * 但是sty中修改后，dw类中的Map并没有改变
 * 因此我要怎么修改才能达到在另一个类中修改本类中的变量
 * 并且修改是生效的
 */
public class dw {
    public static Map<Integer,String> stringMap = new HashMap<Integer,String>();
    public static void main(String[] args) {
        while (true){
            try {
                System.out.println(stringMap.size());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
