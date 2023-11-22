package dev.wallpaper.core.wallpaper;

import dev.wallpaper.core.image.editor.ImageEditor;
import dev.wallpaper.core.image.provider.ImageProvider;
import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.exceptions.CreateWallpaperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class Creator {

    public final static Logger logger = LoggerFactory.getLogger(Creator.class);

    @Autowired
    private ImageProvider imageProvider;
    @Autowired
    private ImageEditor imageEditor;

    private File image;

    public byte[] createWallpaper(String content, OrientationEnum orientation) throws CreateWallpaperException {
        try {
            logger.debug("Initializating wallpaper creation");
            this.loadImageFromProvider(orientation);
            imageEditor.writeContentIntoCenterOfImage(image, content);
            byte[] bytesFromFile = this.getBytesFromFile(image);
            image.delete();
            logger.debug("Finishing wallpaper creation");

            return bytesFromFile;
        } catch (Exception e) {
            throw new CreateWallpaperException(e);
        }
    }

    private void loadImageFromProvider(OrientationEnum orientation) throws Exception {
        String url = imageProvider.getImageUrl(orientation);
        image = imageProvider.download(url);
    }

    private byte[] getBytesFromFile(File file) throws Exception {
        return Files.readAllBytes(Path.of(file.getAbsolutePath()));
    }

}
