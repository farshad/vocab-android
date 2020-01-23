package xyz.farshad.vocab.component.DataAdapter


import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.viewpager.widget.PagerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

import xyz.farshad.vocab.R
import xyz.farshad.vocab.activity.WordPagerActivity
import xyz.farshad.vocab.model.Word

/**
 * Created by farshad on 10/1/15.
 */
class WordSwipeAdapter(private val context: Context, internal var words: List<Word>) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    override fun getCount(): Int {
        return words.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val currentWord = words[position]

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val item_view = layoutInflater!!.inflate(R.layout.word_swipe_layer, container, false)
        val wordPagerName = item_view.findViewById<View>(R.id.wordPagerName) as TextView
        val wordPagerMeaning = item_view.findViewById<View>(R.id.wordPagerMeaning) as TextView
        val wordPagerTranslate = item_view.findViewById<View>(R.id.wordPagerTranslate) as TextView
        val wordPagerExample = item_view.findViewById<View>(R.id.wordPagerExample) as TextView
        wordPagerName.text = currentWord.name
        wordPagerMeaning.text = currentWord.meaning
        wordPagerTranslate.text = currentWord.translate
        wordPagerExample.text = currentWord.example


        //        showTranslateButton.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                wordPagerTranslate.setVisibility(View.VISIBLE);
        //                showTranslateButton.setVisibility(View.GONE);
        //                hideTranslateButton.setVisibility(View.VISIBLE);
        //
        //            }
        //        });
        //        hideTranslateButton.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                wordPagerTranslate.setVisibility(View.INVISIBLE);
        //                hideTranslateButton.setVisibility(View.GONE);
        //                showTranslateButton.setVisibility(View.VISIBLE);
        //            }
        //        });

        item_view.tag = "word_pager$position"
        container.addView(item_view)

        return item_view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
