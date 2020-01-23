package xyz.farshad.vocab.component.DataService

import com.orm.SugarRecord

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

import xyz.farshad.vocab.model.Course
import xyz.farshad.vocab.model.Level
import xyz.farshad.vocab.model.Word

class Import {
    @Throws(JSONException::class)
    fun importCourse(coursesJson: JSONArray) {
        for (i in 0 until coursesJson.length()) {
            val course = coursesJson.getJSONObject(i)
            val sql = "INSERT INTO course VALUES ( ?, ?, ?)"
            Course.executeQuery(sql, *arrayOf(course.getInt("id").toString(), course.getString("name"), "1"))
        }
    }

    @Throws(JSONException::class)
    fun importLevel(levelsJson: JSONArray) {
        for (i in 0 until levelsJson.length()) {
            val level = levelsJson.getJSONObject(i)
            val sql = "INSERT INTO level VALUES ( ?, ?, ?)"
            Level.executeQuery(sql,
                    *arrayOf(level.getInt("id").toString(), level.getInt("courseId").toString(), level.getString("name")))
        }
    }

    @Throws(JSONException::class)
    fun importWord(wordsJson: JSONArray) {
        val words = ArrayList<Word>()
        for (i in 0 until wordsJson.length()) {
            val word = wordsJson.getJSONObject(i)
            words.add(Word(
                    word.getString("name"),
                    word.getString("meaning"),
                    word.getString("example"),
                    word.getString("translate"),
                    word.getInt("levelId"),
                    word.getInt("viewCount"),
                    word.getBoolean("favorite")
            ))
        }
        SugarRecord.saveInTx(words)
    }
}
