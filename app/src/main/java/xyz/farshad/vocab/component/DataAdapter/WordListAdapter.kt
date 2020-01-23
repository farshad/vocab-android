package xyz.farshad.vocab.component.DataAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import xyz.farshad.vocab.R
import xyz.farshad.vocab.activity.WordPagerActivity
import xyz.farshad.vocab.model.Word

/**
 * Created by farshad on 10/2/15.
 */
class WordListAdapter(context: Context, resource: Int, words: List<Word>) : ArrayAdapter<Word>(context, resource, words) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.word_list_view, parent, false)
        }
        val currentWord = getItem(position)

        val word_name_txt = itemView!!.findViewById<TextView>(R.id.word_name_txt)
        word_name_txt.text = currentWord!!.name

        itemView.setOnClickListener { view ->
            val clickedWord = getItem(position)

            //start word pager activity
            val wordPageIntent = Intent(context, WordPagerActivity::class.java)
            wordPageIntent.putExtra("wordId", position)
            wordPageIntent.putExtra("levelId", clickedWord!!.levelId)
            context.startActivity(wordPageIntent)
        }

        return itemView
    }
}
