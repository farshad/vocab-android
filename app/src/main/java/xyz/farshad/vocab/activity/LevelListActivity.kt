package xyz.farshad.vocab.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.LevelListAdapter
import xyz.farshad.vocab.data.dao.LevelDao
import xyz.farshad.vocab.data.model.Level
import javax.inject.Inject

class LevelListActivity : AppCompatActivity() {

    @Inject
    lateinit var levelDao: LevelDao

    lateinit var levels: List<Level>
    private var courseId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_list)
        title = "Levels"

        val b = intent.extras
        if (b != null && b.containsKey("courseId")) {
            courseId = b.getLong("courseId")
            levels = levelDao.findByCourseId(courseId!!.toInt())

            showLevelList(false)
        }
    }

    private fun showLevelList(reload: Boolean) {
        if (reload) {
            levels = levelDao.getAll()
        }
        val adapter = LevelListAdapter(this, R.layout.level_list_view, levels)
        val list = findViewById<ListView>(R.id.levelListView)
        list.adapter = adapter
        list.itemsCanFocus = true
    }
}

