package dev.wallpaper.core.provider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.domain.enums.provider.IProvider;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class PexelsProvider implements IProvider {

    @Value("${pexels.key}")
    private String key;

    @Value("${pexels.per_page}")
    private Integer perPage = 80;

    @Value("${pexels.url}")
    private String url;

    @Override
    public String getImageUrlFromOrientation(OrientationEnum orientation) {
        // Get images from Pexels API
        JsonNode response =  Unirest.get(url)
                                    .queryString("per_page", perPage)
                                    .header("Authorization", key)
                                    .asJson()
                                    .getBody();

        List<?> photos = response.getObject().getJSONArray("photos").toList();

        // Verify orientation from images of Pexels API
        if (orientation == OrientationEnum.LANDSCAPE) {
            // A largura tem que ser maior que a altura
            photos.size();
            System.out.println(photos.size());
        }
        else {
            // A largura tem que ser menor que a altura
            photos.size();
            System.out.println(photos.size());
        }

        return null;
    }

    @Override
    public File download(String url) {
        return null;
    }

}
