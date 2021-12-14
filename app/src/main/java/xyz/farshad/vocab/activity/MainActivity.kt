package xyz.farshad.vocab.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.CourseListAdapter
import xyz.farshad.vocab.data.model.Course
import xyz.farshad.vocab.viewmodel.CourseViewModel
import xyz.farshad.vocab.viewmodel.SyncViewModel

class MainActivity : AppCompatActivity() {
    private val courseViewModel: CourseViewModel by viewModel()
    private val syncViewModel: SyncViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Courses"

        courseViewModel.fetchAll()
        setObserver()
        val sync = findViewById<View>(R.id.sync) as Button
        sync.setOnClickListener {
            syncViewModel.get()
        }
    }

    private fun setObserver() {
        courseViewModel.watchCourse()?.observe(this, Observer {
            showCategoryList(it)
        })

        syncViewModel.watchSync().observe(this, {
            courseViewModel.fetchAll()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        if (id == R.id.action_settings) {
            courseViewModel.fetchAll()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showCategoryList(courses: List<Course>) {
        val adapter = CourseListAdapter(this@MainActivity, R.layout.cuorse_list_view, courses)
        val list = findViewById<View>(R.id.catMainListView) as ListView
        list.adapter = adapter
        list.itemsCanFocus = true
    }
}
