package xyz.farshad.vocab.component.Server;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataTransfer extends AsyncTask {
    private RemoteServer server;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        server = new RemoteServer();
        try{
            String link = "http://192.168.0.101:8080/api/retrieve";


            JSONObject responseData = server.get(link);

            JSONArray courses = responseData.getJSONArray("course");
            JSONArray levels = responseData.getJSONArray("level");
            JSONArray words = responseData.getJSONArray("word");

            for (int i=0; i<courses.length(); i++) {
                JSONObject course = courses.getJSONObject(i);
                Log.i("WEB", course.getString("name"));
            }

            for (int i=0; i<levels.length(); i++) {
                JSONObject level = levels.getJSONObject(i);
                Log.i("WEB", level.getString("name"));
            }

            for (int i=0; i<words.length(); i++) {
                JSONObject word = words.getJSONObject(i);
                Log.i("WEB", word.getString("name"));
            }

            return null;
        }

        catch(Exception e){
            //Log.i("WEB", e.getMessage());

            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
