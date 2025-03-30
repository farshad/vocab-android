package xyz.farshad.vocab.ui.word

import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.farshad.vocab.R
import xyz.farshad.vocab.data.entity.Word
import xyz.farshad.vocab.databinding.FragmentWordPagerBinding
import xyz.farshad.vocab.ui.base.BaseFragment
import xyz.farshad.vocab.viewmodel.WordViewModel
import xyz.farshad.vocab.viewmodel.util.Constants.Companion.DEFAULT_PAGE_SWITCHER_TIMER
import xyz.farshad.vocab.viewmodel.util.Constants.Companion.DEFAULT_SPEECH_RATE
import xyz.farshad.vocab.viewmodel.util.Constants.Companion.ONE_MILLISECOND
import java.util.*

class WordPagerFragment : BaseFragment<FragmentWordPagerBinding>(), TextToSpeech.OnInitListener,
    View.OnClickListener, PagerSettingsListener {

    private val wordViewModel: WordViewModel by viewModel()
    private val args: WordPagerFragmentArgs by navArgs()
    private var words: List<Word> = arrayListOf()
    private var currentItem: Int = 0
    private var currentSpeechRate: Float = DEFAULT_SPEECH_RATE
    private lateinit var textToSpeech: TextToSpeech
    private var sound = true
    private var speed = DEFAULT_PAGE_SWITCHER_TIMER
    private var viewPager: ViewPager? = null
    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null
    private lateinit var pagerControllerBottomSheet: PagerControllerBottomSheet

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentWordPagerBinding = FragmentWordPagerBinding.inflate(inflater, container, false)

    override fun bindView() {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setupToolbar(
            getString(R.string.words),
            binding.innerToolbarTitle,
            binding.backIcon
        )

        pagerControllerBottomSheet = PagerControllerBottomSheet()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.language = Locale.forLanguageTag(args.lang)
        }
    }

    override fun businessLogic() {
        textToSpeech = TextToSpeech(requireContext(), this, "com.google.android.tts")

        setWords()

        binding.hideTranslateButton.setOnClickListener(this)
        binding.showTranslateButton.setOnClickListener(this)
        binding.volumeUp.setOnClickListener(this)
        binding.pause.setOnClickListener(this)
        binding.play.setOnClickListener(this)
        binding.pagerController.setOnClickListener(this)
    }

    private fun setWords() {
        words = args.words.toList()
        setPageAdopter(args.wordIndex)
    }

    private fun setPageAdopter(wordId: Int) {
        currentItem = wordId
        textToSpeech.speak(words[currentItem].title, TextToSpeech.QUEUE_ADD, null, null)
        viewPager = binding.wordViewPage
        val wordSwipeAdapter = WordSwipeAdapter(requireContext(), words)

        wordSwipeAdapter.setOnFavIconClickListener {
            wordViewModel.update(it)
        }

        viewPager!!.adapter = wordSwipeAdapter
        viewPager!!.currentItem = currentItem
        setViewPagerChangeListener()
//        startPageSwitcher(speed)
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
        handler.postDelayed(runnable!!, seconds * ONE_MILLISECOND)
    }

    private fun stopPageSwitcher() {
        if (this.runnable != null) {
            handler.removeCallbacks(runnable!!)
            handler.removeCallbacksAndMessages(null)
        }
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

    private fun showPagerController(speed: Long) {
        if (!pagerControllerBottomSheet.isAdded) {
            pagerControllerBottomSheet.setDefaultSpeechRate(currentSpeechRate)
            pagerControllerBottomSheet.listener = this
            pagerControllerBottomSheet.show(
                requireActivity().supportFragmentManager,
                PagerControllerBottomSheet.TAG
            )
        }
    }

    private fun setSpeechRate(speechRate: Float) {
        currentSpeechRate = speechRate
        textToSpeech.setSpeechRate(speechRate)
    }

    override fun onPause() {
        stopPageSwitcher()
        super.onPause()
    }

    override fun onDestroy() {
        stopPageSwitcher()
        super.onDestroy()
    }

    override fun onPagerSettingsChanged(
        velocity: Long,
        speechRate: Float,
        isSoundEnabled: Boolean
    ) {
        setSpeechRate(speechRate)
        sound = isSoundEnabled
    }
}

interface PagerSettingsListener {
    fun onPagerSettingsChanged(velocity: Long, speechRate: Float, isSoundEnabled: Boolean)
}
