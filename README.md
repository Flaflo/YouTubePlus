# YouTubePlus
A YouTube Library

# Playlists
To parse a ``YouTubePlaylist`` we use the ``YouTubePlaylistParser``:
``` 
final YouTubePlaylistParser playlistParser = new YouTubePlaylistParser("GOOGLE_API_KEY");
final YouTubePlaylist playlist = playlistParser.parsePlaylist("PLAYLIST_ID");
```
You can get various informations from a Playlist.
As example you can get all ``YouTubeVideo``s contained in a ``YouTubePlaylist``:
```
playlist.getVideos();
```
# Videos
To parse a ``YouTubeVideo`` we use the ``YouTubeVideoParser``:
```
final YouTubeVideoParser videoParser = new YouTubeVideoParser("GOOGLE_API_KEY");
final YouTubeVideo video = videoParser.parseVideo("VIDEO_ID");
```
A ``YouTubeVideo`` has a container to store informations about it, the ``YouTubeVideoInfo``.
To get it use its getter:
```
video.getInfo();
```

# Video Info
To get a ``YouTubeVideoInfo`` you can either get it through a ``YouTubeVideo`` as shown above or through the ``YouTubeVideoInfoProvider``:
```
final YouTubeVideoInfoProvider infoProvider = new YouTubeVideoInfoProvider("GOOGLE_API_KEY");
final YouTubeVideoInfo info = infoProvider.provideYouTubeVideoInfo("videoId");
```
The ``YouTubeVideoInfo`` contains informations about a ``YouTubeVideo``.
The following is stored by the ``YouTubeVideoInfo`` at the moment:
* The video title
```
info.getTitle();
```
* The video id
```
info.getVideoId();
```