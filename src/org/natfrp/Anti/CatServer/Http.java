package org.natfrp.Anti.CatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Http {

    private static String rstr;

    public static String GET(String WebURL, String WebRequest) throws IOException {
        String getURL = WebURL + URLEncoder.encode(WebRequest, "utf-8");
        URL getUrl = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            return line;
        }
        reader.close();
        connection.disconnect();
        return null;
    }

    public static String Request(String URL, String Request) {
        try {
            return GET(URL, Request);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

}
