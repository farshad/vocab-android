package xyz.farshad.vocab.activity

import android.os.Bundle
import android.widget.ListView

import androidx.appcompat.app.AppCompatActivity
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.WordListAdapter
import xyz.farshad.vocab.data.dao.WordDao
import xyz.farshad.vocab.data.model.Word
import javax.inject.Inject

class WordListActivity : AppCompatActivity() {

    @Inject
    lateinit var wordDao: WordDao

    lateinit var words: List<Word>
    private var levelId: Long? = null
    private var levelName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        title = "Words"

        val b = intent.extras
        if (b != null && b.containsKey("levelId")) {
            levelName = b.getString("levelName")
            levelId = b.getLong("levelId")
            words = wordDao.findByLevelId(levelId!!.toInt())

            showWordList(false)
        }
        setToolBar()
    }

    private fun setToolBar() {
        val toolbar = supportActionBar
        toolbar!!.title = "Words of " + levelName!!
    }

    private fun showWordList(reload: Boolean) {
        if (reload) {
            words = wordDao.findByLevelId(levelId!!.toInt())
        }
        val adapter = WordListAdapter(this@WordListActivity, R.layout.word_list_view, words)
        val list = findViewById<ListView>(R.id.wordMainListView)
        list.adapter = adapter
        list.itemsCanFocus = true
    }
}
