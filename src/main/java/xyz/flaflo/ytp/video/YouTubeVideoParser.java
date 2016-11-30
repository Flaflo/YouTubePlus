package xyz.flaflo.ytp.video;

import java.io.IOException;
import java.text.ParseException;

/**
 * Represents a parser for YouTube videos
 *
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
     *
     * @param videoId the video id
     * @return the YouTube video
     * @throws java.text.ParseException
     * @throws java.io.IOException
     */
    public YouTubeVideo parseYouTubeVideo(String videoId) throws ParseException, IOException {
        final ImplYouTubeVideo video = new ImplYouTubeVideo(videoId);
        video.parse(apiKey);

        return video;
    }
    
        /**
     * Parses a YouTube video
     *
     * @param videoId the video id
     * @param json the json string
     * @return the YouTube video
     * @throws java.text.ParseException
     * @throws java.io.IOException
     */
    public YouTubeVideo parseYouTubeVideoFromJson(String videoId, String json) throws ParseException, IOException {
        final ImplYouTubeVideo video = new ImplYouTubeVideo(videoId);
        video.parse(apiKey, json);

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
