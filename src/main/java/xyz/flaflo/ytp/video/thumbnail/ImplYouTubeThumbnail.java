package xyz.flaflo.ytp.video.thumbnail;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Represents a YouTube thumbnail
 *
 * @author Flaflo
 */
final class ImplYouTubeThumbnail implements YouTubeThumbnail {

    private final YouTubeThumbnailFormat format;
    private final Image image;

    /**
     * @param format the format
     * @param image the image
     */
    ImplYouTubeThumbnail(YouTubeThumbnailFormat format, Image image) {
        this.format = format;
        this.image = image;
    }
    
        /**
     * @param format the format
     * @param image the image url
     */
    ImplYouTubeThumbnail(YouTubeThumbnailFormat format, String image) throws MalformedURLException, IOException {
        this.format = format;
        this.image = ImageIO.read(new URL(image));
    }
    
    @Override
    public YouTubeThumbnailFormat getFormat() {
        return format;
    }

    @Override
    public Image getImage() {
        return image;
    }
}
