package dev.wallpaper.api.rest;

import dev.wallpaper.core.wallpaper.Creator;
import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.exceptions.CreateWallpaperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wallpaper/make")
public class MakeWallpaperController {

    private final static Logger logger = LoggerFactory.getLogger(MakeWallpaperController.class);

    @Autowired
    private Creator creator;

    @GetMapping(produces = "image/jpeg")
    public ResponseEntity make(
            @RequestParam String content,
            @RequestParam OrientationEnum orientation
    ) throws CreateWallpaperException {
        logger.info("Received request to make a "+orientation+" wallpaper");
        byte[] imageBytes = creator.createWallpaper(content, orientation);

        return ResponseEntity.ok().body(imageBytes);
    }

}
