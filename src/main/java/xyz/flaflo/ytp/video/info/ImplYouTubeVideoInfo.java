package xyz.flaflo.ytp.video.info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xyz.flaflo.ytp.util.WebUtil;

/**
 * Stores informations about a YouTube video
 *
 * @author Flaflo
 */
final class ImplYouTubeVideoInfo implements YouTubeVideoInfo {

    private static final String YOUTUBE_API_TITLE = "https://www.googleapis.com/youtube/v3/videos?id=%s&key=%s&fields=items(snippet(title))&part=snippet";

    private final String videoId;
    private String title;

    /**
     * @param videoId the YouTube video id
     */
    ImplYouTubeVideoInfo(String videoId) {
        this.videoId = videoId;
    }

    /**
     * Parses the video informations
     *
     * @param key the google api key
     */
    public void parse(String key) {
        this.parseTitle(key);
    }

    /**
     * Parses the video title
     *
     * @param key the google api key
     */
    private void parseTitle(String key) {
        final String infoUrl = String.format(YOUTUBE_API_TITLE, this.videoId, key);
        final JSONObject videoInfos = JSONObject.fromObject(WebUtil.getWebContent(infoUrl));
        final JSONArray items = videoInfos.getJSONArray("items");
        
        for (Object obj : items) {
            if (obj instanceof JSONObject) {
                final JSONObject item = (JSONObject) obj;
                final JSONObject snippet = item.getJSONObject("snippet");
                
                if (snippet.containsKey("title")) {
                    final String title = snippet.getString("title");
                    this.title = title;
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

}
