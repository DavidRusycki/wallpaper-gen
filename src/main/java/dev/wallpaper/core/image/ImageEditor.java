package dev.wallpaper.core.image;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Data
@Component
public class ImageEditor {

    public final static Logger logger = LoggerFactory.getLogger(ImageEditor.class);

    private BufferedImage bufferedImage;
    private Font font;
    private String fontName = "Arial";
    private Graphics graphics;
    private FontMetrics metrics;
    private String format = "jpeg";

    private Integer positionX;
    private Integer positionY;

    public void writeContentIntoCenterOfImage(File image, String content) throws Exception {
        logger.debug("initializating write content into center of image");
        this.loadBufferedImage(image);
        this.loadFontWithDinamicImageSize();
        this.loadGraphics();
        graphics.setFont(font);
        this.loadMetrics();
        this.calculatePositionToCenterContent(content);
        this.drawContentIntoImage(content);
        this.writeContentImageIntoFile(image);
        logger.debug("finishing write content into center of image");
    }

    private void loadBufferedImage(File image) throws Exception {
        bufferedImage = ImageIO.read(image);
    }

    private void loadFontWithDinamicImageSize() {
        font = new Font(fontName, Font.BOLD, this.getFontSizeFromImageWidth());
    }

    private Integer getFontSizeFromImageWidth() {
        return (int) (((bufferedImage.getWidth()/100)*1.5)+30);
    }

    private void loadGraphics() {
        graphics = bufferedImage.getGraphics();
    }

    private void loadMetrics() {
        metrics = graphics.getFontMetrics(font);
    }

    private void calculatePositionToCenterContent(String content) {
        positionX = (bufferedImage.getWidth() - metrics.stringWidth(content)) / 2;
        positionY = ((bufferedImage.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
    }

    private void drawContentIntoImage(String content) {
        graphics.drawString(content, positionX, positionY);
        graphics.dispose();
    }

    private void writeContentImageIntoFile(File image) throws Exception {
        ImageIO.write(bufferedImage, format, image);
    }

}
