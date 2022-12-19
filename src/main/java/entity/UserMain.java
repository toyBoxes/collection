package entity;

import config.BeanConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class UserMain {
    public static void main(String[] args) {
        //一.声明式
        //1通过<bean></bean>标签配置SpringBean
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
        User user=context.getBean("user",User.class);
        System.out.println(user.getAge());
        //2通过@Bean注解
        AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext(BeanConfig.class);
        User user1=configApplicationContext.getBean("user",User.class);
        System.out.println(user1.getAge());
        //3通过@ComponentScan和@Component注解生成SpringBean
        AnnotationConfigApplicationContext configApplicationContext1=new AnnotationConfigApplicationContext(BeanConfig.class);
        Student student=configApplicationContext1.getBean("student",Student.class);
        System.out.println(student.getAge());

        //编程式
        //1.通过BeanDefinition,前三者本质都是通过beanDefinition实现
        AnnotationConfigApplicationContext configApplicationContext2=new AnnotationConfigApplicationContext();
        AbstractBeanDefinition beanDefinition= BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(Student.class);

        //beanDefinition.set

        configApplicationContext2.registerBeanDefinition("Student",beanDefinition);
        configApplicationContext2.refresh();
        Student student3=configApplicationContext2.getBean("Student",Student.class);
        System.out.println(student3.getAge());

        //FactoryBean 实际生成两个对象MyFactoryBean,Student
        AnnotationConfigApplicationContext configApplicationContext3=new AnnotationConfigApplicationContext();
        AbstractBeanDefinition beanDefinition1= BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition1.setBeanClass(MyFactoryBean.class);

        configApplicationContext3.registerBeanDefinition("Student",beanDefinition1);
        configApplicationContext3.refresh();
        Student student4=configApplicationContext3.getBean("Student",Student.class);//Student:Student
        MyFactoryBean myFactoryBean=configApplicationContext3.getBean("&Student", MyFactoryBean.class);//&Student:MyFactoryBean

        System.out.println(student4.getAge());
        System.out.println(myFactoryBean.getTips());

        User user2=context.getBean("user1",User.class);
        //单例池，bean默认为单例的，scope为singleton(单例) scope为prototype（原型)
        System.out.println(context.getBean("user",User.class));
        System.out.println(context.getBean("user",User.class));
        System.out.println(context.getBean("user1",User.class));
        System.out.println(context.getBean("user1",User.class));

        //什么是BeanFactory,用来管理bean的生命周期工厂类,例如注册bean和获取bean，销毁bean
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //beanFactory.registerBeanDefinition("student",beanDefinition);
        beanFactory.registerSingleton("student",new Student());
        beanFactory.registerSingleton("user",new User());
        //beanFactory.destroySingleton("student");
        Student student2=beanFactory.getBean("student",Student.class);
        User user3=beanFactory.getBean("user",User.class);
        System.out.println(student2);
        System.out.println(user3);


    }
}
