package dev.wallpaper.core.image.provider.pexels;

import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.domain.model.ImageModel;
import kong.unirest.*;
import kong.unirest.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageUrlPexelsProvider {

    public final static Logger logger = LoggerFactory.getLogger(ImageUrlPexelsProvider.class);

    @Autowired
    private CuratedPexelsProvider curatedPexelsProvider;

    private List<ImageModel> images;

    public String getImageUrlFromOrientation(OrientationEnum orientation) throws Exception {
        JsonNode response =  curatedPexelsProvider.getJsonFromApi(this.getRandomPage());
        this.responseToModelList(response);
        String result = this.getFirstImageFromOrientation(orientation);
        this.validateFoundImage(result);

        return result;
    }

    private Integer getRandomPage() {
        return (int) (Math.random() * 100 + 1);
    }

    private void responseToModelList(JsonNode response) {
        logger.debug("converting response to model list");
        List<?> photos = response.getObject().getJSONArray("photos").toList();
        images = photos.stream().map( photo -> new ImageModel((JSONObject) photo)).collect(Collectors.toList());
    }

    private String getFirstImageFromOrientation(OrientationEnum orientation) {
        String result = null;

        for (ImageModel image : images) {
            // Verify orientation from images of Pexels API
            if (image.getOrientation().equals(orientation)) {
                logger.debug("founded image with requested orientation");
                result = image.getUrl();
                break;
            }
        }

        return result;
    }

    private void validateFoundImage(String result) throws Exception {
        if (result == null) {
            logger.error("No image found with this orientation");
            throw new Exception("No image found with this orientation");
        }
    }

}
