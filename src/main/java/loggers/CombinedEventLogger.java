package loggers;

import entity.Event;

import java.util.List;

/**
 * Created by Denys_Hladkyi on 6/22/2018.
 */
public class CombinedEventLogger implements EventLogger {
    List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(final Event event) {
        loggers.forEach(logger -> logger.logEvent(event));
    }
}
