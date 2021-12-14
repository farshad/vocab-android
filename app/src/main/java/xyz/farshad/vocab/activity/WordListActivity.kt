package xyz.farshad.vocab.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.WordListAdapter
import xyz.farshad.vocab.data.model.Word
import xyz.farshad.vocab.viewmodel.WordViewModel

class WordListActivity : AppCompatActivity() {
    private val wordViewModel: WordViewModel by viewModel()
    private var levelId: Int? = null
    private var levelName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        title = "Words"

        val b = intent.extras
        if (b != null && b.containsKey("levelId")) {
            levelName = b.getString("levelName")
            levelId = b.get("levelId").toString().toInt()
            wordViewModel.findByLevelId(levelId!!)
        }
        setToolBar()
        setObserver()
    }

    private fun setObserver() {
        wordViewModel.watchWord()?.observe(this, {
            showWordList(it)
        })
    }

    private fun setToolBar() {
        val toolbar = supportActionBar
        toolbar!!.title = "Words of " + levelName!!
    }

    private fun showWordList(words: List<Word>) {
        val adapter = WordListAdapter(this@WordListActivity, R.layout.word_list_view, words)
        val list = findViewById<ListView>(R.id.wordMainListView)
        list.adapter = adapter
        list.itemsCanFocus = true
    }
}
