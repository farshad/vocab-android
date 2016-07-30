package xyz.farshad.vocab.component.Server;

import android.util.Log;
import org.json.JSONObject;

/**
 * this class is a facade for RemotePost and RemoteGet classes
 */
public class RemoteServer {
    private RemoteGet remoteGet;

    public RemoteServer() {
        remoteGet = new RemoteGet();
    }

    /**
     * call remoteGet class and send request
     *
     * @param link
     * @return JSONObject
     */
    public Object get(String link){

        try {
            return remoteGet.send(link, null);
        } catch (Exception e) {
            return e;
        }
    }
}
