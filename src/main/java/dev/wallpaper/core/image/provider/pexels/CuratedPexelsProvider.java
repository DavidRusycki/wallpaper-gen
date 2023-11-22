package dev.wallpaper.core.image.provider.pexels;

import kong.unirest.GetRequest;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CuratedPexelsProvider {

    public final static Logger logger = LoggerFactory.getLogger(CuratedPexelsProvider.class);

    @Value("${pexels.key}")
    private String key;

    @Value("${pexels.per_page}")
    private Integer perPage;

    @Value("${pexels.url}")
    private String url;

    public JsonNode getJsonFromApi(Integer page) {
        logger.debug("making request to get list from Pexels API");

        GetRequest request = Unirest.get(url);
        request.queryString("per_page", perPage)
                .queryString("page", page)
                .header("Authorization", key);

        logger.debug("Requesting: {} {}", request.getHttpMethod(), request.getUrl());

        return request.asJson().getBody();
    }

}
