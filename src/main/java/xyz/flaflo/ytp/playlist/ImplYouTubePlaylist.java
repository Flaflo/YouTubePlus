package xyz.flaflo.ytp.playlist;

import java.util.Arrays;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xyz.flaflo.ytp.util.WebUtil;
import xyz.flaflo.ytp.video.YouTubeVideo;
import xyz.flaflo.ytp.video.YouTubeVideoParser;

/**
 * Represents a YouTube Playlist
 *
 * @author Flaflo
 */
final class ImplYouTubePlaylist implements YouTubePlaylist {

    private static final String YOUTUBE_API_PLAYLIST = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=%s&key=%s";

    private String playlistId;
    private YouTubeVideo[] videos;

    /**
     * @param playlistId the YouTube playlist id
     */
    public ImplYouTubePlaylist(String playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Parses the Playlist content
     *
     * @param key the google api key
     */
    public void parse(String key) {
        final YouTubeVideoParser videoParser = new YouTubeVideoParser(key);

        final String infoUrl = String.format(YOUTUBE_API_PLAYLIST, playlistId, key);
        final JSONObject playlistInfos = JSONObject.fromObject(WebUtil.getWebContent(infoUrl));
        
        String nextPageToken = playlistInfos.containsKey("nextPageToken") ? playlistInfos.getString("nextPageToken") : "";

        final JSONObject pageInfo = playlistInfos.getJSONObject("pageInfo");
        final int totalVideos = pageInfo.getInt("totalResults");
        final int pages = (int) Math.ceil(totalVideos / 50D);

        this.videos = new YouTubeVideo[totalVideos];

        int parseIndex = 0;

        for (int page = 0; page < pages; page++) {
            final JSONObject pageInfos = page > 0 ? JSONObject.fromObject(WebUtil.getWebContent(String.format("%s&pageToken=%s", infoUrl, nextPageToken))) : playlistInfos;
            final JSONArray items = pageInfos.getJSONArray("items");

            if (page > 0 && pageInfos.containsKey("nextPageToken")) {
                nextPageToken = pageInfos.getString("nextPageToken");
            }

            for (final Object obj : items) {
                if (obj instanceof JSONObject) {
                    final JSONObject item = (JSONObject) obj;
                    final JSONObject snippet = item.getJSONObject("snippet");
                    final JSONObject resourceId = snippet.getJSONObject("resourceId");
                    final String videoId = resourceId.getString("videoId");
                    
                    this.videos[parseIndex++] = videoParser.parseYouTubeVideo(videoId);
                }
            }
        }
    }

    @Override
    public String getPlaylistId() {
        return playlistId;
    }

    @Override
    public YouTubeVideo[] getVideos() {
        return videos;
    }

    @Override
    public String toString() {
        return "{YouTubePlaylist:{" + "videos:" + Arrays.toString(this.getVideos()) + ",id:" + this.getPlaylistId() + "}";
    }
}
