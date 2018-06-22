package loggers;

import entity.Event;

/**
 * Created by Denis on 19.06.2018.
 */
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event){
        System.out.println(event);
    }
}
