package xyz.farshad.vocab.component.DataAdapter

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

        val editButton = itemView.findViewById<Button>(R.id.edit)
        val submitEditButton = itemView.findViewById<Button>(R.id.submitEditButton)
        val bookTextView = itemView.findViewById<TextView>(R.id.bookTextView)

        // item click listener
        itemView.setOnClickListener { view ->
            val clickedCourse = getItem(position)

            //start Level list activity
            val levelListIntent = Intent(context, LevelListActivity::class.java)
            levelListIntent.putExtra("courseId", clickedCourse!!.id)
            context.startActivity(levelListIntent)
        }

        //edit button click listener
        editButton.setOnClickListener { view ->
            val editCourse = getItem(position)
            val parentView = view.parent.parent as View

            // hide textView and edit button
            val clickedCatTextView = parentView.findViewById<View>(R.id.catTextView) as TextView
            clickedCatTextView.visibility = View.GONE
            editButton.visibility = View.GONE

            //show editText and submit button
            submitEditButton.visibility = View.VISIBLE
            val clickedCategoryEditText = parentView.findViewById<View>(R.id.categoryEditText) as EditText
            clickedCategoryEditText.visibility = View.VISIBLE
            clickedCategoryEditText.setText(editCourse!!.name)
            clickedCategoryEditText.requestFocus()
        }

        return itemView
    }
}