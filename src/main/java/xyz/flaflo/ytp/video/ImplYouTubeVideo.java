package xyz.flaflo.ytp.video;

import java.io.IOException;
import java.text.ParseException;
import xyz.flaflo.ytp.video.info.YouTubeVideoInfo;
import xyz.flaflo.ytp.video.info.YouTubeVideoInfoProvider;

/**
 * Represents a YouTube Video
 *
 * @author Flaflo
 */
final class ImplYouTubeVideo implements YouTubeVideo {

    private final String videoId;
    private YouTubeVideoInfo info;

    /**
     * @param videoId the video id
     */
    ImplYouTubeVideo(final String videoId) {
        this.videoId = videoId;
    }

    /**
     * Parses the video informations
     *
     * @param key the google api key
     */
    void parse(final String key) throws ParseException, IOException {
        final YouTubeVideoInfoProvider infoProvider = new YouTubeVideoInfoProvider(key);
        this.info = infoProvider.provideYouTubeVideoInfo(this.videoId);
    }

    /**
     * Parses the video informations
     *
     * @param key the google api key
     * @param json the json string
     */
    void parse(final String key, final String json) throws ParseException, IOException {
        final YouTubeVideoInfoProvider infoProvider = new YouTubeVideoInfoProvider(key);
        this.info = infoProvider.provideYouTubeVideoInfoFromJson(json);
    }

    @Override
    public String getVideoId() {
        return videoId;
    }

    @Override
    public YouTubeVideoInfo getInfo() {
        return info;
    }
}
