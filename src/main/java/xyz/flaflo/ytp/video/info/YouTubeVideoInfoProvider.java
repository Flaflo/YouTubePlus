package xyz.flaflo.ytp.video.info;

import java.io.IOException;
import java.text.ParseException;

/**
 * Provides YouTube video informations
 *
 * @author Flaflo
 */
public final class YouTubeVideoInfoProvider {

    private String apiKey;

    /**
     * @param apiKey the google api key
     */
    public YouTubeVideoInfoProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Provides a YouTube video info
     *
     * @param videoId the video id
     * @return the YouTube video info
     */
    public YouTubeVideoInfo provideYouTubeVideoInfo(String videoId) throws ParseException, IOException {
        final ImplYouTubeVideoInfo videoInfo = new ImplYouTubeVideoInfo(videoId);
        videoInfo.parse(apiKey);

        return videoInfo;
    }

    /**
     * Provides a YouTube video info by a json string
     *
     * @param json the json string
     * @return the YouTube video info
     */
    public YouTubeVideoInfo provideYouTubeVideoInfoFromJson(String json) throws ParseException, IOException {
        final ImplYouTubeVideoInfo videoInfo = new ImplYouTubeVideoInfo();
        videoInfo.parse(apiKey, json);

        return videoInfo;
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
