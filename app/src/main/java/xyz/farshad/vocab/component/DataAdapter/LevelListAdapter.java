package xyz.farshad.vocab.component.DataAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.activity.WordListActivity;
import xyz.farshad.vocab.model.Level;

/**
 * Created by farshad on 7/15/16.
 */
public class LevelListAdapter extends ArrayAdapter<Level> {

    public LevelListAdapter(Context context, int textViewResourceId, List<Level> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.level_list_view, parent, false);
        }

        Level currentLevel = getItem(position);

        TextView levelText = itemView.findViewById(R.id.level_name_txt);
        levelText.setText(currentLevel.getName());

        // item click listener
        itemView.setOnClickListener(view -> {
            Level clickedLevel = getItem(position);

            //start word list activity
            Intent wordListIntent = new Intent(getContext(), WordListActivity.class);
            wordListIntent.putExtra("levelId", clickedLevel.getId());
            wordListIntent.putExtra("levelName", clickedLevel.getName());
            getContext().startActivity(wordListIntent);
        });

        return itemView;
    }
}
