package base;

import base.config.AnnotationConfig;
import base.entity.Client;
import base.entity.Event;
import base.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Denis on 19.06.2018.
 */
@Component("app")
public class App {

    private Client client;
    private EventLogger defaultEventLogger;
    private Map<EventType,EventLogger> eventLoggers;

    public App(){}

    public App(Client client, EventLogger defaultEventLogger, Map<EventType,EventLogger> eventLoggers) {
        this.client = client;
        this.defaultEventLogger = defaultEventLogger;
        this.eventLoggers = eventLoggers;
    }

    public static void main(String[] args) {
        //ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        App app = (App)context.getBean("app");
        //Event event = (Event)context.getBean("event");
        for(int i = 0; i < 3; i++) {
            app.logEvent(null, (Event)context.getBean("event"));
        }
        app.logEvent(EventType.INFO, (Event)context.getBean("event"));
        app.logEvent(EventType.ERROR, (Event)context.getBean("event"));
        context.close();
    }

    public void logEvent(EventType type, Event event){
        String message = event.getMsg().replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        EventLogger logger = eventLoggers.get(type);
        if(logger == null){
            logger = defaultEventLogger;
        }
        logger.logEvent(event);
    }

    @Autowired
    public void setClient(Client client) {
        this.client = client;
    }

    @Autowired
    @Qualifier("cacheFileEventLogger")
    public void setDefaultEventLogger(EventLogger defaultEventLogger) {
        this.defaultEventLogger = defaultEventLogger;
    }

    //@Autowired
    @Resource(name = "loggerMap")
    public void setEventLoggers(Map<EventType, EventLogger> eventLoggers) {
        this.eventLoggers = eventLoggers;
    }
}
