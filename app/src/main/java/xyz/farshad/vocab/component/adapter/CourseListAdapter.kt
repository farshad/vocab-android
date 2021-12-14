package xyz.farshad.vocab.component.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import xyz.farshad.vocab.R
import xyz.farshad.vocab.activity.LevelListActivity
import xyz.farshad.vocab.data.model.Course

/**
 * Created by farshad on 9/29/15.
 */
class CourseListAdapter(context: Context, resource: Int, categories: List<Course>) : ArrayAdapter<Course>(context, resource, categories) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.cuorse_list_view, parent, false)
        }
        val currentCourse = getItem(position)

        val categoryText = itemView!!.findViewById<TextView>(R.id.catTextView)
        categoryText.text = currentCourse!!.name
        val countText = itemView.findViewById<TextView>(R.id.countTextView)

        // item click listener
        itemView.setOnClickListener {
            val clickedCourse = getItem(position)

            //start Level list activity
            val levelListIntent = Intent(context, LevelListActivity::class.java)
            levelListIntent.putExtra("courseId", clickedCourse!!.id)
            context.startActivity(levelListIntent)
        }

        return itemView
    }
}