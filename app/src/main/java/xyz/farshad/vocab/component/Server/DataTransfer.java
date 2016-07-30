package xyz.farshad.vocab.component.Server;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import xyz.farshad.vocab.component.DataSource.Import;
import xyz.farshad.vocab.model.Course;
import xyz.farshad.vocab.model.Level;
import xyz.farshad.vocab.model.Word;

public class DataTransfer extends AsyncTask {
    private RemoteServer server;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String link = "http://192.168.0.101:8080/api/retrieve";
        server = new RemoteServer();
        try{
            JSONObject responseData = server.get(link);
            return responseData;
        }catch(Exception e){
            return new String("can not connect to server: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        //delete all records
        Course.deleteAll(Course.class);
        Level.deleteAll(Level.class);
        Word.deleteAll(Word.class);

        JSONObject responseData = (JSONObject) o;

        //import all records
        Import imp = new Import();
        try {
            imp.importCourse(responseData.getJSONArray("course"));
            imp.importLevel(responseData.getJSONArray("level"));
            imp.importWord(responseData.getJSONArray("word"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
