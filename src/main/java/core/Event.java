package core;

import lombok.Data;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

@Data
public class Event {
    private final static Random rnd;
    private Integer id;
    private String message;
    private Date date;
    private DateFormat df;

    static {
        rnd = new Random();
    }

    {
        id = rnd.nextInt(100);
    }

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public Event(){}

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
