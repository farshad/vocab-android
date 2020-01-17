package xyz.farshad.vocab.component.DataAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.activity.LevelListActivity;
import xyz.farshad.vocab.model.Course;

/**
 * Created by farshad on 9/29/15.
 */
public class CourseListAdapter extends ArrayAdapter<Course> {

    public CourseListAdapter(Context context, int resource, List<Course> categories) {
        super(context, resource, categories);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.cuorse_list_view, parent, false);
        }
        Course currentCourse = getItem(position);

        TextView categoryText = itemView.findViewById(R.id.catTextView);
        categoryText.setText(currentCourse.getName());
        TextView countText = itemView.findViewById(R.id.countTextView);
        countText.setText("" + currentCourse.getWordCount() + "");

        final Button editButton = itemView.findViewById(R.id.edit);
        final Button submitEditButton = itemView.findViewById(R.id.submitEditButton);
        TextView bookTextView = itemView.findViewById(R.id.bookTextView);

        // item click listener
        itemView.setOnClickListener(view -> {
            Course clickedCourse = getItem(position);

            //start Level list activity
            Intent levelListIntent = new Intent(getContext(), LevelListActivity.class);
            levelListIntent.putExtra("courseId", clickedCourse.getId());
            getContext().startActivity(levelListIntent);
        });

        //edit button click listener
        editButton.setOnClickListener(view -> {
            Course editCourse = getItem(position);
            View parentView = (View) view.getParent().getParent();

            // hide textView and edit button
            TextView clickedCatTextView = (TextView) parentView.findViewById(R.id.catTextView);
            clickedCatTextView.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);

            //show editText and submit button
            submitEditButton.setVisibility(View.VISIBLE);
            EditText clickedCategoryEditText = (EditText) parentView.findViewById(R.id.categoryEditText);
            clickedCategoryEditText.setVisibility(View.VISIBLE);
            clickedCategoryEditText.setText(editCourse.getName());
            clickedCategoryEditText.requestFocus();
        });

        return itemView;
    }
}