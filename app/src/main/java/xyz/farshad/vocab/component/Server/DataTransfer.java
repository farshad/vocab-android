package xyz.farshad.vocab.component.Server;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import xyz.farshad.vocab.component.DataService.Import;
import xyz.farshad.vocab.model.Course;
import xyz.farshad.vocab.model.Level;
import xyz.farshad.vocab.model.Word;

public class DataTransfer extends AsyncTask {

    private Context context;
    private RemoteServer server;
    private ProgressDialog dialog;

    public DataTransfer(Context context) {
        this.context = context;
        this.dialog = new ProgressDialog(this.context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.dialog.setMessage("Please wait...");
        this.dialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String link = "http://192.168.43.207:8080/api/retrieve";
        server = new RemoteServer();
        Object responseData = server.get(link);
        return responseData;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        if (o instanceof Exception){
             Log.i("WEB", ((Exception) o).getMessage());
             Toast.makeText(context, "Can not connect to server, please try again later.", Toast.LENGTH_LONG).show();
        }else {
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
}
