package xyz.farshad.vocab.activity

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.adapter.WordSwipeAdapter
import xyz.farshad.vocab.data.entity.Word
import xyz.farshad.vocab.databinding.ActivityWordPagerBinding
import xyz.farshad.vocab.viewmodel.WordViewModel
import java.util.*

class WordPagerActivity : AppCompatActivity(), TextToSpeech.OnInitListener, View.OnClickListener {
    private val wordViewModel: WordViewModel by viewModel()
    private lateinit var binding: ActivityWordPagerBinding
    private lateinit var textToSpeech: TextToSpeech
    private var words: List<Word> = arrayListOf()
    private var wordId: Int? = null
    private var currentItem: Int = 0
    private var sound = true
    private var speed = 3
    private lateinit var optionsMenu: Menu
    private var timer: Timer? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_word_pager)
        title = "Words"
        textToSpeech = TextToSpeech(this, this)
        textToSpeech.language = Locale.US

        val b = intent.extras

        if (b != null) {
            wordId = b.getInt("wordId")
            val isReview: Boolean = b.getBoolean("isReview")

            if (isReview){
                wordViewModel.fetchReviewWords()
            }else{
                val levelId = b.getInt("levelId")
                wordViewModel.findByLevelId(levelId)
            }
            setObserver()
        }

        binding.hideTranslateButton.setOnClickListener(this)
        binding.showTranslateButton.setOnClickListener(this)
        binding.volumeUp.setOnClickListener(this)
        binding.pause.setOnClickListener(this)
        binding.play.setOnClickListener(this)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun setObserver() {
        wordViewModel.watchWord()?.observe(this, {
            words = it
            wordId?.let { it1 -> setPageAdopter(it1) }
        })
    }

    fun addToFavorite(word: Word){
        wordViewModel.update(word)
    }

    private fun setPageAdopter(wordId: Int) {
        currentItem = wordId
        viewPager = findViewById(R.id.word_view_page)
        val wordSwipeAdapter = WordSwipeAdapter(this, words)
        viewPager!!.adapter = wordSwipeAdapter
        viewPager!!.currentItem = currentItem
        setViewPagerChangeListener()
        textToSpeech.speak(words[currentItem].title, TextToSpeech.QUEUE_FLUSH, null, null)
        pageSwitcher(speed)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_word_pager, menu)
        optionsMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val soundOff = optionsMenu.getItem(0)
        val soundOn = optionsMenu.getItem(1)
        when (id) {
            R.id.sound_off -> {
                soundOn.isVisible = true
                soundOff.isVisible = false
                sound = false
            }
            R.id.sound_on -> {
                soundOn.isVisible = false
                soundOff.isVisible = true
                sound = true
            }
            R.id.increase_speed -> if (speed != 0) {
                speed--
                timer!!.cancel()
                pageSwitcher(speed)
            }
            R.id.decrease_speed -> {
                speed++
                timer!!.cancel()
                pageSwitcher(speed)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onInit(i: Int) {}

    private fun pageSwitcher(seconds: Int) {
        timer = Timer()
        timer!!.scheduleAtFixedRate(RemindTask(), 0, (seconds * 1000).toLong())
    }

    override fun onClick(view: View) {
        val currentView = viewPager!!.findViewWithTag<View>("word_pager" + viewPager!!.currentItem)
        when (view.id) {
            R.id.showTranslateButton -> {
                currentView.findViewById<View>(R.id.wordPagerTranslate).visibility = View.VISIBLE
                binding.showTranslateButton.setVisibility(View.GONE)
                binding.hideTranslateButton.setVisibility(View.VISIBLE)
            }
            R.id.hideTranslateButton -> {
                currentView.findViewById<View>(R.id.wordPagerTranslate).visibility = View.INVISIBLE
                binding.showTranslateButton.setVisibility(View.VISIBLE)
                binding.hideTranslateButton.setVisibility(View.GONE)
            }
            R.id.volumeUp -> textToSpeech.speak(words[viewPager!!.currentItem].title, TextToSpeech.QUEUE_FLUSH, null, null)
            R.id.pause -> {
                binding.play.visibility = View.VISIBLE
                binding.pause.visibility = View.GONE
                timer!!.cancel()
            }
            R.id.play -> {
                binding.play.visibility = View.GONE
                binding.pause.visibility = View.VISIBLE
                pageSwitcher(speed)
            }
        }
    }

    private fun setViewPagerChangeListener() {
        viewPager?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                currentItem = position
                if (sound) {
                    textToSpeech.speak(words[position].title, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    internal inner class RemindTask : TimerTask() {

        override fun run() {
            runOnUiThread {
                if (currentItem == words.size) {
                    timer!!.cancel()
                } else {
                    viewPager!!.currentItem = currentItem++
                }
            }
        }
    }

    override fun onDestroy() {
        timer?.cancel()
        timer?.purge()
        super.onDestroy()
    }
}
