package xyz.farshad.vocab.component.Server

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast

import org.json.JSONException
import org.json.JSONObject

import xyz.farshad.vocab.component.DataService.Import
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.data.model.Level
import xyz.farshad.vocab.data.model.Word

class DataTransfer(private val context: Context) : AsyncTask<*, *, *>() {
    private var server: RemoteServer? = null
    private val dialog: ProgressDialog

    init {
        this.dialog = ProgressDialog(this.context)
    }

    override fun onPreExecute() {
        super.onPreExecute()
        this.dialog.setMessage("Please wait...")
        this.dialog.show()
    }

    protected override fun doInBackground(objects: Array<Any>): Any? {
        val link = "http://192.168.43.207:8080/api/retrieve"
        server = RemoteServer()
        return server!!.get(link)
    }

    protected override fun onPostExecute(o: Any) {
        super.onPostExecute(o)

        if (dialog.isShowing) {
            dialog.dismiss()
        }

        if (o is Exception) {
            Log.i("WEB", o.message)
            Toast.makeText(context, "Can not connect to server, please try again later.", Toast.LENGTH_LONG).show()
        } else {
            //delete all records
            Course.deleteAll<Course>(Course::class.java!!)
            Level.deleteAll<Level>(Level::class.java!!)
            Word.deleteAll<Word>(Word::class.java!!)

            val responseData = o as JSONObject

            //import all records
            val imp = Import()
            try {
                imp.importCourse(responseData.getJSONArray("course"))
                imp.importLevel(responseData.getJSONArray("level"))
                imp.importWord(responseData.getJSONArray("word"))
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

    }
}
