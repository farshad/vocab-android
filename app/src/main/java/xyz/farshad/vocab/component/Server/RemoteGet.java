package xyz.farshad.vocab.component.Server;

import com.google.common.base.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * get request to api and convert response to string
 */
public class RemoteGet extends RemoteRequest {

    /**
     *
     * @param link
     * @param data (in get request will be null)
     * @return JSONObject
     * @throws IOException
     * @throws URISyntaxException
     */
    @Override
    JSONObject send(String link, Optional<Map<String, Object>> data) throws IOException, URISyntaxException {
        URL url = new URL(link);
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        request.setURI(new URI(link));
        HttpResponse response = client.execute(request);
        JSONObject res = null;
        try {
            res = getString(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;

    }
}
