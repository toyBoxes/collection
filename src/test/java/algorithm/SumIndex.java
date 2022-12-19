package algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SumIndex {


    public static ArrayList<Integer> numIndex(int[]nums, int sum){
        ArrayList<Integer> result=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j]==sum){
                    result.add(i);
                    result.add(j);

                }

            }
        }
        return  result;
    }
    public static ArrayList<Integer> numIndex2(int[]nums, int sum){//假设数组袁元素各不相同
        ArrayList<Integer> result=new ArrayList<>();
        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another=sum-nums[i];
            if(hm.containsKey(another)){
                result.add(i);
                result.add(hm.get(another));
            }else{
                hm.put(nums[i],i);
            }

        }
        return result;
    }
    public static void main(String[] args) {

        int [] a={2,7,3,5,8,11,20,22,32,42,0};
        System.out.println("数组下标为: " + numIndex(a,8));
        System.out.println("数组下标为: " + numIndex2(a,8));

    }
}
