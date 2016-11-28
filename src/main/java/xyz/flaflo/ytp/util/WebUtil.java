package xyz.flaflo.ytp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Utilities for HTTPRequests
 *
 * @author Cydhra
 */
public final class WebUtil {

    /**
     * private constructor, Utility class shall not be instanced.
     */
    private WebUtil() {

    }

    /**
     * Get content via request
     *
     * @param targetURL the URL
     *
     * @return a string containing the content
     */
    public static String getWebContent(final String targetURL) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            final URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("user-agent", "YouTubePlus-User-Agent");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setRequestProperty("Content-Length", "0");

            connection.setUseCaches(false);
            connection.setDoOutput(false);

            //Get Response
            final InputStream is = connection.getInputStream();
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("windows-1252")));
            final StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
