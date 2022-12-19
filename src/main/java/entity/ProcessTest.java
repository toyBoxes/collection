package entity;

import config.BeanConfig;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ProcessTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(BeanConfig.class);


        Student student=ac.getBean("student",Student.class);

        System.out.println(student);
        System.out.println(System.getenv().toString());
    }
}
