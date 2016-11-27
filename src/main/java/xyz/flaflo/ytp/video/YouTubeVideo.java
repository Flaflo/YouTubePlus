package xyz.flaflo.ytp.video;

import xyz.flaflo.ytp.video.info.YouTubeVideoInfo;

/**
 * Represents a YouTube Video
 * @author Flaflo
 */
public interface YouTubeVideo {
    
    /**
     * @return the video id
     */
    String getVideoId();
    
    /**
     * @return the youtube video info
     */
    YouTubeVideoInfo getInfo();
}
