import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Denis on 19.06.2018.
 */
public class App {
    private Client client;
    private EventLogger EventLogger;

    public App(Client client, EventLogger EventLogger) {
        this.client = client;
        this.EventLogger = EventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App)context.getBean("app");
        app.logEvent("Event for user 1");
        app.logEvent("Event for user 2");
    }

    public void logEvent(String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        EventLogger.logEvent(message);
    }
}
