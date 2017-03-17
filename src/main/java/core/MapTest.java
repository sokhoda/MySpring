package core;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Data
public class MapTest implements InitializingBean, BeanNameAware, BeanFactoryAware{
    private Map<String, List<String>> map;

    public MapTest(Map<String, List<String>> map) {
        this.map = map;
    }

    @PostConstruct
    private void postConstr(){
        System.out.println("MapTest::postConstruct()");
    }

    public void init(){
        System.out.println("MapTest::init()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MapTest::afterPropertiesSet");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name::" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Factory:: " + beanFactory);
    }
}
