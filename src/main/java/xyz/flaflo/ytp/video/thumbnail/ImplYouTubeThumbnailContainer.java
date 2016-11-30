package xyz.flaflo.ytp.video.thumbnail;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xyz.flaflo.ytp.util.WebUtil;

/**
 * A container for YouTube thumbnails
 *
 * @author Flaflo
 */
final class ImplYouTubeThumbnailContainer implements YouTubeThumbnailContainer {

    private static final String YOUTUBE_API_THUMBNAIL = "https://www.googleapis.com/youtube/v3/videos?id=%s&key=%s&fields=items(snippet(thumbnails))&part=snippet";

    private String videoId;

    private YouTubeThumbnail res_default;
    private YouTubeThumbnail res_medium;
    private YouTubeThumbnail res_high;
    private YouTubeThumbnail res_standard;
    private YouTubeThumbnail res_max;

    /**
     * @param res_default the default resolution
     * @param res_medium the medium resolution
     * @param res_high the hight resolution
     * @param res_standard the standard resolution
     * @param res_max the max resolution
     */
    ImplYouTubeThumbnailContainer(YouTubeThumbnail res_default, YouTubeThumbnail res_medium, YouTubeThumbnail res_high, YouTubeThumbnail res_standard, YouTubeThumbnail res_max) {
        this.res_default = res_default;
        this.res_medium = res_medium;
        this.res_high = res_high;
        this.res_standard = res_standard;
        this.res_max = res_max;
    }

    ImplYouTubeThumbnailContainer(String videoId) {
        this.videoId = videoId;
    }

    ImplYouTubeThumbnailContainer() {
    }

    @Override
    public YouTubeThumbnail getThumbnail(YouTubeThumbnailFormat format) {
        switch (format) {
            case DEFAULT:
                return res_default;
            case MEDIUM:
                return res_medium;
            case HIGH:
                return res_high;
            case STANDARD:
                return res_standard;
            case MAXRES:
                return res_max;
            default:
                throw new AssertionError(format.name());
        }
    }

    /**
     * Parses all thumbnails
     */
    void parse(String key) throws IOException {
        parse(key, WebUtil.getWebContent(String.format(YOUTUBE_API_THUMBNAIL, this.videoId, key)));
    }

    /**
     * Parses all thumbnails
     *
     * @param key the google api key
     * @param json the json string
     */
    void parse(String key, String json) throws IOException {
        final JSONObject videoInfos = JSONObject.fromObject(json);
        final JSONArray items = videoInfos.getJSONArray("items");

        for (Object obj : items) {
            if (obj instanceof JSONObject) {
                final JSONObject item = (JSONObject) obj;

                if (item.containsKey("snippet")) {
                    final JSONObject snippet = item.getJSONObject("snippet");

                    if (snippet.containsKey("thumbnails")) {
                        final JSONObject thumbnails = snippet.getJSONObject("thumbnails");

                        if (thumbnails.containsKey("default")) {
                            final JSONObject infos = thumbnails.getJSONObject("default");
                            final String url = infos.getString("url");

                            this.res_default = new ImplYouTubeThumbnail(YouTubeThumbnailFormat.DEFAULT, url);
                        }
                        
                        if (thumbnails.containsKey("medium")) {
                            final JSONObject infos = thumbnails.getJSONObject("medium");
                            final String url = infos.getString("url");

                            this.res_medium = new ImplYouTubeThumbnail(YouTubeThumbnailFormat.MEDIUM, url);
                        }
                        
                        if (thumbnails.containsKey("high")) {
                            final JSONObject infos = thumbnails.getJSONObject("high");
                            final String url = infos.getString("url");

                            this.res_high = new ImplYouTubeThumbnail(YouTubeThumbnailFormat.HIGH, url);
                        }
                        
                        if (thumbnails.containsKey("standard")) {
                            final JSONObject infos = thumbnails.getJSONObject("standard");
                            final String url = infos.getString("url");

                            this.res_standard = new ImplYouTubeThumbnail(YouTubeThumbnailFormat.STANDARD, url);
                        }
                     
                        if (thumbnails.containsKey("maxres")) {
                            final JSONObject infos = thumbnails.getJSONObject("maxres");
                            final String url = infos.getString("url");

                            this.res_max = new ImplYouTubeThumbnail(YouTubeThumbnailFormat.MAXRES, url);
                        }
                    }
                }
            }
        }
    }
}
