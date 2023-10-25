package xyz.farshad.vocab.ui.chapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import xyz.farshad.vocab.R
import xyz.farshad.vocab.activity.WordListActivity
import xyz.farshad.vocab.data.entity.Chapter

/**
 * Created by farshad on 7/15/16.
 */
class ChapterListAdapter(context: Context, textViewResourceId: Int, objects: List<Chapter>) : ArrayAdapter<Chapter>(context, textViewResourceId, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.chapter_item, parent, false)
        }

        val currentLevel = getItem(position)

        val levelText = itemView!!.findViewById<TextView>(R.id.level_name_txt)
        levelText.text = currentLevel!!.title

        // item click listener
        itemView.setOnClickListener { view ->
            val clickedLevel = getItem(position)

            //start word list activity
            val wordListIntent = Intent(context, WordListActivity::class.java)
            wordListIntent.putExtra("levelId", clickedLevel!!.id)
            wordListIntent.putExtra("levelName", clickedLevel.title)
            context.startActivity(wordListIntent)
        }

        return itemView
    }
}
