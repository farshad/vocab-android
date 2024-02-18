package xyz.farshad.vocab.ui.word

import android.os.Handler
import android.os.Looper
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
import xyz.farshad.vocab.viewmodel.util.Constants.Companion.DEFAULT_PAGE_SWITCHER_TIMER
import xyz.farshad.vocab.viewmodel.util.Constants.Companion.ONE_MILLISECOND
import java.util.*

class WordPagerFragment : BaseFragment<FragmentWordPagerBinding>(), TextToSpeech.OnInitListener,
    View.OnClickListener {

    private val wordViewModel: WordViewModel by viewModel()
    private val args: WordPagerFragmentArgs by navArgs()
    private var words: List<Word> = arrayListOf()
    private var wordIndex: Int? = null
    private var currentItem: Int = 0
    private lateinit var textToSpeech: TextToSpeech
    private var sound = true
    private var speed = DEFAULT_PAGE_SWITCHER_TIMER
    private lateinit var optionsMenu: Menu
    private var viewPager: ViewPager? = null
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
    private lateinit var pagerControllerBottomSheet: PagerControllerBottomSheet

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentWordPagerBinding = FragmentWordPagerBinding.inflate(inflater, container, false)

    override fun bindView() {
        setupToolbar(
            getString(R.string.words),
            binding.innerToolbarTitle,
            binding.backIcon
        )
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.language = Locale.forLanguageTag(args.lang)
        }
    }

    override fun businessLogic() {
        setObserver()
        textToSpeech = TextToSpeech(requireContext(), this, "com.google.android.tts")

        wordIndex = args.wordIndex

        if (args.isReview) {
            wordViewModel.fetchReviewWords()
        } else {
            wordViewModel.findByChapterId(args.chapterId)
        }

        binding.hideTranslateButton.setOnClickListener(this)
        binding.showTranslateButton.setOnClickListener(this)
        binding.volumeUp.setOnClickListener(this)
        binding.pause.setOnClickListener(this)
        binding.play.setOnClickListener(this)
        binding.pagerController.setOnClickListener(this)

        pagerControllerBottomSheet = PagerControllerBottomSheet()
    }

    private fun setObserver() {
        wordViewModel.watchWord()?.observe(this) {
            words = it
            wordIndex?.let { wi -> setPageAdopter(wi) }
        }
    }

    private fun setPageAdopter(wordId: Int) {
        currentItem = wordId
        viewPager = binding.wordViewPage
        val wordSwipeAdapter = WordSwipeAdapter(requireContext(), words)

        wordSwipeAdapter.setOnFavIconClickListener {
            wordViewModel.update(it)
        }

        viewPager!!.adapter = wordSwipeAdapter
        viewPager!!.currentItem = currentItem
        setViewPagerChangeListener()
        textToSpeech.speak(words[currentItem].title, TextToSpeech.QUEUE_FLUSH, null, null)
        startPageSwitcher(speed)
    }

    private fun startPageSwitcher(seconds: Long) {
        runnable = object : Runnable {
            override fun run() {
                if (currentItem == words.size) {
                    stopPageSwitcher()
                } else {
                    viewPager?.currentItem = currentItem++
                }
                handler.postDelayed(this, seconds * ONE_MILLISECOND)
            }
        }
        handler.postDelayed(runnable, seconds * ONE_MILLISECOND)
    }

    private fun stopPageSwitcher(){
        handler.removeCallbacks(runnable)
        handler.removeCallbacksAndMessages(null)
    }

    private fun setViewPagerChangeListener() {
        viewPager?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                currentItem = position
                if (sound) {
                    textToSpeech.speak(words[position].title, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        })
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
                stopPageSwitcher()
            }
            R.id.play -> {
                binding.play.visibility = View.GONE
                binding.pause.visibility = View.VISIBLE
                startPageSwitcher(speed)
            }
            R.id.pager_controller -> {
                showPagerController(speed)
            }
        }
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
            R.id.increase_speed -> if (speed != 0L) {
                speed--
                stopPageSwitcher()
                startPageSwitcher(speed)
            }
            R.id.decrease_speed -> {
                speed++
                stopPageSwitcher()
                startPageSwitcher(speed)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showPagerController(speed: Long) {
        if (!pagerControllerBottomSheet.isAdded) {
            pagerControllerBottomSheet.setDefaultVelocity(speed)
            pagerControllerBottomSheet.show(
                requireActivity().supportFragmentManager,
                PagerControllerBottomSheet.TAG
            )
        }
    }

    override fun onResume() {
        startPageSwitcher(speed)
        super.onResume()
    }

    override fun onPause() {
        stopPageSwitcher()
        super.onPause()
    }

    override fun onDestroy() {
        stopPageSwitcher()
        super.onDestroy()
    }
}