package xyz.flaflo.ytp.playlist;

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
     * @param playlistId the playlist id
     * @return the parsed Playlist
     */
    public YouTubePlaylist parsePlaylist(String playlistId) {
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
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
