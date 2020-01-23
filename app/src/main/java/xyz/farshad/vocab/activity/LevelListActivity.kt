package xyz.farshad.vocab.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.orm.SugarContext
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.LevelListAdapter
import xyz.farshad.vocab.model.Level

class LevelListActivity : AppCompatActivity() {

    internal var levels: List<Level>
    private var courseId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_list)
        title = "Levels"
        SugarContext.init(this@LevelListActivity)

        val b = intent.extras
        if (b != null && b.containsKey("courseId")) {
            courseId = b.getLong("courseId")
            levels = Level.find(Level::class.java!!, "course_id = ?", courseId!!.toString())

            showLevelList(false)
        }
    }

    private fun showLevelList(reload: Boolean) {
        if (reload) {
            levels = Level.listAll(Level::class.java!!)
        }
        val adapter = LevelListAdapter(this, R.layout.level_list_view, levels)
        val list = findViewById<ListView>(R.id.levelListView)
        list.adapter = adapter
        list.itemsCanFocus = true
    }
}

