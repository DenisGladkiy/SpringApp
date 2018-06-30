package base.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Denys_Hladkyi on 6/22/2018.
 */
@Component
@Scope("prototype")
public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat dateFormat;

    @Autowired
    public Event(Date date, DateFormat dateFormat){
        id = new Random().nextInt(100);
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public String getMsg() {
        return msg;
    }

    @Value("Event for user 1")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "entity.Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
