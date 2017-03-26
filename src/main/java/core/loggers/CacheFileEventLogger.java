package core.loggers;

import core.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        cache = new ArrayList<>();
        this.cacheSize = cacheSize;
    }

    private void destroy(){
        if (!cache.isEmpty()){
            writeEventsFromCache();
        }
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    public void someMethod(){}

    private void writeEventsFromCache() {
        cache.stream().forEach(super::logEvent);
    }

}
