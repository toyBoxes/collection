package entity;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class MyBeanDefinitionReader implements BeanDefinitionReader {
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return null;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return null;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return null;
    }

    @Override
    public BeanNameGenerator getBeanNameGenerator() {
        return null;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(String s) throws BeanDefinitionStoreException {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(String... strings) throws BeanDefinitionStoreException {
        return 0;
    }
}
