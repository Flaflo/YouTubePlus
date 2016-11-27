package xyz.flaflo.ytp.video;

/**
 * Represents a parser for YouTube videos
 * @author Flaflo
 */
public final class YouTubeVideoParser {
    
    private String apiKey;

    /**
     * @param apiKey the google api key
     */
    public YouTubeVideoParser(String apiKey) {
        this.apiKey = apiKey;
    }
    
    /**
     * Parses a YouTube video
     * @param videoId the video id
     * @return the YouTube video
     */
    public YouTubeVideo parseYouTubeVideo(String videoId) {
        final ImplYouTubeVideo video = new ImplYouTubeVideo(videoId);
        video.parse(apiKey);
        
        return video;
    }

    /**
     * @return the google api key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey the google api key
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}