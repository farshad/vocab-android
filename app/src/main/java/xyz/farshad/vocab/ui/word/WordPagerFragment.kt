package xyz.farshad.vocab.ui.word

import android.speech.tts.TextToSpeech
import android.view.*
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.data.entity.Word
import xyz.farshad.vocab.databinding.FragmentWordPagerBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.WordViewModel
import java.util.*

class WordPagerFragment : BaseFragment<FragmentWordPagerBinding>(), TextToSpeech.OnInitListener,
    View.OnClickListener {

    private val wordViewModel: WordViewModel by viewModel()
    private val args: WordPagerFragmentArgs by navArgs()
    private var words: List<Word> = arrayListOf()
    private var wordId: Int? = null
    private var currentItem: Int = 0
    private lateinit var textToSpeech: TextToSpeech
    private var sound = true
    private var speed = 3
    private lateinit var optionsMenu: Menu
    private var timer: Timer? = null
    private var viewPager: ViewPager? = null

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentWordPagerBinding = FragmentWordPagerBinding.inflate(inflater, container, false)

    override fun bindView() {
    }

    override fun businessLogic() {
        setObserver()
        textToSpeech = TextToSpeech(requireContext(), this)
        textToSpeech.language = Locale.US

        wordId = args.wordId

        if (args.isReview) {
            wordViewModel.fetchReviewWords()
        } else {
            wordViewModel.findByLevelId(args.chapterId)
        }

    }

    private fun setObserver() {
        wordViewModel.watchWord()?.observe(this) {
            words = it
            wordId?.let { it1 -> setPageAdopter(it1) }
        }
    }

    private fun setPageAdopter(wordId: Int) {
        currentItem = wordId
        viewPager = binding.wordViewPage
        val wordSwipeAdapter = WordSwipeAdapter(requireContext(), words)
        viewPager!!.adapter = wordSwipeAdapter
        viewPager!!.currentItem = currentItem
        setViewPagerChangeListener()
        textToSpeech.speak(words[currentItem].title, TextToSpeech.QUEUE_FLUSH, null, null)
        pageSwitcher(speed)
    }

    private fun pageSwitcher(seconds: Int) {
        timer = Timer()
        timer!!.scheduleAtFixedRate(this.RemindTask(), 0, (seconds * 1000).toLong())
    }

    private fun setViewPagerChangeListener() {
        viewPager?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
            }

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
            activity?.runOnUiThread {
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

    override fun onClick(view: View) {
        val currentView = viewPager!!.findViewWithTag<View>("word_pager" + viewPager!!.currentItem)
        when (view.id) {
            R.id.showTranslateButton -> {
                currentView.findViewById<View>(R.id.wordPagerTranslate).visibility = View.VISIBLE
                binding.showTranslateButton.visibility = View.GONE
                binding.hideTranslateButton.visibility = View.VISIBLE
            }
            R.id.hideTranslateButton -> {
                currentView.findViewById<View>(R.id.wordPagerTranslate).visibility = View.INVISIBLE
                binding.showTranslateButton.visibility = View.VISIBLE
                binding.hideTranslateButton.visibility = View.GONE
            }
            R.id.volumeUp -> textToSpeech.speak(
                words[viewPager!!.currentItem].title,
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
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

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_word_pager, menu)
//        optionsMenu = menu
//        return true
//    }

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

    override fun onInit(status: Int) {}
}