package base.config;

import base.EventType;
import base.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.*;

/**
 * Created by Denis on 30.06.2018.
 */
@Configuration
@ComponentScan(basePackages = "base")
@PropertySource("classpath:client.properties")
public class AnnotationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap(@Qualifier("consoleEventLogger") EventLogger infoLogger,
                                                 @Qualifier("combinedEventLogger") EventLogger errorLogger){
        Map<EventType, EventLogger> map = new HashMap<>();
        map.put(EventType.INFO, infoLogger);
        map.put(EventType.ERROR, errorLogger);
        return map;
    }

    @Bean
    public List<EventLogger> loggerList(@Qualifier("consoleEventLogger") EventLogger firstLogger,
                                        @Qualifier("fileEventLogger") EventLogger secondLogger){
        List<EventLogger> list = new ArrayList<>();
        list.add(firstLogger);
        list.add(secondLogger);
        return list;
    }

    @Bean
    public Date date(){
        return new Date();
    }

    @Bean
    public DateFormat dateFormat(){
        return DateFormat.getDateTimeInstance();
    }

   /* <util:map id="loggerMap" map-class="java.util.HashMap" key-type="base.EventType">
        <entry key="INFO" value-ref="consoleEventLogger"/>
        <entry key="ERROR" value-ref="combinedEventLogger"/>
    </util:map>*/
}
