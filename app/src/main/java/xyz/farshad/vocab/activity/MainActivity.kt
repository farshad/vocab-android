package xyz.farshad.vocab.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView

import com.orm.SugarContext

import androidx.appcompat.app.AppCompatActivity
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.CourseListAdapter
import xyz.farshad.vocab.component.Server.DataTransfer
import xyz.farshad.vocab.data.model.Course


class MainActivity : AppCompatActivity() {

    internal var courses: List<Course>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SugarContext.init(this@MainActivity)
        title = "Courses"
        courses = Course.listAll(Course::class.java!!)

        // create default courses if not exist
        if (courses.size == 0) {
            createDefaultCourses()
            courses = Course.listAll(Course::class.java!!)
        }
        showCategoryList(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            DataTransfer(this).execute()
            showCategoryList(true)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun createDefaultCourses() {
        //
        //        List<Course> curs = new ArrayList();
        //        curs.add(new Course("Oxford Word Skills", 504));
        //        curs.add(new Course("Wikipedia", 10));
        //        SugarRecord.saveInTx(curs);
        //
        //        List<Level> levels = new ArrayList<Level>();
        //        levels.add(new Level("Unit 1", 1));
        //        levels.add(new Level("Unit 2", 1));
        //        levels.add(new Level("Unit 3", 1));
        //        SugarRecord.saveInTx(levels);
        //
        //        List<Word> words = new ArrayList<Word>();
        //        words.add(new Word(
        //                "Abandon",
        //                "desert",
        //                "abandon her idea",
        //                "ترک کردن",
        //                1,
        //                0,
        //                false
        //        ));
        //        words.add(new Word(
        //                "keen",
        //                "sharp",
        //                "keen sense of smell",
        //                "مشتاق",
        //                1,
        //                0,
        //                false
        //        ));
        //        SugarRecord.saveInTx(words);
    }

    private fun showCategoryList(reload: Boolean = false) {
        if (reload == true) {
            courses = Course.listAll(Course::class.java!!)
        }

        val adapter = CourseListAdapter(this@MainActivity, R.layout.cuorse_list_view, courses)
        val list = findViewById<View>(R.id.catMainListView) as ListView
        list.adapter = adapter
        list.itemsCanFocus = true
    }
}
