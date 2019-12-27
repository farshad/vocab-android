package xyz.farshad.vocab.component.DataAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.activity.WordListActivity;
import xyz.farshad.vocab.activity.WordPagerActivity;
import xyz.farshad.vocab.model.Level;
import xyz.farshad.vocab.model.Word;

/**
 * Created by farshad on 10/2/15.
 */
public class WordListAdapter extends ArrayAdapter<Word> {
    public WordListAdapter(Context context, int resource, List<Word> words) {
        super(context, resource, words);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.word_list_view, parent, false);
        }
        Word currentWord = getItem(position);

        TextView word_name_txt = itemView.findViewById(R.id.word_name_txt);
        word_name_txt.setText(currentWord.getName());

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "font/fontawesome.ttf");
        TextView starBtn = itemView.findViewById(R.id.starBtn);

        if (currentWord.isFavorite()){
            starBtn.setText(Html.fromHtml("&#xf005;"));
        }
        starBtn.setTypeface(font);

        itemView.setOnClickListener(view -> {
            Word clickedWord = getItem(position);

            //start word pager activity
            Intent wordPageIntent = new Intent(getContext(), WordPagerActivity.class);
            wordPageIntent.putExtra("wordId", position);
            wordPageIntent.putExtra("levelId", clickedWord.getLevelId());
            getContext().startActivity(wordPageIntent);
        });

        return itemView;
    }
}
