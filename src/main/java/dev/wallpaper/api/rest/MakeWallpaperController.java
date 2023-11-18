package dev.wallpaper.api.rest;

import dev.wallpaper.core.wallpaper.Creator;
import dev.wallpaper.domain.enums.OrientationEnum;
import dev.wallpaper.exceptions.CreateWallpaperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wallpaper/make")
public class MakeWallpaperController {

    @Autowired
    private Creator creator;

    @GetMapping
    public ResponseEntity make(
            @RequestParam String content,
            @RequestParam OrientationEnum orientation
    ) throws CreateWallpaperException {
        creator.createWallpaper(content, orientation);

        return ResponseEntity.ok().build();
    }

}
