package config;

import entity.Student;
import entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("entity")
public class BeanConfig {
    @Bean
    public User user(){
        return new User();

    }
    @Bean
    public Student student(){
        return new Student();
    }
}
