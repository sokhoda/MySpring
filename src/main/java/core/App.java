package core;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Data
public class App {
    private EventLogger eventLogger;
    private Client client;

    public App(){}

    public App(EventLogger eventLogger, Client client) {
        this.eventLogger = eventLogger;
        this.client = client;
    }

    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring.xml");

        App app = ctx.getBean("app", core.App.class);

        Event event1 = ctx.getBean("event", Event.class);
        event1.setMessage("Some info for user 1");
        app.logEvent(event1);

        Event event2 = ctx.getBean("event", Event.class);
        event2.setMessage("Some info for user 2");
        app.logEvent(event2);
    }

    private void logEvent(Event event){
        String message = event.getMessage().replaceAll(client.getId().toString(), client
                .getFullName());
        event.setMessage(message);
        eventLogger.logEvent(event);
    }
}
