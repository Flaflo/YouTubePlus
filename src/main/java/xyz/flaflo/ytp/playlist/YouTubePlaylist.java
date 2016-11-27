/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.flaflo.ytp.playlist;

import xyz.flaflo.ytp.video.YouTubeVideo;

/**
 * Represents a YouTube Playlist
 *
 * @author Flaflo
 */
public interface YouTubePlaylist {

    /**
     * @return the YouTube playlist id
     */
    String getPlaylistId();

    /**
     * @return the Videos contained in the playlist
     */
    YouTubeVideo[] getVideos();

}
