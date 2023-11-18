package dev.wallpaper.domain.enums.provider;

import dev.wallpaper.domain.enums.OrientationEnum;

import java.io.File;

public interface IProvider {

    public String getImageUrlFromOrientation(OrientationEnum orientation);
    public File download(String url);

}
