package core;

import lombok.Data;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static boolean isDay(){
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        if (hour >= 8 && hour < 17) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "\nEvent{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
