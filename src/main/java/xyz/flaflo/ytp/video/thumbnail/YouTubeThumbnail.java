package xyz.flaflo.ytp.video.thumbnail;

import java.awt.Image;

/**
 * Represents a YouTube thumbnail
 *
 * @author Flaflo
 */
public interface YouTubeThumbnail {

    /**
     * @return the youtube thumbnail format
     */
    YouTubeThumbnailFormat getFormat();
    
    /**
     * @return the thumbnail as image object
     */
    Image getImage();
}
