package base.loggers;

import base.entity.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by Denys_Hladkyi on 6/22/2018.
 */
@Component("fileEventLogger")
public class FileEventLogger implements EventLogger {
    protected String fileName;
    private File file;

    @Autowired
    public FileEventLogger(@Value("eventMessage") String fileName){
        this.fileName = fileName;
    }

    @PostConstruct
    protected void init() throws IOException {
        file = new File(fileName);
        file.createNewFile();
        if(!file.canWrite()){
            throw new IOException("Can't write to file");
        }

    }

    public void logEvent(Event event) {
        boolean append = true;
        try {
            FileUtils.writeStringToFile(file, event.toString() +"\n", append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
