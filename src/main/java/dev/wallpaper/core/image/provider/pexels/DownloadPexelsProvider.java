package dev.wallpaper.core.image.provider.pexels;

import dev.wallpaper.exceptions.ImageDownloadException;
import kong.unirest.GetRequest;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class DownloadPexelsProvider {

    public final static Logger logger = LoggerFactory.getLogger(DownloadPexelsProvider.class);

    private byte[] bytesFromImage;

    public File download(String url) throws ImageDownloadException {
        try {
            this.getImageBytesFromPexelsApi(url);
            File image = Files.createTempFile("imageFromPexels", ".jpeg").toFile();
            image.deleteOnExit();
            Files.write(Path.of(image.getAbsolutePath()), bytesFromImage);

            return image;
        }
        catch (Exception e) {
            logger.error("Error while downloading image from Pexels API");
            throw new ImageDownloadException(e);
        }
    }

    private void getImageBytesFromPexelsApi(String url) {
        logger.debug("making request to get image bytes from Pexels API");
        GetRequest request = Unirest.get(url);
        logger.debug("Requesting: {} {}", request.getHttpMethod(), request.getUrl());
        bytesFromImage = request.asBytes().getBody();
    }

}
