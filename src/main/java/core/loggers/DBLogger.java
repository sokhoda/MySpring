package core.loggers;

import core.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBLogger {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void logEvent(Event event){
        jdbcTemplate.update(
                "INSERT INTO t_event (id, msg) VALUES(?,?)",
                event.getId(),
                event.toString()
                );
    }
}
