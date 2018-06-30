package base.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Denis on 19.06.2018.
 */
@Component("client")
public class Client {
    private String id;
    private String fullName;
    private String greeting;

    public Client(){}

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    @Value("${id}")
    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    @Value("${name}")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    @Value("${greeting}")
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
