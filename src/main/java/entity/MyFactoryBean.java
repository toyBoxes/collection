package entity;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean {
    private String Tips="this is FactoryBean";

    public String getTips() {
        return Tips;
    }

    public void setTips(String tips) {
        Tips = tips;
    }

    @Override
    public Object getObject() throws Exception {
        return new Student();
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }
}
