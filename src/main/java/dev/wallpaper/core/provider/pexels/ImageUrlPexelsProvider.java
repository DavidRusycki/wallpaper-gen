package dev.wallpaper.core.provider.pexels;

import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.domain.model.ImageModel;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageUrlPexelsProvider {

    public final static Logger logger = LoggerFactory.getLogger(ImageUrlPexelsProvider.class);

    @Value("${pexels.key}")
    private String key;

    @Value("${pexels.per_page}")
    private Integer perPage;

    @Value("${pexels.url}")
    private String url;

    private List<ImageModel> images;

    public String getImageUrlFromOrientation(OrientationEnum orientation) throws Exception {
        String result = null;

        JsonNode response =  this.getImageListFromPexelsApi();
        this.responseToModelList(response);
        result = this.getFirstImageFromOrientation(orientation);
        this.validateFoundImage(result);

        return result;
    }

    private JsonNode getImageListFromPexelsApi() {
        logger.debug("making request to Pexels API");
        return Unirest.get(url)
                .queryString("per_page", perPage)
                .header("Authorization", key)
                .asJson()
                .getBody();
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
