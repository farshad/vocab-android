package xyz.farshad.vocab.component.adapter


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.viewpager.widget.PagerAdapter
import xyz.farshad.vocab.R
import xyz.farshad.vocab.activity.WordPagerActivity
import xyz.farshad.vocab.data.model.Word


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
        val itemView =
            layoutInflater!!.inflate(xyz.farshad.vocab.R.layout.word_swipe_layer, container, false)
        val wordPagerName =
            itemView.findViewById<View>(xyz.farshad.vocab.R.id.wordPagerName) as TextView
        val wordPagerMeaning =
            itemView.findViewById<View>(xyz.farshad.vocab.R.id.wordPagerMeaning) as TextView
        val wordPagerTranslate =
            itemView.findViewById<View>(xyz.farshad.vocab.R.id.wordPagerTranslate) as TextView
        val wordPagerExample =
            itemView.findViewById<View>(xyz.farshad.vocab.R.id.wordPagerExample) as TextView
        val favButton = itemView.findViewById<View>(xyz.farshad.vocab.R.id.addToFav) as Button

        if (currentWord.isFavorite) {
            val drawableCompat = AppCompatResources.getDrawable(
                context,
                R.drawable.ic_baseline_star_24
            )

            favButton.setCompoundDrawablesWithIntrinsicBounds(
                drawableCompat, null, null, null
            )
        }
        wordPagerName.text = currentWord.name
        wordPagerMeaning.text = currentWord.meaning
        wordPagerTranslate.text = currentWord.translate
        wordPagerExample.text = currentWord.example

        favButton.setOnClickListener {
            currentWord.isFavorite = !currentWord.isFavorite
            (context as WordPagerActivity).addToFavorite(currentWord)
        }
        itemView.tag = "word_pager$position"
        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
