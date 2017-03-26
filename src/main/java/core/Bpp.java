package core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class Bpp implements BeanPostProcessor {
    @Autowired
    private Client client;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
<<<<<<< HEAD
        if (bean.getClass() == MapTest.class)
        System.out.println("postProcessBeforeInitialization:: " + beanName);
=======
        if (bean.getClass() == MapTest.class) {
            System.out.println("postProcessBeforeInitialization:: " + beanName);
            System.out.println("client:: " + client);
        }
>>>>>>> origin/master
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
<<<<<<< HEAD
        if (bean.getClass() == MapTest.class)
        System.out.println("postProcessAfterInitialization:: " + beanName);
=======
        if (bean.getClass() == MapTest.class) {
            System.out.println("postProcessAfterInitialization:: " + beanName);
        }
>>>>>>> origin/master
        return bean;
    }
}
