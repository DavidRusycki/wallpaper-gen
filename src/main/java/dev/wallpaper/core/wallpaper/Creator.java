package dev.wallpaper.core.wallpaper;

import dev.wallpaper.core.image.ImageEditor;
import dev.wallpaper.core.image.ImageProvider;
import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.exceptions.CreateWallpaperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class Creator {

    @Autowired
    private ImageProvider imageProvider;
    @Autowired
    private ImageEditor imageEditor;

    private File image;

    public byte[] createWallpaper(String content, OrientationEnum orientation) throws CreateWallpaperException {
        try {
            this.loadImageFromProvider(orientation);
            imageEditor.writeContentIntoCenterOfImage(image, content);

            return this.getBytesFromFile(image);
        } catch (Exception e) {
            throw new CreateWallpaperException(e);
        }
    }

    private void loadImageFromProvider(OrientationEnum orientation) {
        String url = imageProvider.getImageUrl(orientation);
        image = imageProvider.download(url);
    }

    private byte[] getBytesFromFile(File file) throws Exception {
        return Files.readAllBytes(Path.of(file.getAbsolutePath()));
    }

}
