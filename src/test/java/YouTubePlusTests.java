
import java.awt.Image;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import xyz.flaflo.ytp.video.YouTubeVideo;
import xyz.flaflo.ytp.video.YouTubeVideoParser;
import xyz.flaflo.ytp.video.info.YouTubeVideoInfo;

/**
 * A class where features get tested
 *
 * @author Flaflo
 */
public final class YouTubePlusTests {

    public static void main(String[] args) throws ParseException, IOException {
        final String key = "AIzaSyC87bHcfBOLWOCG7GIURULXnyZTPhwmiqA";
        final String videoId = "LHX8Ml9A2sA";

        final YouTubeVideoParser videoParser = new YouTubeVideoParser(key);
        final YouTubeVideo video = videoParser.parseYouTubeVideo(videoId);
        final YouTubeVideoInfo videoInfo = video.getInfo();

    }

    private static void showPreview(final String title, final Image image) {
        if (image == null) {
            return;
        }

        final JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(image);
        frame.setVisible(true);
    }

}
