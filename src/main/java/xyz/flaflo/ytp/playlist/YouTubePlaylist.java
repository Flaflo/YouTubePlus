package xyz.flaflo.ytp.playlist;

import java.util.List;
import xyz.flaflo.ytp.video.YouTubeVideo;

/**
 * Represents a YouTube Playlist
 *
 * @author Flaflo
 */
public interface YouTubePlaylist extends List<YouTubeVideo> {

    /**
     * @return the YouTube playlist id
     */
    String getPlaylistId();

}
