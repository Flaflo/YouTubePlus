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
    public YouTubeVideoInfoProvider(final String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Provides a YouTube video info
     *
     * @param videoId the video id
     * @return the YouTube video info
     * @throws java.text.ParseException
     * @throws java.io.IOException
     */
    public YouTubeVideoInfo provideYouTubeVideoInfo(final String videoId) throws ParseException, IOException {
        final ImplYouTubeVideoInfo videoInfo = new ImplYouTubeVideoInfo(videoId);
        videoInfo.parse(apiKey);

        return videoInfo;
    }

    /**
     * Provides a YouTube video info by a json string
     *
     * @param json the json string
     * @return the YouTube video info
     * @throws java.text.ParseException
     * @throws java.io.IOException
     */
    public YouTubeVideoInfo provideYouTubeVideoInfoFromJson(final String json) throws ParseException, IOException {
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
    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }
}
