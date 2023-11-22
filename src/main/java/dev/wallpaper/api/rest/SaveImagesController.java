package dev.wallpaper.api.rest;

import dev.wallpaper.core.image.appender.LocalImageAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveImagesController {

    @Autowired
    private LocalImageAppender appender;

    public void save() {
        appender.append();
    }

}
