package xyz.flaflo.ytp.video.info;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xyz.flaflo.ytp.util.ISO8601DateParser;
import xyz.flaflo.ytp.util.WebUtil;

/**
 * Stores informations about a YouTube video
 *
 * @author Flaflo
 */
final class ImplYouTubeVideoInfo implements YouTubeVideoInfo {

    private static final String YOUTUBE_API_VIDEO_INFO = "https://www.googleapis.com/youtube/v3/videos?id=%s&key=%s&part=snippet";

    private String videoId;

    private String title;

    private Date publishedAt;

    private String channelId;

    private String description;

    private String[] tags;

    /**
     * @param videoId the YouTube video id
     */
    ImplYouTubeVideoInfo(String videoId) {
        this.videoId = videoId;
    }

    ImplYouTubeVideoInfo() {
    }

    /**
     * Parses the video informations
     *
     * @param key the google api key
     */
    void parse(String key) throws ParseException, IOException {
        this.parse(key, WebUtil.getWebContent(String.format(YOUTUBE_API_VIDEO_INFO, this.videoId, key)));
    }

    /**
     * Parses the video informations
     *
     * @param key the google api key
     * @param json the json string
     */
    void parse(String key, String json) throws ParseException, IOException {
        final JSONObject infos = JSONObject.fromObject(json);
        final JSONArray items = infos.getJSONArray("items");

        for (Object obj : items) {
            if (obj instanceof JSONObject) {
                final JSONObject item = (JSONObject) obj;

                if (item.containsKey("snippet")) {
                    final JSONObject snippet = item.getJSONObject("snippet");

                    if (snippet.containsKey("publishedAt")) {
                        final Date publishedAt = ISO8601DateParser.parse(snippet.getString("publishedAt"));
                        this.publishedAt = publishedAt;
                    }

                    if (snippet.containsKey("channelId")) {
                        final String channelId = snippet.getString("channelId");
                        this.channelId = channelId;
                    }

                    if (snippet.containsKey("title")) {
                        final String title = snippet.getString("title");
                        this.title = title;
                    }

                    if (snippet.containsKey("description")) {
                        final String description = snippet.getString("description");
                        this.description = description;
                    }

                    if (snippet.containsKey("tags")) {
                        final JSONArray tags = snippet.getJSONArray("tags");
                        this.tags = new String[tags.size()];

                        for (int i = 0; i < tags.size(); i++) {
                            final Object obj1 = tags.get(i);

                            if (obj1 instanceof String) {
                                final String tag = (String) obj1;
                                this.tags[i] = tag;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getVideoId() {
        return videoId;
    }

    @Override
    public Date getPublishedAt() {
        return publishedAt;
    }

    @Override
    public String getChannelId() {
        return channelId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String[] getTags() {
        return tags;
    }
}
