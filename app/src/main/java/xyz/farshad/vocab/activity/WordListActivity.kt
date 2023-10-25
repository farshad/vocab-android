package xyz.farshad.vocab.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.viewmodel.WordViewModel

class WordListActivity : AppCompatActivity() {
    private val wordViewModel: WordViewModel by viewModel()
    private var levelId: Int? = null
    var isReview: Boolean = false
    private var levelName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        title = "Words"

        val b = intent.extras
        if (b != null) {
            levelName = b.getString("levelName")
            isReview = b.getBoolean("isReview")

            if (isReview) {
                wordViewModel.fetchReviewWords()
            } else {
                levelId = b.get("levelId").toString().toInt()
                wordViewModel.findByLevelId(levelId!!)
            }
        }
        setToolBar()
        setObserver()
    }

    private fun setObserver() {
    }

    private fun setToolBar() {
        val toolbar = supportActionBar
        toolbar!!.title = "Words of " + levelName!!
    }
}
