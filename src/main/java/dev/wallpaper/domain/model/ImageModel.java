package dev.wallpaper.domain.model;

import dev.wallpaper.domain.enums.OrientationEnum;
import kong.unirest.json.JSONObject;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

@Data
public class ImageModel {

    public final static Logger logger = LoggerFactory.getLogger(ImageModel.class);

    private BigInteger id;
    private Integer width;
    private Integer height;
    private String url;
    private String alt;
    private OrientationEnum orientation;

    public ImageModel(JSONObject json) {
        this.id = json.getBigInteger("id");
        this.width = json.getInt("width");
        this.height = json.getInt("height");
        this.url = json.getJSONObject("src").getString("original");
        this.alt = json.getString("alt");
        this.loadOrientation();
    }

    private void loadOrientation() {
        if (this.isLandScape()) {
            this.orientation = OrientationEnum.LANDSCAPE;
        }
        if (this.isPortrait()) {
            this.orientation = OrientationEnum.PORTRAIT;
        }
    }

    public Boolean isLandScape() {
        return this.width > this.height;
    }

    public Boolean isPortrait() {
        return this.width < this.height;
    }

}
