package xyz.farshad.vocab.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.LevelListAdapter
import xyz.farshad.vocab.data.model.Level
import xyz.farshad.vocab.viewmodel.LevelViewModel

class LevelListActivity : AppCompatActivity() {
    private val levelViewModel: LevelViewModel by viewModel()
    private var courseId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_list)
        title = "Levels"

        val b = intent.extras
        if (b != null && b.containsKey("courseId")) {
            courseId = b.get("courseId").toString().toLong()
            levelViewModel.findByCourseId(courseId!!.toInt())
        }

        setObserver()
    }

    private fun setObserver() {
        levelViewModel.watchLevel()?.observe(this, {
            showLevelList(it)
        })
    }

    private fun showLevelList(levels: List<Level>) {
        val adapter = LevelListAdapter(this, R.layout.level_list_view, levels)
        val list = findViewById<ListView>(R.id.levelListView)
        list.adapter = adapter
        list.itemsCanFocus = true
    }
}

