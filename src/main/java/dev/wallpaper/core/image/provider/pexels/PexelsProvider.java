package dev.wallpaper.core.image.provider.pexels;

import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.domain.provider.IProvider;
import dev.wallpaper.domain.exceptions.ImageDownloadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PexelsProvider implements IProvider {

    @Autowired
    private ImageUrlPexelsProvider imageUrlProvider;
    @Autowired
    private DownloadPexelsProvider downloadProvider;

    @Override
    public String getImageUrlFromOrientation(OrientationEnum orientation) throws Exception {
        return imageUrlProvider.getImageUrlFromOrientation(orientation);
    }

    @Override
    public File download(String url) throws ImageDownloadException {
        return downloadProvider.download(url);
    }

}
