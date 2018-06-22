package loggers;

import entity.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denys_Hladkyi on 6/22/2018.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
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


//    @Override
//    protected void init() throws IOException {
//        super.init();
//    }

    private void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
