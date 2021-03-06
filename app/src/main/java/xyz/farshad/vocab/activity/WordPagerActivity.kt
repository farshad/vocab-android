package xyz.farshad.vocab.activity

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import dagger.android.support.DaggerAppCompatActivity
import xyz.farshad.vocab.R
import xyz.farshad.vocab.component.DataAdapter.WordSwipeAdapter
import xyz.farshad.vocab.data.dao.WordDao
import xyz.farshad.vocab.data.model.Word
import xyz.farshad.vocab.databinding.ActivityWordPagerBinding
import java.util.*
import javax.inject.Inject

class WordPagerActivity : DaggerAppCompatActivity(), TextToSpeech.OnInitListener, View.OnClickListener {

    @Inject
    lateinit var wordDao: WordDao

    private lateinit var binding: ActivityWordPagerBinding
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var words: List<Word>
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
        if (b != null && b.containsKey("wordId") && b.containsKey("levelId")) {
            val wordId = b.getInt("wordId")
            val levelId = b.getInt("levelId")
            words = wordDao.findByLevelId(levelId!!.toInt())

            setPageAdopter(wordId)
        }

        pageSwitcher(speed)
        setViewPagerChangeListener()
        binding.hideTranslateButton.setOnClickListener(this)
        binding.showTranslateButton.setOnClickListener(this)
        binding.volumeUp.setOnClickListener(this)
        binding.pause.setOnClickListener(this)
        binding.play.setOnClickListener(this)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun setPageAdopter(wordId: Int) {
        viewPager = findViewById(R.id.word_view_page)
        val wordSwipeAdapter = WordSwipeAdapter(this, words)
        viewPager!!.adapter = wordSwipeAdapter
        currentItem = wordId
        viewPager!!.currentItem = currentItem
        textToSpeech.speak(words[currentItem].name, TextToSpeech.QUEUE_FLUSH, null)
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

    override fun onInit(i: Int) {

    }

    fun pageSwitcher(seconds: Int) {
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
            R.id.volumeUp -> textToSpeech.speak(words[viewPager!!.currentItem].name, TextToSpeech.QUEUE_FLUSH, null)
            R.id.pause -> {
                binding.play.setVisibility(View.VISIBLE)
                binding.pause.setVisibility(View.GONE)
                timer!!.cancel()
            }
            R.id.play -> {
                binding.play.setVisibility(View.GONE)
                binding.pause.setVisibility(View.VISIBLE)
                pageSwitcher(speed)
            }
        }
    }

    private fun setViewPagerChangeListener() {
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                currentItem = position
                if (sound) {
                    textToSpeech.speak(words[position].name, TextToSpeech.QUEUE_FLUSH, null)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
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
