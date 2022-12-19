package algorithm;

import java.util.Arrays;

public class Fibonacci {
    //斐波那契数列

    public  static Long Fibo(int n){
        long result;
        if(n==0) return 0l;
        if(n==1) return 1l;
        if(n<0) return null;
        else{
            result=Fibo(n-1)+Fibo(n-2);
        }
        return  result;
    }

    public  static Long Fibo2(int n){
        long result=0l;
        if(n==0) return 0l;
        if(n==1) return 1l;
        long bef=0;
        long pre=1;
        if(n<0) return null;
        for (int i =2; i <=n ; i++) {
            result=bef+pre;
            bef=pre;
            pre=result;

        }
        return  result;
    }
    public static void main(String[] args) {
        System.out.println(Fibo(7));
        System.out.println(Fibo2(7));

    }
}
