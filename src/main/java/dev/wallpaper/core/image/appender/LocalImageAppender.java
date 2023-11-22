package dev.wallpaper.core.image.appender;

import dev.wallpaper.core.image.appender.pexels.LocalImagePexelsAppender;
import dev.wallpaper.domain.provider.IProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalImageAppender {

    @Autowired
    private LocalImagePexelsAppender appender;

    public void append() {
        appender.append();
    }

}
