package core.loggers;

import core.Event;
import lombok.Data;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Data
public class FileEventLogger implements EventLogger{
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void init() throws IOException{
        file = new File(filename);
        if (!file.canWrite()) throw  new IOException();
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
