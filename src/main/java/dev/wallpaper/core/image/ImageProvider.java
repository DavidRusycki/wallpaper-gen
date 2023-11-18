package dev.wallpaper.core.image;

import dev.wallpaper.core.provider.PexelsProvider;
import dev.wallpaper.domain.enums.OrientationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ImageProvider {

    @Autowired
    private PexelsProvider provider;

    public String getImageUrl(OrientationEnum orientation) {
        return provider.getImageUrlFromOrientation(orientation);
    }

    public File download(String url) {
        return provider.download(url);
    }

}
