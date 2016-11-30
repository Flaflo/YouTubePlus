package xyz.flaflo.ytp.video.info;

import java.util.Date;
import xyz.flaflo.ytp.video.thumbnail.YouTubeThumbnailContainer;

/**
 * Stores informations about a YouTube video
 *
 * @author Flaflo
 */
public interface YouTubeVideoInfo {

    /**
     * @return the title
     */
    String getTitle();
    
    /**
     * @return the publish date
     */
    Date getPublishedAt();
    
    /**
     * @return the uploader channel id
     */
    String getChannelId();
    
    /**
     * @return the video description
     */
    String getDescription();
    
    /**
     * @return the video tags
     */
    String[] getTags();

    /**
     * @return a container with all thumbnail formats
     */
    YouTubeThumbnailContainer getThumbnailContainer();
    
    /**
     * @return the video id
     */
    String getVideoId();

}
