package xyz.farshad.vocab.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.lifecycle.lifecycleScope
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.CourseListAdapter
import xyz.farshad.vocab.data.dao.CourseDao
import xyz.farshad.vocab.data.model.Course
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var courseDao: CourseDao

    private lateinit var courses: List<Course>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Courses"
        lifecycleScope.launch {
            courses = courseDao.getAll()
            // create default courses if not exist
            if (courses.isEmpty()) {
                courses = courseDao.getAll()
            }
            showCategoryList(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        if (id == R.id.action_settings) {
            showCategoryList(true)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showCategoryList(reload: Boolean = false) {
        if (reload) {
            lifecycleScope.launch {
                courses = courseDao.getAll()
            }
        }

        val adapter = CourseListAdapter(this@MainActivity, R.layout.cuorse_list_view, courses)
        val list = findViewById<View>(R.id.catMainListView) as ListView
        list.adapter = adapter
        list.itemsCanFocus = true
    }
}
