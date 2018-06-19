/**
 * Created by Denis on 19.06.2018.
 */
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(String msg){
        System.out.println(msg);
    }
}
