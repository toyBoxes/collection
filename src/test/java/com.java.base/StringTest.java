package com.java.base;

import java.util.Arrays;

public class StringTest {
    public static void main(String[] args) {
        String s1="HelloWorld";
        String s2=new String("HelloWorld");
        String s3="Hello";
        String s4="World";
        String s5="Hello"+"World";
        String s6=s3+s4;
        String s7=new String("Hello")+new String("World");
        String s8=new String("Hello")+new String("Mike");
        String s9=new String("ja")+new String("va");

        System.out.println(s1==s2);//堆地址与常量池对比 false
        System.out.println(s1==s5);//直接加入常量池，因前面s1字符串已加入常量池，创建0个对象 true
        System.out.println(s1==s6);//本质调用StringBuilder.append().toString()生成新的堆对象，常量池与堆地址对比 false
        System.out.println(s1==s6.intern());//true 常量池与常量池对比
        System.out.println(s2==s2.intern());//false 堆地址与常量池对比
        System.out.println(s7==s7.intern());//false 堆地址与常量池对比
        System.out.println(s8==s8.intern());//true 堆地址与堆地址对比
        // 有一个初始化的java字符串(JDK出娘胎自带的)，在加载sun.misc.Version这个类的时候进入常量池。
        System.out.println(s9==s9.intern());//false 堆地址与常量池对比


    }
}
