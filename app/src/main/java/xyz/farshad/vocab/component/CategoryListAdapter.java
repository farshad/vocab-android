package xyz.farshad.vocab.component;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.activity.WordActivity;
import xyz.farshad.vocab.model.Course;

/**
 * Created by farshad on 9/29/15.
 */
public class CategoryListAdapter extends ArrayAdapter<Course>{

    public CategoryListAdapter(Context context, int resource, List<Course> categories) {
        super(context, resource, categories);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.category_list_view, parent, false);
        }
        Course currentCourse = getItem(position);

        TextView categoryText = (TextView) itemView.findViewById(R.id.catTextView);
        categoryText.setText(currentCourse.getName());
        TextView countText = (TextView) itemView.findViewById(R.id.countTextView);
        countText.setText("" + currentCourse.getWordCount() + "");

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "font/fontawesome.ttf");
        final Button editButton = (Button) itemView.findViewById(R.id.edit);
        final Button submitEditButton = (Button) itemView.findViewById(R.id.submitEditButton);
        TextView bookTextView = (TextView) itemView.findViewById(R.id.bookTextView);
        editButton.setTypeface(font);
        bookTextView.setTypeface(font);
        submitEditButton.setTypeface(font);

       // item click listener
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course clickedCourse = getItem(position);

                //start word activity
                Intent wordIntent = new Intent(getContext(), WordActivity.class);
                wordIntent.putExtra("categoryId", clickedCourse.getId());
                wordIntent.putExtra("action", "edit");
                getContext().startActivity(wordIntent);
            }
        });

        //edit button click listener
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        //submit button click listener
        submitEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Category procCategory = getItem(position);
//                View parentView = (View) view.getParent().getParent();
//
//                // hide editText and submit button
//                submitEditButton.setVisibility(View.GONE);
//                EditText clickedCategoryEditText = (EditText) parentView.findViewById(R.id.categoryEditText);
//                procCategory.setName(clickedCategoryEditText.getText().toString());
//                clickedCategoryEditText.setVisibility(View.GONE);
//
//                // update category
//                CategoryRepository categoryRepository = new CategoryRepository(getContext());
//                categoryRepository.open();
//                categoryRepository.update(procCategory);
//
//                // show textView and edit button
//                editButton.setVisibility(View.VISIBLE);
//                TextView clickedCatTextView = (TextView) parentView.findViewById(R.id.catTextView);
//                clickedCatTextView.setVisibility(View.VISIBLE);
//                clickedCatTextView.setText(procCategory.getName());

            }
        });

        return itemView;
    }
}