package core;

import lombok.Data;

import java.util.List;

@Data
public class CombinedEventLogger implements EventLogger{
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(e -> e.logEvent(event));
    }
}
