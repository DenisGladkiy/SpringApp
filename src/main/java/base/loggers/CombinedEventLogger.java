package base.loggers;

import base.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Denys_Hladkyi on 6/22/2018.
 */
@Component
public class CombinedEventLogger implements EventLogger {

    private List<EventLogger> loggers;

    @Autowired
    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(final Event event) {
        loggers.forEach(logger -> logger.logEvent(event));
    }
}
