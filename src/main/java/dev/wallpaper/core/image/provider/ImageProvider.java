package dev.wallpaper.core.image.provider;

import dev.wallpaper.core.image.provider.pexels.PexelsProvider;
import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.domain.exceptions.ImageDownloadException;
import dev.wallpaper.domain.provider.IProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ImageProvider {

    public final static Logger logger = LoggerFactory.getLogger(ImageProvider.class);

    @Autowired
    private IProvider provider;

    public String getImageUrl(OrientationEnum orientation) throws Exception {
        logger.debug("Getting image url from provider");
        return provider.getImageUrlFromOrientation(orientation);
    }

    public File download(String url) throws ImageDownloadException {
        logger.debug("Downloading image from provider");
        return provider.download(url);
    }

}
