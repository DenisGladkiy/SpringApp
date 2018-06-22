import entity.Client;
import entity.Event;
import loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by Denis on 19.06.2018.
 */
public class App {
    private Client client;
    private loggers.EventLogger EventLogger;

    public App(Client client, Map<EventType,EventLogger> EventLoggers) {
        this.client = client;
        this.EventLogger = EventLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App)context.getBean("app");
        Event event = (Event)context.getBean("event");
        for(int i = 0; i < 12; i++)
        app.logEvent(event);
        //app.logEvent("entity.Event for user 2");
        context.close();
    }

    public void logEvent(Event event){
        String message = event.getMsg().replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        EventLogger.logEvent(event);
    }
}
