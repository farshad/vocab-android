package xyz.farshad.vocab.component.DataService;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.farshad.vocab.model.Course;
import xyz.farshad.vocab.model.Level;
import xyz.farshad.vocab.model.Word;

public class Import {
    public void importCourse(JSONArray coursesJson) throws JSONException {
        for (int i=0; i<coursesJson.length(); i++) {
            JSONObject course = coursesJson.getJSONObject(i);
            String sql="INSERT INTO course VALUES ( ?, ?, ?)";
            Course.executeQuery(sql, new String[]{String.valueOf(course.getInt("id")), course.getString("name"), "1"});
        }
    }
    public void importLevel(JSONArray levelsJson) throws JSONException {
        for (int i=0; i<levelsJson.length(); i++) {
            JSONObject level = levelsJson.getJSONObject(i);
            String sql="INSERT INTO level VALUES ( ?, ?, ?)";
            Level.executeQuery(sql,
                    new String[]{String.valueOf(level.getInt("id")),
                            String.valueOf(level.getInt("courseId")),
                            level.getString("name")
                    });
        }
    }
    public void importWord(JSONArray wordsJson) throws JSONException{
        List<Word> words = new ArrayList<Word>();
        for (int i=0; i<wordsJson.length(); i++) {
            JSONObject word = wordsJson.getJSONObject(i);
            words.add(new Word(
                    word.getString("name"),
                    word.getString("meaning"),
                    word.getString("example"),
                    word.getString("translate"),
                    word.getInt("levelId"),
                    word.getInt("viewCount"),
                    word.getBoolean("favorite")
            ));
        }
        SugarRecord.saveInTx(words);
    }
}
