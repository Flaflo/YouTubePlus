package xyz.flaflo.ytp.video.info;

import java.util.Date;

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
     * @return the video id
     */
    String getVideoId();

    /**
     * @return the url to video file
     */
    String getVideoFile();
}
