package entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

@Order
public class Person {
    @Autowired
    private String Name;

}
