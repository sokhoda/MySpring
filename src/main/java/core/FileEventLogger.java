package core;

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
        file = new File(filename);
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
