package xyz.flaflo.ytp.video.thumbnail;

import java.awt.Image;
import java.io.IOException;

/**
 * Parses YouTube thumbnails
 *
 * @author Flaflo
 */
public final class YouTubeThumbnailParser {

    private String apiKey;

    /**
     * @param apiKey the google api key
     */
    public YouTubeThumbnailParser(final String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @param format the thumbnail format
     * @param imageUrl the direct url to the image
     * @return the generated YouTubeThumbnail object
     * @throws IOException
     */
    public YouTubeThumbnail createThumbnail(final YouTubeThumbnailFormat format, final String imageUrl) throws IOException {
        return new ImplYouTubeThumbnail(format, imageUrl);
    }
    
        /**
     * @param format the thumbnail format
     * @param image the image to create from
     * @return the generated YouTubeThumbnail object
     * @throws IOException
     */
    public YouTubeThumbnail createThumbnail(final YouTubeThumbnailFormat format, final Image image) throws IOException {
        return new ImplYouTubeThumbnail(format, image);
    }

    /**
     * Parses a YouTube thumbnail
     *
     * @param videoId the video id
     * @return the container for all thumbnail formats
     * @throws java.io.IOException
     */
    public YouTubeThumbnailContainer parseThumbnail(final String videoId) throws IOException {
        final ImplYouTubeThumbnailContainer container = new ImplYouTubeThumbnailContainer(videoId);
        container.parse(apiKey);

        return container;
    }

    /**
     * Parses a YouTube thumbnail
     * @param json the json string
     * @return the container for all thumbnail formats
     * @throws java.io.IOException
     */
    public YouTubeThumbnailContainer parseThumbnailFromJson(final String json) throws IOException {
        final ImplYouTubeThumbnailContainer container = new ImplYouTubeThumbnailContainer();
        container.parse(apiKey, json);

        return container;
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
