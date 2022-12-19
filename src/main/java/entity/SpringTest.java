package entity;

import config.BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

public class SpringTest {
    public static void main(String[] args) {
        //1.ClassPathXmlApplicationContext 只支持相对路径,可刷新,调用refresh重新初始化对象
        ClassPathXmlApplicationContext  cxc=new ClassPathXmlApplicationContext("spring.xml");
        System.out.println(cxc.getBean("user"));
        cxc.refresh();
        System.out.println(cxc.getBean("user"));
        //2.AnnotationConfigApplicationContext 通过@Bean实现bean注入，与@Component和@ComponentScan无关，不可刷新
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(BeanConfig.class);
        System.out.println(ac.getBean("user"));
        System.out.println(ac.getBean("student",Student.class));

        //ac.refresh();
        //ac.getBean("student",Student.class);
        //C:\Users\12052320\IdeaProjects\collection\src\main\resources\spring.xml
        //src/main/resources/spring.xml
        //3.FileSystemXmlApplicationContext 支持绝对路径和相对路径,可刷新
        FileSystemXmlApplicationContext fc=new FileSystemXmlApplicationContext("src/main/resources/spring.xml");
        User user=fc.getBean("user",User.class);
        fc.refresh();
        System.out.println(user);

    }
}
