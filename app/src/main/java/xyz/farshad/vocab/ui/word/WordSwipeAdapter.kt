package xyz.farshad.vocab.ui.word

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager.widget.PagerAdapter
import xyz.farshad.vocab.R
import xyz.farshad.vocab.data.entity.Word

/**
 * Created by farshad on 10/1/15.
 */
class WordSwipeAdapter(private val context: Context, internal var words: List<Word>) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    override fun getCount(): Int {
        return words.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val currentWord = words[position]

        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView =
            layoutInflater!!.inflate(R.layout.word_swipe_layer, container, false)
        val wordPagerNumber =
            itemView.findViewById<View>(R.id.wordPagerNumber) as TextView
        val wordPagerName =
            itemView.findViewById<View>(R.id.wordPagerName) as TextView
        val wordPagerTranslate =
            itemView.findViewById<View>(R.id.wordPagerTranslate) as TextView
        val wordPagerExample =
            itemView.findViewById<View>(R.id.wordPagerExample) as TextView
        val favButton = itemView.findViewById<View>(R.id.addToFav) as TextView

        setStarIcon(currentWord.isFavorite, favButton)

        wordPagerNumber.text = "${position + 1}"
        wordPagerName.text = currentWord.title
        wordPagerTranslate.text = currentWord.translate
        wordPagerExample.text = currentWord.example

        favButton.setOnClickListener {
            currentWord.isFavorite = !currentWord.isFavorite
            setStarIcon(currentWord.isFavorite, favButton)
            onFavIconClickListener?.let { it(currentWord) }
        }
        itemView.tag = "word_pager$position"
        container.addView(itemView)

        return itemView
    }

    private var onFavIconClickListener: ((Word) -> Unit)? = null

    private fun setStarIcon(isFavorite: Boolean, favButton: TextView) {
        val drawableCompat: Drawable? = if (isFavorite) {
            AppCompatResources.getDrawable(
                context,
                R.drawable.ic_baseline_star_24
            )
        } else {
            AppCompatResources.getDrawable(
                context,
                R.drawable.ic_baseline_star_border_24
            )
        }

        favButton.setCompoundDrawablesWithIntrinsicBounds(
            drawableCompat, null, null, null
        )
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun setOnFavIconClickListener(listener: (Word) -> Unit) {
        onFavIconClickListener = listener
    }
}
