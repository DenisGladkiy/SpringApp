package loggers;

import entity.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Denys_Hladkyi on 6/22/2018.
 */
public class FileEventLogger implements EventLogger {
    protected String fileName;
    private File file;

    public FileEventLogger(String fileName){
        this.fileName = fileName;
    }

    protected void init() throws IOException {
        file = new File(fileName);
        if(!file.canWrite()){
            throw new IOException("Can't write to file");
        }

    }

    public void logEvent(Event event) {
        boolean append = true;
        try {
            FileUtils.writeStringToFile(file, event.toString(), append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
