package xyz.flaflo.ytp.video.thumbnail;

/**
 * A container for YouTube thumbnails
 *
 * @author Flaflo
 */
public interface YouTubeThumbnailContainer {

    /**
     * @param format the format
     * @return thumbnail of a YouTube video by its format
     */
    public YouTubeThumbnail getThumbnail(YouTubeThumbnailFormat format);
}
