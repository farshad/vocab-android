package xyz.farshad.vocab.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.WordListAdapter
import xyz.farshad.vocab.model.Word

class WordListActivity : AppCompatActivity() {

    internal var words: List<Word>
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
            words = Word.find(Word::class.java!!, "level_id = ?", levelId!!.toString())

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
            words = Word.find(Word::class.java!!, "level_id = ?", levelId!!.toString())
        }
        val adapter = WordListAdapter(this@WordListActivity, R.layout.word_list_view, words)
        val list = findViewById<ListView>(R.id.wordMainListView)
        list.adapter = adapter
        list.itemsCanFocus = true
    }
}
