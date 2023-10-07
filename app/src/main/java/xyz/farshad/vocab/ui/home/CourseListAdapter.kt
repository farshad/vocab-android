package xyz.farshad.vocab.ui.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import xyz.farshad.vocab.R
import xyz.farshad.vocab.data.model.Course

/**
 * Created by farshad on 9/29/15.
 */
class CourseListAdapter(context: Activity, resource: Int, categories: List<Course>) : ArrayAdapter<Course>(context, resource, categories) {

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

//            val action = HomeFragmentDirections.actionHomeFragmentToChapterFragment(clickedCourse!!.id)
//            Navigation.findNavController(context, R.id.nav_host_fragment).navigate(action)
        }

        return itemView
    }
}