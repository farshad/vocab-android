package xyz.farshad.vocab.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.component.DataAdapter.CourseListAdapter;
import xyz.farshad.vocab.component.Server.DataTransfer;
import xyz.farshad.vocab.model.Course;
import xyz.farshad.vocab.model.Level;
import xyz.farshad.vocab.model.Word;


public class MainActivity extends Activity {

    List<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(MainActivity.this);
        courses = Course.listAll(Course.class);

        // create default courses if not exist
        if(courses.size() == 0){
            createDefaultCourses();
            courses = Course.listAll(Course.class);
        }
        showCategoryList(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            new DataTransfer().execute();
            Toast.makeText(MainActivity.this, "retrieve from server...", Toast.LENGTH_LONG).show();
            showCategoryList(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createDefaultCourses(){

        List<Course> curs = new ArrayList();
        curs.add(new Course("Oxford Word Skills", 504));
        curs.add(new Course("Wikipedia", 10));
        SugarRecord.saveInTx(curs);

        List<Level> levels = new ArrayList<Level>();
        levels.add(new Level("Unit 1", 1));
        levels.add(new Level("Unit 2", 1));
        levels.add(new Level("Unit 3", 1));
        SugarRecord.saveInTx(levels);

        List<Word> words = new ArrayList<Word>();
        words.add(new Word(
                "Abandon",
                "desert",
                "abandon her idea",
                "ترک کردن",
                1,
                0,
                false
        ));
        words.add(new Word(
                "keen",
                "sharp",
                "keen sense of smell",
                "مشتاق",
                1,
                0,
                false
        ));
        SugarRecord.saveInTx(words);
    }

    private void showCategoryList(){
        showCategoryList(false);
    }

    private void showCategoryList(boolean reload){
        if (reload == true){
            courses = Course.listAll(Course.class);
        }

        ArrayAdapter<Course> adapter = new CourseListAdapter(MainActivity.this, R.layout.cuorse_list_view, courses);
        ListView list = (ListView) findViewById(R.id.catMainListView);
        list.setAdapter(adapter);
        list.setItemsCanFocus(true);
    }
}
