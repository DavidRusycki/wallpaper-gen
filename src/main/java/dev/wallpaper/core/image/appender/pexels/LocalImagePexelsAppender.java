package dev.wallpaper.core.image.appender.pexels;

import dev.wallpaper.core.image.provider.pexels.CuratedPexelsProvider;
import dev.wallpaper.core.writer.DiskWriter;
import dev.wallpaper.domain.appender.IAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalImagePexelsAppender implements IAppender {

    @Value("${local-appender.path}")
    private String path;

    @Autowired
    private CuratedPexelsProvider provider;

    @Autowired
    private DiskWriter writer;

    @Override
    public void append() {

        writer.setPath(path.concat("/json"));
        writer.setFileExtension(".json");

        for (Integer page = 1; page <= 100; page++) {
            var json = provider.getJsonFromApi(page);
            String fileName = "page_".concat(page.toString()).concat("_");

            writer.writeNewFile(fileName, json.toPrettyString());
        }

    }

}
