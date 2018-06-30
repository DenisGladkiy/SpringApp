package base.loggers;

import base.entity.Event;
import org.springframework.stereotype.Component;

/**
 * Created by Denis on 19.06.2018.
 */

@Component("consoleEventLogger")
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event){
        System.out.println(event);
    }
}
