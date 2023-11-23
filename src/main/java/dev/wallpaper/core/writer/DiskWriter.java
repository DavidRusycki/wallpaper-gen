package dev.wallpaper.core.writer;

import dev.wallpaper.api.rest.MakeWallpaperController;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
@Component
public class DiskWriter {

    private final static Logger logger = LoggerFactory.getLogger(DiskWriter.class);

    private String fileExtension;
    private String path;

    public void writeNewFile(String fileName, String content) {

        try {
            Path realPath = Path.of(path);
            if (!Files.isDirectory(realPath)) {
                Files.createDirectory(realPath);
            }

            File file = Files.createTempFile(realPath, fileName, fileExtension).toFile();
            var FileOutputStream = new FileOutputStream(file);
            FileOutputStream.write(content.getBytes());
            FileOutputStream.close();
            file = null;
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("Error while writing file: {}", e.getMessage());
        }

    }

}
