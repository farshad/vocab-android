package xyz.farshad.vocab.component.Server;

import android.util.Log;

import org.json.JSONObject;

public class RemoteServer {
    private RemoteGet remoteGet;

    public RemoteServer() {
        remoteGet = new RemoteGet();
    }

    public JSONObject get(String link){

        try {
            return remoteGet.send(link, null);
        } catch (Exception e) {
            Log.i("WEB", e.getMessage());
        }
        return null;
    }
}
