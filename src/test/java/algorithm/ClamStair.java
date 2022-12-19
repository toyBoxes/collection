package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClamStair {
    private static Map<Integer,Long> hm=new HashMap<>();
    private static Map<Integer,Integer> hs=new HashMap<>();


    public static long clamStair1(int n){
        if(n==1) return 1;
        if(n==2) return 2;
        if(null!=hm.get(n)){
            return  hm.get(n);
        }
        else{
            long result=clamStair1(n-1)+clamStair1(n -2);
            hm.put(n,result);
            return result;
        }
    }
    public static  long clamStairUp(int n){
        if(n==1) return 1;
        if(n==2) return 2;
        long result=0;
        long bef=1;
        long pre=2;
        for (int i = 3; i <=n ; i++) {
            result=bef+pre;
            bef=pre;
            pre=result;
        }
        return result;
    }



    public static void main(String[] args) {
        int n=100;
        Long b=clamStair1(n);
        long c=clamStairUp(n);
        System.out.println("爬上"+n+"层阶梯一共有" +b+"方法");
        System.out.println("爬上"+n+"层阶梯一共有" +c+"方法");


    }
}
