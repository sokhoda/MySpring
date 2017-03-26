package core;

import core.experiment.AutowireMapTest;
import core.experiment.ClientManager;
import core.loggers.DBLogger;
import core.loggers.EventLogger;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.ArrayList;
=======
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.jsp.tagext.BodyTagSupport;
>>>>>>> origin/master
import java.util.Map;

@Data
public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;
    @Autowired
    private StatisticsAspect statisticsAspect;

    @Autowired
    private DBLogger dbLogger;
    @Autowired
    private MapTest mapTest;

    public App(){}

    public App(Client client, EventLogger defaultLogger, Map<EventType,
            EventLogger> loggers) {
        this.defaultLogger = defaultLogger;
        this.client = client;
        this.loggers = loggers;
    }

    public App(Client client, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.loggers = loggers;
    }

    public App(Map<EventType, EventLogger> loggers) {
        this.loggers = loggers;
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");

        App app = ctx.getBean("app", core.App.class);
//        ApplicationEvent
        AutowireMapTest autowireMapTest = ctx.getBean("autowireMapTest",
                AutowireMapTest.class);

        ClientManager clientManager = ctx.getBean("clientManager", ClientManager.class);
        System.out.println("clientManager:" + clientManager);

        Client lookupClient = ctx.getBean("lookupClient", Client.class);
        System.out.println("lookupClient:" + lookupClient);

        System.out.println(app.getClient());
        Event event1 = ctx.getBean("event", Event.class);
        event1.setMessage("Some info for user 1");
        app.logEvent(EventType.ERROR, event1);

        Event event2 = ctx.getBean("event", Event.class);
        event2.setMessage("Some info for user 2");
        app.logEvent(EventType.INFO, event2);

        Event event4 = ctx.getBean("event", Event.class);
        event4.setMessage("Some info for user 4");
        app.logEvent(null, event4);

        Event event5 = ctx.getBean("event", Event.class);
        event5.setMessage("Some info for user 5");
        app.logEvent(null, event5);

        System.out.println("STATISTICS:\n" + app.statisticsAspect.getPointCutMethExecCount());

        ctx.close();
    }

    private void logEvent(EventType type, Event event){
        String message = event.getMessage().replaceAll(client.getId().toString(), client.getFullName());
        event.setMessage(message);
        EventLogger logger = loggers.get(type);
        if (logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
//        dbLogger.logEvent(event);
    }
}
