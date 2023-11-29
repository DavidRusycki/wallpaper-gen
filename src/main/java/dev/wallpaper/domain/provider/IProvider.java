package dev.wallpaper.domain.provider;

import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.domain.exceptions.ImageDownloadException;

import java.io.File;

public interface IProvider {

    public String getImageUrlFromOrientation(OrientationEnum orientation) throws Exception;
    public File download(String url) throws ImageDownloadException;

}
