package dev.wallpaper.api.rest;

import dev.wallpaper.core.image.appender.LocalImageAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/save")
public class SaveImagesController {

    @Autowired
    private LocalImageAppender appender;

    @GetMapping
    public void save() {
        appender.append();
    }

}
