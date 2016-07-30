package xyz.farshad.vocab.component.Server;

import com.google.common.base.Optional;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Map;

public abstract class RemoteRequest {

    abstract JSONObject send(String link, Optional<Map<String, Object>> data) throws IOException, URISyntaxException;

    public JSONObject getString(HttpResponse response) throws JSONException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer sb = new StringBuffer("");
        String line="";

        while ((line = in.readLine()) != null) {
            sb.append(line);
            break;
        }
        in.close();

        return new JSONObject(sb.toString());
    }
}
