package xyz.farshad.vocab.component.DataAdapter;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import xyz.farshad.vocab.R;
import xyz.farshad.vocab.model.Word;

/**
 * Created by farshad on 10/1/15.
 */
public class WordSwipeAdapter extends PagerAdapter {

    private Context context;
    List<Word> words;
    private LayoutInflater layoutInflater;

    public WordSwipeAdapter(Context context, List<Word> words){
        this.context = context;
        this.words = words;
    }
    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view==(RelativeLayout)o) ;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Word currentWord = words.get(position);

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.word_swipe_layer, container, false);
        TextView wordPagerName = (TextView) item_view.findViewById(R.id.wordPagerName);
        TextView wordPagerMeaning = (TextView) item_view.findViewById(R.id.wordPagerMeaning);
        TextView wordPagerTranslate = (TextView) item_view.findViewById(R.id.wordPagerTranslate);
        TextView wordPagerExample = (TextView) item_view.findViewById(R.id.wordPagerExample);
        wordPagerName.setText(currentWord.getName());
        wordPagerMeaning.setText(currentWord.getMeaning());
        wordPagerTranslate.setText(currentWord.getTranslate());
        wordPagerExample.setText(currentWord.getExample());

        // set font awesome
        Typeface font = Typeface.createFromAsset(context.getAssets(), "font/fontawesome.ttf");
        final Button showTranslateButton = (Button) item_view.findViewById(R.id.showTranslateButton);
        showTranslateButton.setTypeface(font);

        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
