package xyz.flaflo.ytp.playlist;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

/**
 * Provides a parser for YouTube playlists
 *
 * @author Flaflo
 */
public final class YouTubePlaylistParser {

    private String apiKey;

    /**
     * @param apiKey the google api key
     */
    public YouTubePlaylistParser(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Pareses a YouTube playlist by its id
     *
     * @param playlistId the playlist id
     * @return the parsed Playlist
     * @throws java.text.ParseException
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    public YouTubePlaylist parsePlaylist(final String playlistId) throws ParseException, IOException, InterruptedException, ExecutionException {
        final ImplYouTubePlaylist playlist = new ImplYouTubePlaylist(playlistId);
        playlist.parse(apiKey);

        return playlist;
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
