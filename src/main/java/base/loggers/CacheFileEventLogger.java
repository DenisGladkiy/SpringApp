package base.loggers;

import base.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denys_Hladkyi on 6/22/2018.
 */

@Component("cacheFileEventLogger")
public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    @Autowired
    public CacheFileEventLogger(@Value("eventMessage") String fileName, @Value("3") int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<Event>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        for (Event e : cache){
            super.logEvent(e);
        }
    }

    @PreDestroy
    private void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
