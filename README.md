# YouTubePlus
A YouTube Library

# Playlists
To parse a ``YouTubePlaylist`` you use the ``YouTubePlaylistParser``:
```Java
final YouTubePlaylistParser playlistParser = new YouTubePlaylistParser("GOOGLE_API_KEY");
final YouTubePlaylist playlist = playlistParser.parsePlaylist("PLAYLIST_ID");
```
You can get various informations from a Playlist.
As example you can get all ``YouTubeVideo``s contained in a ``YouTubePlaylist`` by iterating through it like a ``java.util.List``.
# Videos
To parse a ``YouTubeVideo`` you use the ``YouTubeVideoParser``:
```Java
final YouTubeVideoParser videoParser = new YouTubeVideoParser("GOOGLE_API_KEY");
final YouTubeVideo video = videoParser.parseVideo("VIDEO_ID");
```
A ``YouTubeVideo`` has a container to store informations about it, the ``YouTubeVideoInfo``.
To get it use its getter:
```Java
final YouTubeVideoInfo videoInfo = video.getInfo();
```

# Video Informations
To get a ``YouTubeVideoInfo`` you can either get it through a ``YouTubeVideo`` as shown above or through the ``YouTubeVideoInfoProvider``:
```Java
final YouTubeVideoInfoProvider infoProvider = new YouTubeVideoInfoProvider("GOOGLE_API_KEY");
final YouTubeVideoInfo info = infoProvider.provideYouTubeVideoInfo("videoId");
```
The ``YouTubeVideoInfo`` contains informations about a ``YouTubeVideo``.
The following is stored by the ``YouTubeVideoInfo`` at the moment:
* The video title
```Java
final String title = info.getTitle();
```
* The publish date
```Java
final Date publishedAt = info.getPublishedAt();
```
* The channel id
```Java
final String channelId = info.getChannelId();
```
* The description
```Java
final String description = info.getDescription();
```
* The tags
```Java
final String[] tags = info.getTags();
```
* The video id
```Java
final String videoId = info.getVideoId();
```

# Thumbnails
To get a ``YouTubeThumbnail`` you use the ``YouTubeThumbnailParser``, this will return a ``YouTubeThumbnailContainer`` which contains all thumbnails in their resolutions. You can get them with providing a ``YouTubeThumbnailFormat``:
```Java
final YouTubeThumbnailParser thumbnailParser = new YouTubeThumbnailParser("GOOGLE_API_KEY");
final YouTubeThumbnailContainer thumbnailContainer = thumbnailParser.parseThumbnail("videoId");
final YouTubeThumbnail thumbnail = thumbnailContainer.getThumbnail(YouTubeThumbnailFormat.MAXRES);
```
The ``YouTubeThumbnail`` contains the following informations:
* The format
```Java
final YouTubeThumbnailFormat = thumbnail.getFormat();
```
* The java image object
```Java
final Image image = thumbnail.getImage();
```
You can also create thumbnail objects by an url or image object you provide in the ``YouTubeThumbnailParser``.
```Java
final YouTubeThumbnail createdThumbnail = thumbnailParser.createThumbnail(URL_OR_IMAGE);
```
