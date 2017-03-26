package core.experiment;

import core.Client;
import core.MapTest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


public class AutowireMapTest {

    @Autowired
    private MapTest mapTest;

    @Autowired
    private MapTest mapTest3;

    private MapTest mapTest4;

    private int count;

    @Resource(name = "${testBeanName}")
    public void setMapTest4(MapTest mapTest4) {
        this.mapTest4 = mapTest4;
    }

    public AutowireMapTest() {
    }

    @PostConstruct
    public void init(){
        System.out.println("MAPTEST: " + mapTest);
        System.out.println("MAPTEST3: " + mapTest3);
        System.out.println("MAPTEST4: " + mapTest4);
        System.out.println("count: " + count);
    }

    public static AutowireMapTest getInstance(){
        System.out.println("getInstance():");
        AutowireMapTest res =  new AutowireMapTest();
        res.count = 100;
        return res;
    }

    protected Client createClient(){
        throw new IllegalStateException("countainer couldn't create proxy");
    }

//    @Override
//    public String toString() {
//        return "AutowireMapTest{" +
//                "mapTest=" + mapTest +
//                ", mapTest3=" + mapTest3 +
//                ", mapTest4=" + mapTest4 +
//                ", count=" + count +
//                '}';
//    }
}
