/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.flaflo.ytp.video;

import xyz.flaflo.ytp.video.info.YouTubeVideoInfo;
import xyz.flaflo.ytp.video.info.YouTubeVideoInfoProvider;

/**
 *
 * @author Flaflo
 */
final class ImplYouTubeVideo implements YouTubeVideo {

    private final String videoId;
    private YouTubeVideoInfo info;
    
    /**
     * @param videoId the video id
     */
    ImplYouTubeVideo(String videoId) {
        this.videoId = videoId;
    }

    /**
     * Parses the video informations
     * @param key the google api key
     */
    public void parse(String key) {
        final YouTubeVideoInfoProvider infoProvider = new YouTubeVideoInfoProvider(key);
        this.info = infoProvider.provideYouTubeVideoInfo(this.videoId);
    }
    
    @Override
    public String getVideoId() {
        return videoId;
    }
    
    @Override
    public YouTubeVideoInfo getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "{YouTubeVideo:{videoId:" + this.getVideoId() + ",title:" + this.getInfo().getTitle() + "}}";
    }
}
