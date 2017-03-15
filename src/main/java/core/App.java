package core;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Data
public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

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
        System.out.println(app.client);
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

        ctx.close();
    }

    private void logEvent(EventType type, Event event){
        String message = event.getMessage().replaceAll(client.getId().toString(), client
                .getFullName());
        event.setMessage(message);
        EventLogger logger = loggers.get(type);
        if (logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
