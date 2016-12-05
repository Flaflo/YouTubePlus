package xyz.flaflo.ytp.playlist;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
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
final class ImplYouTubePlaylist extends ArrayList<YouTubeVideo> implements YouTubePlaylist {

	private static final int MAX_RESULTS = 50;
    private static final String YOUTUBE_API_PLAYLIST = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=%s&playlistId=%s&key=%s";

    private String playlistId;

    /**
     * @param playlistId the YouTube playlist id
     */
    ImplYouTubePlaylist(String playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Parses the Playlist content
     *
     * @param key the google api key
     */
    void parse(String key) throws ParseException, IOException, InterruptedException, ExecutionException {
        final YouTubeVideoParser videoParser = new YouTubeVideoParser(key);
        final String infoUrl = String.format(YOUTUBE_API_PLAYLIST, MAX_RESULTS, playlistId, key);
        final JSONObject playlistInfos = JSONObject.fromObject(WebUtil.getWebContent(infoUrl));

        String nextPageToken = playlistInfos.containsKey("nextPageToken") ? playlistInfos.getString("nextPageToken") : "";

        final JSONObject pageInfo = playlistInfos.getJSONObject("pageInfo");
        final int totalVideos = pageInfo.getInt("totalResults");
        final int pages = (int) Math.ceil(totalVideos / 50D);

        final ExecutorService executor = Executors.newFixedThreadPool(totalVideos);
        final FutureTask[] tasks = new FutureTask[totalVideos];

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

                    final FutureTask<YouTubeVideo> videoParseTask = new FutureTask<>(() -> videoParser.parseYouTubeVideoFromJson(videoId, item.toString()));
                    tasks[parseIndex++] = videoParseTask;
                    executor.execute(videoParseTask);
                }
            }
        }

        for (int i = 0; i < parseIndex; i++) {
            final FutureTask<YouTubeVideo> videoParseTask = tasks[i];
            this.add(videoParseTask.get());
        }

        executor.shutdown();
    }

    @Override
    public String getPlaylistId() {
        return playlistId;
    }
}
