package base.loggers;

import base.entity.Event;

/**
 * Created by Denis on 19.06.2018.
 */
public interface EventLogger {
    void logEvent(Event event);
}
